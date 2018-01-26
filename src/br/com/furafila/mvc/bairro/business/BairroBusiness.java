package br.com.furafila.mvc.bairro.business;

import java.io.Serializable;
import java.util.List;

import br.com.furafila.mvc.bairro.model.Bairro;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gon�alves Pires
 */
public class BairroBusiness  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public void gravar(Bairro bairro) throws Exception{
        
        String strQuery = "INSERT INTO BAIRRO "
                + "("
                + "desc_bairro,"
                + "id_cidade_FK)"
                + " VALUES "
                + "('" + bairro.getDescBairro() + "'"
                + "," + bairro.getCidade().getIdCidade()
                + ")";
        
        bairro.setIdBairro(BancoDados.inserirRetornaID(strQuery));
         
    }
    
    public List<String> buscarBairro(Bairro bairro) throws Exception{
        
        String strQuery = "SELECT B.id_bairro AS [CD],"
                + " B.desc_bairro AS [BAIRRO] "
                + "FROM BAIRRO B WHERE B.desc_bairro LIKE '" + bairro.getDescBairro() + "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
}

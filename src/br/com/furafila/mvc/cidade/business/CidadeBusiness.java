package br.com.furafila.mvc.cidade.business;

import java.util.List;

import br.com.furafila.mvc.cidade.model.Cidade;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CidadeBusiness {
    
    public void gravar(Cidade cidade) throws Exception{
        
        String strQuery = "INSERT INTO CIDADE"
                + " ("
                + "desc_cidade,"
                + "id_uf_FK"
                + ") "
                + "VALUES "
                + "('" + cidade.getDescCidade() + "',"
                + " (SELECT U.id_uf FROM UF U WHERE U.sigla_uf LIKE '" + cidade.getUf().getSiglaUf() + "'))";
        
        cidade.setIdCidade(BancoDados.inserirRetornaID(strQuery));

    }
    
    public List<String> buscarCidade(Cidade cidade) throws Exception{
        
        String strQuery = "SELECT C.id_cidade AS [CD],"
                + " C.desc_cidade AS [CIDADE]"
                + " FROM CIDADE C WHERE C.desc_cidade LIKE '" + cidade.getDescCidade() + "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
}

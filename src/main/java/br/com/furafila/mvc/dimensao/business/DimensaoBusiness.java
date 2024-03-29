package br.com.furafila.mvc.dimensao.business;

import java.io.Serializable;

import br.com.furafila.mvc.dimensao.model.Dimensao;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class DimensaoBusiness  implements Serializable {

    private static final long serialVersionUID = 1L;
    public void gravar(Dimensao dimensao) throws Exception {

        String strQuery = "INSERT INTO DIMENSAO"
                + "("
                + "altura,"
                + "largura,"
                + "profundidade"
                + ") VALUES ("
                + dimensao.getAltura() + ","
                + dimensao.getLargura() + ","
                + dimensao.getProfundidade()
                + ") RETURNING id_dimensao;";

        dimensao.setIdDimensao(BancoDados.inserirRetornaID(strQuery));

    }

    public void alterar(Dimensao dimensao) throws Exception {

        String strQuery = "UPDATE DIMENSAO "
                + "SET "
                + "altura = " + dimensao.getAltura() + ", "
                + "largura =  " + dimensao.getLargura()+ ", "
                + "profundidade = " + dimensao.getProfundidade()
                + " WHERE id_dimensao = " + dimensao.getIdDimensao();
        
        BancoDados.executaComando(strQuery);
        
    }

}

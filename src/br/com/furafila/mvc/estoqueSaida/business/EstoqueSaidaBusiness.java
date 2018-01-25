package br.com.furafila.mvc.estoqueSaida.business;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoqueSaida.model.EstoqueSaida;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueSaidaBusiness {
    
    public void gravar(EstoqueSaida estoqueSaida, Estabelecimento estabelecimento) throws Exception {
        
        String strQuery = "SELECT * INTO #ESTOQUE_TEMP FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estabelecimento.getId_estabelecimento() + ";"
                +"INSERT INTO ESTOQUE_SAIDA ("
                + "qtdSaida,"
                + "id_produto_FK,"
                + "id_motivo_FK"
                + ") VALUES ("
                + estoqueSaida.getQtdSaida() + ","
                + estoqueSaida.getProduto().getId_produto() + ","
                + "(SELECT id_motivo FROM MOTIVO_SAIDA MS WHERE MS.motivoSaida = '" + estoqueSaida.getMotivoSaida().getMotivoSaida() + "')"
                + ")";
        
        BancoDados.executaComando(strQuery);
        
    }
    
}

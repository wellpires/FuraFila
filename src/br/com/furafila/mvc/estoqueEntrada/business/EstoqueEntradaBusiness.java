package br.com.furafila.mvc.estoqueEntrada.business;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoqueEntrada.model.EstoqueEntrada;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueEntradaBusiness {

    public void gravar(EstoqueEntrada estoqueEntrada, Estabelecimento estabelecimento) throws Exception {

        String strQuery = "SELECT * INTO #ESTOQUE_TEMP FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estabelecimento.getIdEstabelecimento() + ";"
                + "INSERT INTO ESTOQUE_ENTRADA ("
                + "qtdEntrada,"
                + "id_produto_FK,"
                + "id_motivo_entrada"
                + ") VALUES ("
                + estoqueEntrada.getQtdEntrada() + ","
                + estoqueEntrada.getProduto().getIdProduto() + ","
                + "(SELECT id_motivo_entrada FROM MOTIVO_ENTRADA ME WHERE ME.motivoEntrada = '" + estoqueEntrada.getMotivoEntrada().getMotivoEntrada() + "')"
                + ")";

        BancoDados.executaComando(strQuery);
        
    }

}

package br.com.furafila.mvc.estoqueProdutos.business;

import java.util.List;

import br.com.furafila.mvc.estoqueProdutos.model.EstoqueProdutos;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueProdutosBusiness {

	public void gravar(EstoqueProdutos estoqueProdutos) throws Exception {

		String strQuery = "INSERT INTO ESTOQUE_PRODUTOS" + " (" + "qtdEstoque," + "id_produto_FK," + "id_estoque_FK"
				+ ") VALUES (" + estoqueProdutos.getQtdEstoque() + "," + estoqueProdutos.getProduto().getIdProduto()
				+ "," + estoqueProdutos.getEstoque().getIdEstoque() + ")";

		estoqueProdutos.setIdEstoqueProdutos(BancoDados.inserirRetornaID(strQuery));

	}

	public List<List<String>> dadosParaRelatorio(EstoqueProdutos ep) throws Exception {

		String strQuery = "SELECT (SELECT E.razao_social FROM ESTABELECIMENTO E WHERE E.id_estabelecimento IN (SELECT EQ.id_estabelecimento_FK FROM ESTOQUE EQ WHERE EQ.id_estoque = EP.id_estoque_FK)) AS [ESTABELECIMENTO],"
				+ "(SELECT P.produto_desc FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK) AS [PRODUTO],"
				+ " (SELECT P.valor_unitario FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK) AS [VL_UNITARIO],"
				+ "EP.qtdEstoque AS [QTD_ESTOQUE],"
				+ "(SELECT TP.tipo_produto_desc FROM TIPO_PRODUTO TP WHERE TP.id_tipo_produto IN (SELECT P.id_tipo_produto_FK FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK)) AS [TIPO_PRODUTO] "
				+ "FROM ESTOQUE_PRODUTOS EP WHERE EP.id_estoque_FK = (SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = "
				+ ep.getEstoque().getEstabelecimento().getIdEstabelecimento() + ")";

		return BancoDados.retorna_N_Registros(strQuery);

	}

}

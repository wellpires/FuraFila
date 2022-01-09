package br.com.furafila.mvc.produto.business;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.modelsGerais.ComprarProduto;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;
import br.com.furafila.mvc.produto.model.Produto;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ProdutoBusiness {

	public void gravar(Produto produto) throws Exception {

		String strQuery = "INSERT INTO PRODUTO" + "(produto_desc," + "qtdMinima," + "id_tipo_produto_FK,"
				+ "id_imagem_FK," + "id_dimensao_FK" + ") VALUES (" + "'" + produto.getProdutoDesc() + "',"
				+ produto.getQtdMinima() + "," + produto.getTipoProduto().getIdTipoProduto() + ","
				+ produto.getImagem().getIdImagem() + "," + produto.getDimensao().getIdDimensao()
				+ ") RETURNING id_produto";

		produto.setIdProduto(BancoDados.inserirRetornaID(strQuery));

	}

	public void alterar(Produto produto) throws Exception {

		String strQuery = "UPDATE " + "PRODUTO " + "SET " + "produto_desc = '" + produto.getProdutoDesc() + "',"
				+ "qtdMinima = " + produto.getQtdMinima() + ", " + "id_tipo_produto_FK = "
				+ produto.getTipoProduto().getIdTipoProduto() + " WHERE id_produto = " + produto.getIdProduto();

		BancoDados.executaComando(strQuery);

	}

	public void alterarStatus(Produto produto) throws Exception {

		String strQuery = "UPDATE PRODUTO SET " + "status = " + produto.getStatusSQL() + " WHERE id_produto = "
				+ produto.getIdProduto();

		BancoDados.executaComando(strQuery);

	}

	public void alterarPreco(Produto produto) throws Exception {

		String strQuery = "UPDATE PRODUTO SET valor_unitario = " + produto.getValorUnitario() + " WHERE id_produto = "
				+ produto.getIdProduto();

		BancoDados.executaComando(strQuery);

	}

	public List<List<String>> listarProdutoCardapio(Estabelecimento estabelecimento) throws Exception {

		String strQuery = "SELECT P.id_produto AS [CD_PRODUTO]," + "P.produto_desc AS [PRODUTO],"
				+ "P.valor_unitario AS [PRECO],"
				+ " (SELECT TP.tipo_produto_desc FROM TIPO_PRODUTO TP WHERE TP.id_tipo_produto = P.id_tipo_produto_FK) AS [TIPO PRODUTO] "
				+ "FROM PRODUTO P WHERE P.id_tipo_produto_FK IN (SELECT TP.id_tipo_produto FROM TIPO_PRODUTO TP WHERE TP.status = 1)"
				+ " AND P.status = 1"
				+ " AND P.id_produto IN (SELECT EP.id_produto_FK FROM ESTOQUE_PRODUTOS EP WHERE EP.qtdEstoque = 0 "
				+ "AND EP.id_estoque_FK = (SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = "
				+ estabelecimento.getIdEstabelecimento() + "))";

		return BancoDados.retorna_N_Registros(strQuery);

	}

	public List<List<String>> listarProdutosComprar(ComprarProduto comprarProduto) throws Exception {

		String orderBy = "";
		String complementoQuery = "";

		if (0 != comprarProduto.getEstabelecimento().getIdEstabelecimento()) {
			complementoQuery += " AND P.id_produto IN (SELECT EP.id_produto_FK FROM ESTOQUE_PRODUTOS EP WHERE EP.id_estoque_FK IN (SELECT E.id_estoque FROM ESTOQUE E WHERE id_estabelecimento_FK = "
					+ comprarProduto.getEstabelecimento().getIdEstabelecimento() + "))";
		}
		if (!"".equals(comprarProduto.getOrdenarProdutos())) {
			orderBy += " ORDER BY " + comprarProduto.getOrdenarProdutos();
		}

		String strQuery = "select p.id_produto , P.produto_desc , P.valor_unitario, etb.id_estabelecimento, etb.razao_social, d.altura, d.largura, d.profundidade, p.id_imagem_fk "
				+ "from produto p " + "inner join estoque_produtos ep on ep.id_produto_fk = p.id_produto "
				+ "inner join estoque e on e.id_estoque = ep.id_estoque_fk "
				+ "inner join estabelecimento etb on etb.id_estabelecimento = e.id_estabelecimento_fk "
				+ "inner join dimensao d ON d.id_dimensao = p.id_dimensao_fk "
				+ "WHERE P.status = 1 AND P.valor_unitario > 0.0 AND"
				+ " P.id_produto IN (SELECT EP.id_produto_FK FROM ESTOQUE_PRODUTOS EP WHERE EP.qtdEstoque > 0) AND"
				+ " (P.produto_desc LIKE '%" + comprarProduto.getPesquisa() + "%'  "
				+ "OR P.id_produto IN (SELECT EP.id_produto_FK FROM ESTOQUE_PRODUTOS EP WHERE EP.id_estoque_FK IN  (SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK IN  (SELECT E.id_estabelecimento FROM ESTABELECIMENTO E WHERE E.razao_social LIKE '%"
				+ comprarProduto.getPesquisa() + "%'))))" + complementoQuery + orderBy;

		return BancoDados.retorna_N_Registros(strQuery);

	}

}

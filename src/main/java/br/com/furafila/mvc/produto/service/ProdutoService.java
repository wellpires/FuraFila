package br.com.furafila.mvc.produto.service;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.modelsGerais.ComprarProduto;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.mvc.produto.model.Produto;

public interface ProdutoService {

	List<Produto> buscarCardapio(Estabelecimento estabelecimento) throws Exception;

	List<Pedidos> listarProdutosComprar(ComprarProduto comprarProduto) throws Exception;

	Long gravar(Produto produtos);

	void alterar(Produto produto);

	void alterarStatus(Integer idProduto);

	void alterarPreco(Produto produto);

}

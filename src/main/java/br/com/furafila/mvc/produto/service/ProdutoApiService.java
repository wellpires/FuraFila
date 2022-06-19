package br.com.furafila.mvc.produto.service;

import java.util.List;

import br.com.furafila.mvc.estoqueProdutos.dto.ProdutosEstabelecimentoDTO;
import br.com.furafila.mvc.produto.dto.EditarProdutoDTO;
import br.com.furafila.mvc.produto.dto.EditarProdutoPrecoUnitarioDTO;
import br.com.furafila.mvc.produto.dto.NovoProdutoDTO;

public interface ProdutoApiService {

	Long gravar(NovoProdutoDTO novoProdutoDTO);

	List<ProdutosEstabelecimentoDTO> listarProdutosEstabelecimento(Integer idEstabelecimento);

	void alterar(EditarProdutoDTO editarProdutoDTO, Long productId);

	void alterarStatus(Integer idProduto);

	void alterarPreco(EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO, Long idProduto);

}

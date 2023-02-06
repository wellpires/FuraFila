package br.com.furafila.mvc.tipoProduto.service;

import java.util.List;

import br.com.furafila.mvc.tipoProduto.dto.EditarTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.NovoTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDuplicadoDTO;

public interface TipoProdutoApiService {

	TipoProdutoDuplicadoDTO verificarDuplicidade(String tipoProdutoDesc);

	void gravar(NovoTipoProdutoDTO novoTipoProdutoDTO);

	void alterar(EditarTipoProdutoDTO editarTipoProdutoDTO, Long idTipoProduto);

	void alterarStatus(Long idTipoProduto);

	List<TipoProdutoDTO> listarTipoProduto(boolean isAdministador);

}

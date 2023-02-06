package br.com.furafila.mvc.estoque.service;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueDTO;

public interface EstoqueApiService {

	Boolean verificarEstoqueExiste(Integer idEstabelecimento);

	void gravar(NovoEstoqueDTO novoEstoqueDTO);

}

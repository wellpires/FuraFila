package br.com.furafila.mvc.tipoProduto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.tipoProduto.dto.NovoTipoProdutoDTO;

public class NovoTipoProdutoRequest {

	@JsonProperty("productType")
	private NovoTipoProdutoDTO novoTipoProdutoDTO;

	public NovoTipoProdutoRequest(NovoTipoProdutoDTO novoTipoProdutoDTO) {
		this.novoTipoProdutoDTO = novoTipoProdutoDTO;
	}

	public NovoTipoProdutoDTO getNovoTipoProdutoDTO() {
		return novoTipoProdutoDTO;
	}

	public void setNovoTipoProdutoDTO(NovoTipoProdutoDTO novoTipoProdutoDTO) {
		this.novoTipoProdutoDTO = novoTipoProdutoDTO;
	}

}

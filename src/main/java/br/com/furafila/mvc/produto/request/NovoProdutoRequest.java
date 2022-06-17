package br.com.furafila.mvc.produto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.produto.dto.NovoProdutoDTO;

public class NovoProdutoRequest {

	@JsonProperty("product")
	private NovoProdutoDTO novoProdutoDTO;

	public NovoProdutoRequest(NovoProdutoDTO novoProdutoDTO) {
		this.novoProdutoDTO = novoProdutoDTO;
	}

	public NovoProdutoDTO getNovoProdutoDTO() {
		return novoProdutoDTO;
	}

	public void setNovoProdutoDTO(NovoProdutoDTO novoProdutoDTO) {
		this.novoProdutoDTO = novoProdutoDTO;
	}

}

package br.com.furafila.mvc.estoque.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueDTO;

public class NovoEstoqueRequest {

	@JsonProperty("stock")
	private NovoEstoqueDTO novoEstoqueDTO;

	public NovoEstoqueRequest() {
	}

	public NovoEstoqueRequest(NovoEstoqueDTO novoEstoqueDTO) {
		this.novoEstoqueDTO = novoEstoqueDTO;
	}

	public NovoEstoqueDTO getNovoEstoqueDTO() {
		return novoEstoqueDTO;
	}

	public void setNovoEstoqueDTO(NovoEstoqueDTO novoEstoqueDTO) {
		this.novoEstoqueDTO = novoEstoqueDTO;
	}

}

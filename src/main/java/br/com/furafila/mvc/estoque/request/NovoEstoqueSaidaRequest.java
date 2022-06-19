package br.com.furafila.mvc.estoque.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueSaidaDTO;

public class NovoEstoqueSaidaRequest {

	@JsonProperty("stockOutgoing")
	private NovoEstoqueSaidaDTO newEstoqueSaidaDTO;

	public NovoEstoqueSaidaRequest(NovoEstoqueSaidaDTO newEstoqueSaidaDTO) {
		this.newEstoqueSaidaDTO = newEstoqueSaidaDTO;
	}

	public NovoEstoqueSaidaDTO getNewEstoqueSaidaDTO() {
		return newEstoqueSaidaDTO;
	}

	public void setNewEstoqueSaidaDTO(NovoEstoqueSaidaDTO newEstoqueSaidaDTO) {
		this.newEstoqueSaidaDTO = newEstoqueSaidaDTO;
	}

}

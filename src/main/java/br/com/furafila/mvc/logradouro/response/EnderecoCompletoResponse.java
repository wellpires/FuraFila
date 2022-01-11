package br.com.furafila.mvc.logradouro.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.logradouro.dto.EnderecoCompletoDTO;

public class EnderecoCompletoResponse {

	@JsonProperty("address")
	private EnderecoCompletoDTO enderecoCompletoDTO;

	public EnderecoCompletoDTO getEnderecoCompletoDTO() {
		return enderecoCompletoDTO;
	}

	public void setEnderecoCompletoDTO(EnderecoCompletoDTO enderecoCompletoDTO) {
		this.enderecoCompletoDTO = enderecoCompletoDTO;
	}

}

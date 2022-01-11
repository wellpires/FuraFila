package br.com.furafila.mvc.logradouro.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.logradouro.dto.NovoLogradouroDTO;

public class NovoLogradouroResponse {

	@JsonProperty("address")
	private NovoLogradouroDTO novoLogradouroDTO;

	public NovoLogradouroResponse(NovoLogradouroDTO novoLogradouroDTO) {
		this.novoLogradouroDTO = novoLogradouroDTO;
	}

	public NovoLogradouroDTO getNovoLogradouroDTO() {
		return novoLogradouroDTO;
	}

	public void setNovoLogradouroDTO(NovoLogradouroDTO novoLogradouroDTO) {
		this.novoLogradouroDTO = novoLogradouroDTO;
	}

}

package br.com.furafila.mvc.cliente.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.cliente.dto.NovoClienteDTO;

public class NovoClienteRequest {

	@JsonProperty("customer")
	private NovoClienteDTO novoClienteDTO;

	public NovoClienteRequest(NovoClienteDTO novoClienteDTO) {
		this.novoClienteDTO = novoClienteDTO;
	}

	public NovoClienteDTO getNovoClienteDTO() {
		return novoClienteDTO;
	}

	public void setNovoClienteDTO(NovoClienteDTO novoClienteDTO) {
		this.novoClienteDTO = novoClienteDTO;
	}

}

package br.com.furafila.mvc.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.login.dto.NovoLoginDTO;

public class NovoLoginRequest {

	@JsonProperty("credential")
	private NovoLoginDTO novoLoginDTO;

	public NovoLoginRequest(NovoLoginDTO novoLoginDTO) {
		this.novoLoginDTO = novoLoginDTO;
	}

	public NovoLoginDTO getNovoLoginDTO() {
		return novoLoginDTO;
	}

	public void setNovoLoginDTO(NovoLoginDTO novoLoginDTO) {
		this.novoLoginDTO = novoLoginDTO;
	}

}

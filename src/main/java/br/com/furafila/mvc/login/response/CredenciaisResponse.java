package br.com.furafila.mvc.login.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.login.dto.CredenciaisDTO;

public class CredenciaisResponse {

	@JsonProperty("credential")
	private CredenciaisDTO credenciaisDTO;

	public CredenciaisDTO getCredenciaisDTO() {
		return credenciaisDTO;
	}

	public void setCredenciaisDTO(CredenciaisDTO credenciaisDTO) {
		this.credenciaisDTO = credenciaisDTO;
	}

}

package br.com.furafila.mvc.login.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DuplicidadeCredencialResponse {

	@JsonProperty("isCredentialExist")
	private Boolean credencialDuplicada;

	public Boolean getCredencialDuplicada() {
		return credencialDuplicada;
	}

	public void setCredencialDuplicada(Boolean credencialDuplicada) {
		this.credencialDuplicada = credencialDuplicada;
	}

}

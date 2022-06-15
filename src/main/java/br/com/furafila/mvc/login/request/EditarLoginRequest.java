package br.com.furafila.mvc.login.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.login.dto.EditarLoginDTO;

public class EditarLoginRequest {

	@JsonProperty("credential")
	private EditarLoginDTO editarLoginDTO;

	public EditarLoginRequest(EditarLoginDTO editarLoginDTO) {
		this.editarLoginDTO = editarLoginDTO;
	}

	public EditarLoginDTO getEditarLoginDTO() {
		return editarLoginDTO;
	}

	public void setEditarLoginDTO(EditarLoginDTO editarLoginDTO) {
		this.editarLoginDTO = editarLoginDTO;
	}

}

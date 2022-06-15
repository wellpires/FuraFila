package br.com.furafila.mvc.login.builder;

import br.com.furafila.mvc.login.dto.EditarLoginDTO;

public class EditarLoginDTOBuilder {

	private String usuario;
	private String senha;

	public EditarLoginDTOBuilder username(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public EditarLoginDTOBuilder password(String senha) {
		this.senha = senha;
		return this;
	}

	public EditarLoginDTO build() {
		EditarLoginDTO editarLoginDTO = new EditarLoginDTO();
		editarLoginDTO.setUsername(usuario);
		editarLoginDTO.setPassword(senha);
		return editarLoginDTO;
	}

}

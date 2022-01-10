package br.com.furafila.mvc.login.builder;

import br.com.furafila.mvc.login.dto.NovoLoginDTO;

public class NovoLoginDTOBuilder {

	private String username;
	private String password;
	private Boolean status;
	private Boolean deliveryAvailable;
	private Integer levelId;

	public NovoLoginDTOBuilder username(String username) {
		this.username = username;
		return this;
	}

	public NovoLoginDTOBuilder password(String password) {
		this.password = password;
		return this;
	}

	public NovoLoginDTOBuilder status(Boolean status) {
		this.status = status;
		return this;
	}

	public NovoLoginDTOBuilder deliveryAvailable(Boolean deliveryAvailable) {
		this.deliveryAvailable = deliveryAvailable;
		return this;
	}

	public NovoLoginDTOBuilder levelId(Integer levelId) {
		this.levelId = levelId;
		return this;
	}

	public NovoLoginDTO build() {
		NovoLoginDTO novoLoginDTO = new NovoLoginDTO();

		novoLoginDTO.setUsername(username);
		novoLoginDTO.setPassword(password);
		novoLoginDTO.setStatus(status);
		novoLoginDTO.setDeliveryAvailable(deliveryAvailable);
		novoLoginDTO.setLevelId(levelId);

		return novoLoginDTO;
	}

}

package br.com.furafila.mvc.estabelecimento.dto;

public class NovoEstabelecimentoDTO {

	private String corporateName;
	private String email;
	private String cnpj;
	private String stateRegistration;
	private Long idLogin;
	private Integer idImage;

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public Long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	public Integer getIdImage() {
		return idImage;
	}

}

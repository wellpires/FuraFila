package br.com.furafila.mvc.estabelecimento.dto;

public class NovoUsuarioEstabelecimentoDTO {

	private Long establishmentId;
	private Long loginId;

	public Long getEstablishmentId() {
		return establishmentId;
	}

	public void setEstablishmentId(Long establishmentId) {
		this.establishmentId = establishmentId;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

}

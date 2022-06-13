package br.com.furafila.mvc.estabelecimento.builder;

import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;

public class NovoEstabelecimentoDTOBuilder {

	private String corporateName;
	private String email;
	private String cnpj;
	private String stateRegistration;
	private Long idLogin;
	private Integer idImagem;

	public NovoEstabelecimentoDTOBuilder corporateName(String corporateName) {
		this.corporateName = corporateName;
		return this;
	}

	public NovoEstabelecimentoDTOBuilder email(String email) {
		this.email = email;
		return this;
	}

	public NovoEstabelecimentoDTOBuilder cnpj(Long cnpj) {
		this.cnpj = String.valueOf(cnpj);
		return this;
	}

	public NovoEstabelecimentoDTOBuilder stateRegistration(Long stateRegistration) {
		this.stateRegistration = String.valueOf(stateRegistration);
		return this;
	}

	public NovoEstabelecimentoDTOBuilder idLogin(Long idLogin) {
		this.idLogin = idLogin;
		return this;
	}

	public NovoEstabelecimentoDTOBuilder idImagem(Integer idImagem) {
		this.idImagem = idImagem;
		return this;
	}

	public NovoEstabelecimentoDTO build() {
		NovoEstabelecimentoDTO novoEstabelecimentoDTO = new NovoEstabelecimentoDTO();
		novoEstabelecimentoDTO.setCorporateName(corporateName);
		novoEstabelecimentoDTO.setEmail(email);
		novoEstabelecimentoDTO.setCnpj(cnpj);
		novoEstabelecimentoDTO.setStateRegistration(stateRegistration);
		novoEstabelecimentoDTO.setIdLogin(idLogin);
		novoEstabelecimentoDTO.setIdImage(idImagem);

		return novoEstabelecimentoDTO;
	}

}

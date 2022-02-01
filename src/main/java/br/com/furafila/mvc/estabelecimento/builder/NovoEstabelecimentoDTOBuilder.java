package br.com.furafila.mvc.estabelecimento.builder;

import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;

public class NovoEstabelecimentoDTOBuilder {

	private String corporateName;
	private String email;
	private String cnpj;
	private String stateRegistration;

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

	public NovoEstabelecimentoDTO build() {
		NovoEstabelecimentoDTO novoEstabelecimentoDTO = new NovoEstabelecimentoDTO();
		novoEstabelecimentoDTO.setCorporateName(corporateName);
		novoEstabelecimentoDTO.setEmail(email);
		novoEstabelecimentoDTO.setCnpj(cnpj);
		novoEstabelecimentoDTO.setStateRegistration(stateRegistration);

		return novoEstabelecimentoDTO;
	}

}

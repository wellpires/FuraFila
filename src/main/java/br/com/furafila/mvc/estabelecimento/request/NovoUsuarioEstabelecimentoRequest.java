package br.com.furafila.mvc.estabelecimento.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.NovoUsuarioEstabelecimentoDTO;

public class NovoUsuarioEstabelecimentoRequest {

	@JsonProperty("establishmentUser")
	private NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO;

	public NovoUsuarioEstabelecimentoRequest() {
	}

	public NovoUsuarioEstabelecimentoRequest(NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO) {
		this.novoUsuarioEstabelecimentoDTO = novoUsuarioEstabelecimentoDTO;
	}

	public NovoUsuarioEstabelecimentoDTO getNovoUsuarioEstabelecimentoDTO() {
		return novoUsuarioEstabelecimentoDTO;
	}

	public void setNovoUsuarioEstabelecimentoDTO(NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO) {
		this.novoUsuarioEstabelecimentoDTO = novoUsuarioEstabelecimentoDTO;
	}

}

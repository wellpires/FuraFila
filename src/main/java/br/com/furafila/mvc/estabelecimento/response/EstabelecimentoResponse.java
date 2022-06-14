package br.com.furafila.mvc.estabelecimento.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;

public class EstabelecimentoResponse {

	@JsonProperty("establishment")
	private EstabelecimentoDTO estabelecimentoDTO;

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

}

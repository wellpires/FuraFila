package br.com.furafila.mvc.estabelecimento.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;

public class EstabelecimentosResponse {

	@JsonProperty("establishments")
	private List<EstabelecimentoDTO> estabelecimentoDTOs;

	public List<EstabelecimentoDTO> getEstabelecimentoDTOs() {
		return estabelecimentoDTOs;
	}

	public void setEstabelecimentoDTOs(List<EstabelecimentoDTO> estabelecimentoDTOs) {
		this.estabelecimentoDTOs = estabelecimentoDTOs;
	}

}

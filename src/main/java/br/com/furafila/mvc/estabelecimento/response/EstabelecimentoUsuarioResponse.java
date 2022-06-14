package br.com.furafila.mvc.estabelecimento.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoUsuarioDTO;

public class EstabelecimentoUsuarioResponse {

	@JsonProperty("establishmentUsers")
	private List<EstabelecimentoUsuarioDTO> estabelecimentoUsuarioDTO;

	public List<EstabelecimentoUsuarioDTO> getEstabelecimentoUsuarioDTO() {
		return estabelecimentoUsuarioDTO;
	}

	public void setEstabelecimentoUsuarioDTO(List<EstabelecimentoUsuarioDTO> estabelecimentoUsuarioDTO) {
		this.estabelecimentoUsuarioDTO = estabelecimentoUsuarioDTO;
	}

}

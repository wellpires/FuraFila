package br.com.furafila.mvc.estabelecimento.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EditarEstabelecimentoDTO;

public class EditarEstabelecimentoRequest {

	@JsonProperty("establishment")
	private EditarEstabelecimentoDTO editarEstabelecimentoDTO;

	public EditarEstabelecimentoRequest(EditarEstabelecimentoDTO editarEstabelecimentoDTO) {
		this.editarEstabelecimentoDTO = editarEstabelecimentoDTO;
	}

	public EditarEstabelecimentoDTO getEditarEstabelecimentoDTO() {
		return editarEstabelecimentoDTO;
	}

	public void setEditarEstabelecimentoDTO(EditarEstabelecimentoDTO editarEstabelecimentoDTO) {
		this.editarEstabelecimentoDTO = editarEstabelecimentoDTO;
	}

}

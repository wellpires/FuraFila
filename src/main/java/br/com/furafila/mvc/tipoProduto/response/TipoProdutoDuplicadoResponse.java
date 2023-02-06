package br.com.furafila.mvc.tipoProduto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDuplicadoDTO;

public class TipoProdutoDuplicadoResponse {

	@JsonProperty("productType")
	private TipoProdutoDuplicadoDTO tipoProdutoDuplicadoDTO;

	public TipoProdutoDuplicadoDTO getTipoProdutoDuplicadoDTO() {
		return tipoProdutoDuplicadoDTO;
	}

	public void setTipoProdutoDuplicadoDTO(TipoProdutoDuplicadoDTO tipoProdutoDuplicadoDTO) {
		this.tipoProdutoDuplicadoDTO = tipoProdutoDuplicadoDTO;
	}

}

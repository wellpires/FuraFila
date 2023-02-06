package br.com.furafila.mvc.tipoProduto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDTO;

public class TipoProdutosResponse {

	@JsonProperty("productTypes")
	private List<TipoProdutoDTO> tipoProdutoDTOs;

	public List<TipoProdutoDTO> getTipoProdutoDTOs() {
		return tipoProdutoDTOs;
	}

	public void setTipoProdutoDTOs(List<TipoProdutoDTO> tipoProdutoDTOs) {
		this.tipoProdutoDTOs = tipoProdutoDTOs;
	}

}

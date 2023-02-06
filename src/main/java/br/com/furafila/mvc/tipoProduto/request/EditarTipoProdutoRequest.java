package br.com.furafila.mvc.tipoProduto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.tipoProduto.dto.EditarTipoProdutoDTO;

public class EditarTipoProdutoRequest {

	@JsonProperty("productType")
	private EditarTipoProdutoDTO editarTipoProdutoDTO;

	public EditarTipoProdutoRequest() {
	}

	public EditarTipoProdutoRequest(EditarTipoProdutoDTO editarTipoProdutoDTO) {
		this.editarTipoProdutoDTO = editarTipoProdutoDTO;
	}

	public EditarTipoProdutoDTO getEditarTipoProdutoDTO() {
		return editarTipoProdutoDTO;
	}

	public void setEditarTipoProdutoDTO(EditarTipoProdutoDTO editarTipoProdutoDTO) {
		this.editarTipoProdutoDTO = editarTipoProdutoDTO;
	}

}

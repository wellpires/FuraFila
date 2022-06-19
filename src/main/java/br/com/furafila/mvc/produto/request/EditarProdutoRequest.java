package br.com.furafila.mvc.produto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.produto.dto.EditarProdutoDTO;

public class EditarProdutoRequest {

	@JsonProperty("product")
	private EditarProdutoDTO editarProdutoDTO;

	public EditarProdutoRequest() {
	}

	public EditarProdutoRequest(EditarProdutoDTO editarProdutoDTO) {
		this.editarProdutoDTO = editarProdutoDTO;
	}

	public EditarProdutoDTO getEditarProdutoDTO() {
		return editarProdutoDTO;
	}

	public void setEditarProdutoDTO(EditarProdutoDTO editarProdutoDTO) {
		this.editarProdutoDTO = editarProdutoDTO;
	}

}

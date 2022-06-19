package br.com.furafila.mvc.produto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.produto.dto.EditarProdutoPrecoUnitarioDTO;

public class EditarProdutoPrecoUnitarioRequest {

	@JsonProperty("product")
	private EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO;

	public EditarProdutoPrecoUnitarioRequest(EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO) {
		this.editarProdutoPrecoUnitarioDTO = editarProdutoPrecoUnitarioDTO;
	}

	public EditarProdutoPrecoUnitarioDTO getEditarProdutoPrecoUnitarioDTO() {
		return editarProdutoPrecoUnitarioDTO;
	}

	public void setEditarProdutoPrecoUnitarioDTO(EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO) {
		this.editarProdutoPrecoUnitarioDTO = editarProdutoPrecoUnitarioDTO;
	}

}

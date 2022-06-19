package br.com.furafila.mvc.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditarProdutoDTO {

	private String name;
	private Long minimumStockQuantity;
	private Long productTypeId;

	@JsonProperty("dimension")
	private EditarDimensaoDTO editarDimensaoDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMinimumStockQuantity() {
		return minimumStockQuantity;
	}

	public void setMinimumStockQuantity(Long minimumStockQuantity) {
		this.minimumStockQuantity = minimumStockQuantity;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public EditarDimensaoDTO getEditarDimensaoDTO() {
		return editarDimensaoDTO;
	}

	public void setEditarDimensaoDTO(EditarDimensaoDTO editarDimensaoDTO) {
		this.editarDimensaoDTO = editarDimensaoDTO;
	}

}

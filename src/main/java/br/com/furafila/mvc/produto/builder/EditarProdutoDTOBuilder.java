package br.com.furafila.mvc.produto.builder;

import br.com.furafila.mvc.produto.dto.EditarDimensaoDTO;
import br.com.furafila.mvc.produto.dto.EditarProdutoDTO;

public class EditarProdutoDTOBuilder {

	private String name;
	private Long minimumStockQuantity;
	private Long productTypeId;
	private EditarDimensaoDTO dimension;

	public EditarProdutoDTOBuilder name(String name) {
		this.name = name;
		return this;
	}

	public EditarProdutoDTOBuilder minimumStockQuantity(Integer minimumStockQuantity) {
		this.minimumStockQuantity = minimumStockQuantity.longValue();
		return this;
	}

	public EditarProdutoDTOBuilder productTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId.longValue();
		return this;
	}

	public EditarProdutoDTOBuilder newDimension() {
		this.dimension = new EditarDimensaoDTO();
		return this;
	}

	public EditarProdutoDTOBuilder height(Integer height) {
		this.dimension.setHeight(height);
		return this;
	}

	public EditarProdutoDTOBuilder width(Integer width) {
		this.dimension.setWidth(width);
		return this;
	}

	public EditarProdutoDTOBuilder length(Integer length) {
		this.dimension.setLength(length);
		return this;
	}

	public EditarProdutoDTO builder() {
		EditarProdutoDTO editarProdutoDTO = new EditarProdutoDTO();
		editarProdutoDTO.setName(name);
		editarProdutoDTO.setMinimumStockQuantity(minimumStockQuantity);
		editarProdutoDTO.setProductTypeId(productTypeId);
		editarProdutoDTO.setEditarDimensaoDTO(dimension);
		return editarProdutoDTO;
	}

}

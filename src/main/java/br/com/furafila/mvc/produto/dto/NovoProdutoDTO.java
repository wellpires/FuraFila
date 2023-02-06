package br.com.furafila.mvc.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovoProdutoDTO {

	private String name;
	private Long minimumStockQuantity;
	private Long productTypeId;
	private Long imageId;

	@JsonProperty("dimension")
	private NovaDimensaoDTO novaDimensaoDTO;

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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public NovaDimensaoDTO getNovaDimensaoDTO() {
		return novaDimensaoDTO;
	}

	public void setNovaDimensaoDTO(NovaDimensaoDTO novaDimensaoDTO) {
		this.novaDimensaoDTO = novaDimensaoDTO;
	}

}
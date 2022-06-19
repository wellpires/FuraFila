package br.com.furafila.mvc.estoqueProdutos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutosEstabelecimentoDTO {

	private Long productId;
	private String productName;
	private Double unitPrice;
	private Boolean status;
	private Long imageId;
	private Long stockMinimumQuantity;
	private Long stockQuantity;

	@JsonProperty("productType")
	private EstabelecimentoProdutoTipoDTO estabelecimentoProdutoTipoDTO;

	@JsonProperty("dimension")
	private EstabelecimentoProdutoDimesaoDTO estabelecimentoProdutoDimesaoDTO;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getStockMinimumQuantity() {
		return stockMinimumQuantity;
	}

	public void setStockMinimumQuantity(Long stockMinimumQuantity) {
		this.stockMinimumQuantity = stockMinimumQuantity;
	}

	public Long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public EstabelecimentoProdutoTipoDTO getEstabelecimentoProdutoTipoDTO() {
		return estabelecimentoProdutoTipoDTO;
	}

	public void setEstabelecimentoProdutoTipoDTO(EstabelecimentoProdutoTipoDTO estabelecimentoProdutoTipoDTO) {
		this.estabelecimentoProdutoTipoDTO = estabelecimentoProdutoTipoDTO;
	}

	public EstabelecimentoProdutoDimesaoDTO getEstabelecimentoProdutoDimesaoDTO() {
		return estabelecimentoProdutoDimesaoDTO;
	}

	public void setEstabelecimentoProdutoDimesaoDTO(EstabelecimentoProdutoDimesaoDTO estabelecimentoProdutoDimesaoDTO) {
		this.estabelecimentoProdutoDimesaoDTO = estabelecimentoProdutoDimesaoDTO;
	}

}

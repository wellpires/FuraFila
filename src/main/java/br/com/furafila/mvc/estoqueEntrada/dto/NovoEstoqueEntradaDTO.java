package br.com.furafila.mvc.estoqueEntrada.dto;

public class NovoEstoqueEntradaDTO {

	private Long stockIncomingQuantity;
	private Long productId;
	private String incomingReason;
	private Long stockId;

	public Long getStockIncomingQuantity() {
		return stockIncomingQuantity;
	}

	public void setStockIncomingQuantity(Long stockIncomingQuantity) {
		this.stockIncomingQuantity = stockIncomingQuantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getIncomingReason() {
		return incomingReason;
	}

	public void setIncomingReason(String incomingReason) {
		this.incomingReason = incomingReason;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

}

package br.com.furafila.mvc.estoque.builder;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueSaidaDTO;

public class NewEstoqueSaidaDTOBuilder {

	private Long stockOutgoingQuantity;
	private Long productId;
	private String outgoingReason;

	public NewEstoqueSaidaDTOBuilder stockOutgoingQuantity(Long stockOutgoingQuantity) {
		this.stockOutgoingQuantity = stockOutgoingQuantity;
		return this;
	}

	public NewEstoqueSaidaDTOBuilder productId(Integer productId) {
		this.productId = productId.longValue();
		return this;
	}

	public NewEstoqueSaidaDTOBuilder outgoingReason(String outgoingReason) {
		this.outgoingReason = outgoingReason;
		return this;
	}

	public NovoEstoqueSaidaDTO build() {
		NovoEstoqueSaidaDTO newEstoqueSaidaDTO = new NovoEstoqueSaidaDTO();
		newEstoqueSaidaDTO.setStockOutgoingQuantity(stockOutgoingQuantity);
		newEstoqueSaidaDTO.setProductId(productId);
		newEstoqueSaidaDTO.setOutgoingReason(outgoingReason);

		return newEstoqueSaidaDTO;
	}

}

package br.com.furafila.mvc.estoqueEntrada.builder;

import br.com.furafila.mvc.estoqueEntrada.dto.NovoEstoqueEntradaDTO;
import br.com.furafila.utils.FuraFilaConstants;

public class NovoEstoqueEntradaDTOBuilder {

	private Long stockIncomingQuantity;
	private Long productId;
	private String incomingReason;
	private Long stockId;

	public NovoEstoqueEntradaDTOBuilder stockIncomingQuantity(Long stockIncomingQuantity) {
		this.stockIncomingQuantity = stockIncomingQuantity;
		return this;
	}

	public NovoEstoqueEntradaDTOBuilder productId(Integer productId) {
		this.productId = productId.longValue();
		return this;
	}

	public NovoEstoqueEntradaDTOBuilder firstEntry() {
		this.incomingReason = FuraFilaConstants.MOTIVO_ENTRADA_INICIAL;
		return this;
	}

	public NovoEstoqueEntradaDTOBuilder stockId(Long stockId) {
		this.stockId = stockId;
		return this;
	}
	
	public NovoEstoqueEntradaDTO build() {
		NovoEstoqueEntradaDTO novoEstoqueEntradaDTO = new NovoEstoqueEntradaDTO();
		novoEstoqueEntradaDTO.setProductId(productId);
		novoEstoqueEntradaDTO.setStockIncomingQuantity(stockIncomingQuantity);
		novoEstoqueEntradaDTO.setIncomingReason(incomingReason);
		novoEstoqueEntradaDTO.setStockId(stockId);
		return novoEstoqueEntradaDTO;
	}

	

}

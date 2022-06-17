package br.com.furafila.mvc.estoqueEntrada.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estoqueEntrada.dto.NovoEstoqueEntradaDTO;

public class NovoEstoqueEntradaRequest {

	@JsonProperty("stockIncoming")
	private NovoEstoqueEntradaDTO novoEstoqueEntradaDTO;

	public NovoEstoqueEntradaRequest(NovoEstoqueEntradaDTO novoEstoqueEntradaDTO) {
		this.novoEstoqueEntradaDTO = novoEstoqueEntradaDTO;
	}

	public NovoEstoqueEntradaDTO getNovoEstoqueEntradaDTO() {
		return novoEstoqueEntradaDTO;
	}

	public void setNovoEstoqueEntradaDTO(NovoEstoqueEntradaDTO novoEstoqueEntradaDTO) {
		this.novoEstoqueEntradaDTO = novoEstoqueEntradaDTO;
	}

}

package br.com.furafila.mvc.pedidos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.pedidos.dto.ComandaAprovadaDTO;

public class ComandasAprovadasResponse {

	@JsonProperty("orders")
	private List<ComandaAprovadaDTO> comandasAprovadasDTOs;

	public void setComandasAprovadasDTOs(List<ComandaAprovadaDTO> comandasAprovadasDTOs) {
		this.comandasAprovadasDTOs = comandasAprovadasDTOs;
	}

	public List<ComandaAprovadaDTO> getComandasAprovadas() {
		return comandasAprovadasDTOs;
	}

}

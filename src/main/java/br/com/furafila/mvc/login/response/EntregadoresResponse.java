package br.com.furafila.mvc.login.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.login.dto.EntregadorDTO;

public class EntregadoresResponse {

	@JsonProperty("couriers")
	private List<EntregadorDTO> entregadores;

	public List<EntregadorDTO> getEntregadores() {
		return entregadores;
	}

	public void setEntregadores(List<EntregadorDTO> entregadores) {
		this.entregadores = entregadores;
	}

}

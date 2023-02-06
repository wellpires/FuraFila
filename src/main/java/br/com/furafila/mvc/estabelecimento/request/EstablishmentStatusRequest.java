package br.com.furafila.mvc.estabelecimento.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EstablishmentStatusDTO;

public class EstablishmentStatusRequest {

	@JsonProperty("establishment")
	private EstablishmentStatusDTO establishmentStatusDTO;

	public EstablishmentStatusRequest() {
	}

	public EstablishmentStatusRequest(EstablishmentStatusDTO establishmentStatusDTO) {
		this.establishmentStatusDTO = establishmentStatusDTO;
	}

	public EstablishmentStatusDTO getEstablishmentStatusDTO() {
		return establishmentStatusDTO;
	}

	public void setEstablishmentStatusDTO(EstablishmentStatusDTO establishmentStatusDTO) {
		this.establishmentStatusDTO = establishmentStatusDTO;
	}

}

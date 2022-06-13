package br.com.furafila.mvc.estabelecimento.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;

/**
 * @author wellington
 *
 */
public class EstabelecimentoInformacoesIniciaisResponse {

	@JsonProperty("establishmentInitialInfo")
	private EstabelecimentoInformacoesIniciaisDTO estabelecimentoInformacoesIniciaisDTO;

	public EstabelecimentoInformacoesIniciaisDTO getEstabelecimentoInformacoesIniciaisDTO() {
		return estabelecimentoInformacoesIniciaisDTO;
	}

	public void setEstabelecimentoInformacoesIniciaisDTO(
			EstabelecimentoInformacoesIniciaisDTO estabelecimentoInformacoesIniciaisDTO) {
		this.estabelecimentoInformacoesIniciaisDTO = estabelecimentoInformacoesIniciaisDTO;
	}

}

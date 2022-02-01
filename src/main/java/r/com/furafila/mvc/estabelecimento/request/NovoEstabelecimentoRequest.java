package r.com.furafila.mvc.estabelecimento.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;

public class NovoEstabelecimentoRequest {

	@JsonProperty("establishment")
	private NovoEstabelecimentoDTO novoEstabelecimentoDTO;

	public NovoEstabelecimentoRequest(NovoEstabelecimentoDTO novoEstabelecimentoDTO) {
		this.novoEstabelecimentoDTO = novoEstabelecimentoDTO;
	}

	public NovoEstabelecimentoDTO getNovoEstabelecimentoDTO() {
		return novoEstabelecimentoDTO;
	}

	public void setNovoEstabelecimentoDTO(NovoEstabelecimentoDTO novoEstabelecimentoDTO) {
		this.novoEstabelecimentoDTO = novoEstabelecimentoDTO;
	}

}

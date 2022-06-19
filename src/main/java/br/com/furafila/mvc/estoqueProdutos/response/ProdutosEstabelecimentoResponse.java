package br.com.furafila.mvc.estoqueProdutos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.mvc.estoqueProdutos.dto.ProdutosEstabelecimentoDTO;

public class ProdutosEstabelecimentoResponse {

	@JsonProperty("establishmentProducts")
	private List<ProdutosEstabelecimentoDTO> produtosEstabelecimentoDTO;

	public List<ProdutosEstabelecimentoDTO> getProdutosEstabelecimentoDTO() {
		return produtosEstabelecimentoDTO;
	}

	public void setProdutosEstabelecimentoDTO(List<ProdutosEstabelecimentoDTO> produtosEstabelecimentoDTO) {
		this.produtosEstabelecimentoDTO = produtosEstabelecimentoDTO;
	}

}

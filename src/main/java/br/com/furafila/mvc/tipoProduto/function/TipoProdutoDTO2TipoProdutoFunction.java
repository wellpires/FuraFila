package br.com.furafila.mvc.tipoProduto.function;

import java.util.function.Function;

import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;

public class TipoProdutoDTO2TipoProdutoFunction implements Function<TipoProdutoDTO, TipoProduto> {

	@Override
	public TipoProduto apply(TipoProdutoDTO tipoProdutoDTO) {

		TipoProduto tipoProduto = new TipoProduto();
		tipoProduto.setIdTipoProduto(tipoProdutoDTO.getId().intValue());
		tipoProduto.setTipoProdutoDesc(tipoProdutoDTO.getName());
		tipoProduto.setStatus(tipoProdutoDTO.getStatus());

		return tipoProduto;
	}

}

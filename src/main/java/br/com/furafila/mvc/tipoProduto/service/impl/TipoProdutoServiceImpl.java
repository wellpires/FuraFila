package br.com.furafila.mvc.tipoProduto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.furafila.mvc.tipoProduto.dto.EditarTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.NovoTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDuplicadoDTO;
import br.com.furafila.mvc.tipoProduto.function.TipoProdutoDTO2TipoProdutoFunction;
import br.com.furafila.mvc.tipoProduto.model.TipoProduto;
import br.com.furafila.mvc.tipoProduto.service.TipoProdutoApiService;
import br.com.furafila.mvc.tipoProduto.service.TipoProdutoService;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoProdutoServiceImpl implements TipoProdutoService {

	private TipoProdutoApiService tipoProdutoApiService = new TipoProdutoApiServiceImpl();

	@Override
	public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception {

		return tipoProdutoApiService.listarTipoProduto(isAdministador).stream()
				.map(new TipoProdutoDTO2TipoProdutoFunction()).collect(Collectors.toList());

	}

	@Override
	public boolean pegarTipoProduto(TipoProduto tipoProduto) throws Exception {

		TipoProdutoDuplicadoDTO tipoProdutoDuplicadoDTO = tipoProdutoApiService
				.verificarDuplicidade(tipoProduto.getTipoProdutoDesc());

		return tipoProdutoDuplicadoDTO.getIsExists();

	}

	@Override
	public void gravar(TipoProduto tipoProduto) {
		tipoProdutoApiService.gravar(new NovoTipoProdutoDTO(tipoProduto.getTipoProdutoDesc()));

	}

	@Override
	public void alterar(TipoProduto tipoProduto) {

		tipoProdutoApiService.alterar(new EditarTipoProdutoDTO(tipoProduto.getTipoProdutoDesc()),
				tipoProduto.getIdTipoProduto().longValue());

	}

	@Override
	public void alterarStatus(TipoProduto tipoProduto) {

		tipoProdutoApiService.alterarStatus(tipoProduto.getIdTipoProduto().longValue());

	}

}

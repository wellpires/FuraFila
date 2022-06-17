package br.com.furafila.mvc.estoqueEntrada.service.impl;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoqueEntrada.builder.NovoEstoqueEntradaDTOBuilder;
import br.com.furafila.mvc.estoqueEntrada.dto.NovoEstoqueEntradaDTO;
import br.com.furafila.mvc.estoqueEntrada.service.EstoqueEntradaApiService;
import br.com.furafila.mvc.produto.model.Produto;

public class EstoqueEntradaServiceImpl implements EstoqueEntradaService {

	private EstoqueEntradaApiService estoqueEntradaApiService = new EstoqueEntradaApiServiceImpl();

	@Override
	public void gravar(Produto produto, Estabelecimento estabelecimento) {

		NovoEstoqueEntradaDTO novoEstoqueEntradaDTO = new NovoEstoqueEntradaDTOBuilder().stockIncomingQuantity(0l)
				.productId(produto.getIdProduto()).firstEntry().stockId(estabelecimento.getEstoqueId()).build();

		estoqueEntradaApiService.gravar(novoEstoqueEntradaDTO);

	}

}

package br.com.furafila.mvc.estoque.service.impl;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoque.builder.NewEstoqueSaidaDTOBuilder;
import br.com.furafila.mvc.estoque.business.EstoqueBusiness;
import br.com.furafila.mvc.estoque.dto.NovoEstoqueSaidaDTO;
import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.estoque.service.EstoqueSaidaApiService;
import br.com.furafila.mvc.estoque.service.EstoqueService;
import br.com.furafila.mvc.estoqueEntrada.builder.NovoEstoqueEntradaDTOBuilder;
import br.com.furafila.mvc.estoqueEntrada.dto.NovoEstoqueEntradaDTO;
import br.com.furafila.mvc.estoqueEntrada.model.EstoqueEntrada;
import br.com.furafila.mvc.estoqueEntrada.service.EstoqueEntradaApiService;
import br.com.furafila.mvc.estoqueEntrada.service.impl.EstoqueEntradaApiServiceImpl;
import br.com.furafila.mvc.estoqueSaida.model.EstoqueSaida;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueServiceImpl implements EstoqueService {

	private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();

	private EstoqueEntradaApiService estoqueEntradaApiService = new EstoqueEntradaApiServiceImpl();

	private EstoqueSaidaApiService estoqueSaidaApiService = new EstoqueSaidaApiServiceImpl();

	@Override
	public boolean estoqueExiste(Estoque estoque) throws Exception {

		List<String> lstDados = estoqueBusiness.verificarEstoqueExiste(estoque);

		Boolean estoqueExiste = false;

		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
			estoqueExiste = Integer.parseInt(lstDados.get(0)) > 0;
		}

		return estoqueExiste;

	}

	@Override
	public void buscarCodigoEstoque(Estoque estoque) throws Exception {

		List<String> lstDados = estoqueBusiness.buscarCodigoEstoque(estoque);

		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
			estoque.setIdEstoque(Integer.parseInt(lstDados.get(0)));
		}

	}

	@Override
	public void gravarEstoqueSaida(EstoqueSaida estoqueSaida) {

		NovoEstoqueSaidaDTO newEstoqueSaidaDTO = new NewEstoqueSaidaDTOBuilder()
				.stockOutgoingQuantity(estoqueSaida.getQtdSaida().longValue())
				.productId(estoqueSaida.getProduto().getIdProduto())
				.outgoingReason(estoqueSaida.getMotivoSaida().getMotivoSaida()).build();

		estoqueSaidaApiService.gravar(newEstoqueSaidaDTO);

	}

	@Override
	public void gravarEstoqueEntrada(EstoqueEntrada estoqueEntrada, Estabelecimento estabelecimento) {
		NovoEstoqueEntradaDTO novoEstoqueEntradaDTO = new NovoEstoqueEntradaDTOBuilder()
				.stockIncomingQuantity(estoqueEntrada.getQtdEntrada().longValue())
				.productId(estoqueEntrada.getProduto().getIdProduto())
				.incomingReason(estoqueEntrada.getMotivoEntrada().getMotivoEntrada())
				.stockId(estabelecimento.getEstoqueId()).build();

		estoqueEntradaApiService.gravar(novoEstoqueEntradaDTO);

	}

}

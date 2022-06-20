package br.com.furafila.mvc.pedidos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.dto.ComandaAprovadaDTO;
import br.com.furafila.mvc.pedidos.function.ComandaAprovadaDTO2PedidoLockerFunction;
import br.com.furafila.mvc.pedidos.service.PedidoApiService;
import br.com.furafila.mvc.pedidos.service.PedidoService;

public class PedidoServiceImpl implements PedidoService {

	private PedidoApiService pedidoApiService = new PedidoApiServiceImpl();

	@Override
	public List<PedidoLocker> listarComandasAprovadas(Estabelecimento estabelecimento) {

		List<ComandaAprovadaDTO> comandasAprovadas = pedidoApiService
				.listarComandasAprovadas(estabelecimento.getIdEstabelecimento());

		return comandasAprovadas.stream().map(new ComandaAprovadaDTO2PedidoLockerFunction())
				.collect(Collectors.toList());
	}

}

package br.com.furafila.mvc.comanda.service.impl;

import java.util.List;

import br.com.furafila.mvc.comanda.model.Comanda;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.model.Pedidos;

public interface ComandaService {

	List<PedidoLocker> listarComandasAprovadas(String complementoQuery) throws Exception;

	List<Pedidos> listarProdutosPorComanda(Comanda comanda) throws Exception;

	PedidoLocker buscarEstabelecimentoLocker(Comanda comanda) throws Exception;

}
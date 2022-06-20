package br.com.furafila.mvc.pedidos.service;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;

public interface PedidoService {

	List<PedidoLocker> listarComandasAprovadas(Estabelecimento estabelecimento);

}

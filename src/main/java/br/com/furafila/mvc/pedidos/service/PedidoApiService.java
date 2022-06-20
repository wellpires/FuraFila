package br.com.furafila.mvc.pedidos.service;

import java.util.List;

import br.com.furafila.mvc.pedidos.dto.ComandaAprovadaDTO;

public interface PedidoApiService {

	List<ComandaAprovadaDTO> listarComandasAprovadas(Integer idEstabelecimento);

}

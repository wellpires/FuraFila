package br.com.furafila.mvc.estoqueEntrada.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import br.com.furafila.mvc.estoqueEntrada.dto.NovoEstoqueEntradaDTO;
import br.com.furafila.mvc.estoqueEntrada.exception.EstoqueEntradaServerApiException;
import br.com.furafila.mvc.estoqueEntrada.request.NovoEstoqueEntradaRequest;
import br.com.furafila.mvc.estoqueEntrada.service.EstoqueEntradaApiService;
import br.com.furafila.utils.EstoqueUrlConstants;

public class EstoqueEntradaApiServiceImpl implements EstoqueEntradaApiService {

	@Override
	public void gravar(NovoEstoqueEntradaDTO novoEstoqueEntradaDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstoqueUrlConstants.STOCK_INCOMING).request()
				.post(Entity.entity(new NovoEstoqueEntradaRequest(novoEstoqueEntradaDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstoqueEntradaServerApiException(response.getStatusInfo());
		}

	}

}

package br.com.furafila.mvc.estoque.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueSaidaDTO;
import br.com.furafila.mvc.estoque.exception.EstoqueSaidaServerApiException;
import br.com.furafila.mvc.estoque.request.NovoEstoqueSaidaRequest;
import br.com.furafila.mvc.estoque.service.EstoqueSaidaApiService;
import br.com.furafila.utils.EstoqueUrlConstants;

public class EstoqueSaidaApiServiceImpl implements EstoqueSaidaApiService {

	@Override
	public void gravar(NovoEstoqueSaidaDTO newEstoqueSaidaDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstoqueUrlConstants.CHECK_ESTABLISHMENT_HAS_STOCK).request()
				.post(Entity.entity(new NovoEstoqueSaidaRequest(newEstoqueSaidaDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstoqueSaidaServerApiException(response.getStatusInfo());
		}

	}

}

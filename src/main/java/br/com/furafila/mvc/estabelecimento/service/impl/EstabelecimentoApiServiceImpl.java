package br.com.furafila.mvc.estabelecimento.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.cliente.exception.ClienteServerApiException;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.response.NovoEstabelecimentoResponse;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoApiService;
import br.com.furafila.utils.EstabelecimentoUrlConstants;
import r.com.furafila.mvc.estabelecimento.request.NovoEstabelecimentoRequest;

public class EstabelecimentoApiServiceImpl implements EstabelecimentoApiService {

	private static final Logger logger = LogManager.getLogger(EstabelecimentoApiServiceImpl.class);

	@Override
	public Long gravar(NovoEstabelecimentoDTO novoEstabelecimentoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstabelecimentoUrlConstants.SAVE_ESTABLISHMENT).request().post(
				Entity.entity(new NovoEstabelecimentoRequest(novoEstabelecimentoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new ClienteServerApiException(statusMessage);
		}

		return response.readEntity(NovoEstabelecimentoResponse.class).getId();
	}

}

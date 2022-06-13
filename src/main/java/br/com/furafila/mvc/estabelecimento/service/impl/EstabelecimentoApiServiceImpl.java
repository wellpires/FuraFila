package br.com.furafila.mvc.estabelecimento.service.impl;

import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.exception.EstabelecimentoServerApiException;
import br.com.furafila.mvc.estabelecimento.request.NovoEstabelecimentoRequest;
import br.com.furafila.mvc.estabelecimento.response.EstabelecimentoInformacoesIniciaisResponse;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoApiService;
import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.utils.EstabelecimentoUrlConstants;

public class EstabelecimentoApiServiceImpl implements EstabelecimentoApiService {

	private static final Logger logger = LogManager.getLogger(EstabelecimentoApiServiceImpl.class);

	@Override
	public void gravar(NovoEstabelecimentoDTO novoEstabelecimentoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstabelecimentoUrlConstants.SAVE_ESTABLISHMENT).request().post(
				Entity.entity(new NovoEstabelecimentoRequest(novoEstabelecimentoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new EstabelecimentoServerApiException(statusMessage);
		}

	}

	@Override
	public EstabelecimentoInformacoesIniciaisDTO buscarInformacoesIniciaisEstabelecimento(Login login) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", login.getIdLogin());

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.FIND_ESTABLISHMENT_INITIAL_INFO)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {

			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new EstabelecimentoServerApiException(statusMessage);

		}

		return response.readEntity(EstabelecimentoInformacoesIniciaisResponse.class)
				.getEstabelecimentoInformacoesIniciaisDTO();

	}

}

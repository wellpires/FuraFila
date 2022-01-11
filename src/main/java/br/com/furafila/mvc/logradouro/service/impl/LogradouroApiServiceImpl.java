package br.com.furafila.mvc.logradouro.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.logradouro.dto.EnderecoCompletoDTO;
import br.com.furafila.mvc.logradouro.dto.NovoLogradouroDTO;
import br.com.furafila.mvc.logradouro.exception.LogradouroServerApiException;
import br.com.furafila.mvc.logradouro.response.EnderecoCompletoResponse;
import br.com.furafila.mvc.logradouro.response.NovoLogradouroResponse;
import br.com.furafila.mvc.logradouro.service.LogradouroApiService;
import br.com.furafila.utils.LogradouroUrlConstants;

public class LogradouroApiServiceImpl implements LogradouroApiService {

	private static final Logger logger = LogManager.getLogger(LogradouroApiServiceImpl.class);

	@Override
	public void gravarLogradouro(NovoLogradouroDTO novoLogradouroDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(LogradouroUrlConstants.SAVE_ADDRESS).request(MediaType.APPLICATION_JSON)
				.post(Entity.json(new NovoLogradouroResponse(novoLogradouroDTO)));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new LogradouroServerApiException(statusMessage);
		}

	}

	@Override
	public EnderecoCompletoDTO buscarEnderecoCompleto(Integer nroCep) {

		Map<String, Integer> parameters = new HashMap<>();
		parameters.put("postalCode", nroCep);
		UriComponents uriExpanded = UriComponentsBuilder.fromHttpUrl(LogradouroUrlConstants.FIND_FULL_ADDRESS)
				.buildAndExpand(parameters);

		Client client = ClientBuilder.newClient();
		Response response = client.target(uriExpanded.toUriString()).request(MediaType.APPLICATION_JSON).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new LogradouroServerApiException(statusMessage);
		}

		return response.readEntity(EnderecoCompletoResponse.class).getEnderecoCompletoDTO();
	}

}

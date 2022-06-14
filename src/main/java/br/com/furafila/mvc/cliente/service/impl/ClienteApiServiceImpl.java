package br.com.furafila.mvc.cliente.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import br.com.furafila.mvc.cliente.dto.NovoClienteDTO;
import br.com.furafila.mvc.cliente.exception.ClienteServerApiException;
import br.com.furafila.mvc.cliente.request.NovoClienteRequest;
import br.com.furafila.mvc.cliente.service.ClienteApiService;
import br.com.furafila.utils.ClienteUrlConstants;

public class ClienteApiServiceImpl implements ClienteApiService {

	@Override
	public void gravar(NovoClienteDTO novoClienteDTO) {

		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Client client = ClientBuilder.newClient();
		Response response = client
				.register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS))
				.target(ClienteUrlConstants.SAVE_CUSTOMER).request()
				.post(Entity.entity(new NovoClienteRequest(novoClienteDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ClienteServerApiException(response.getStatusInfo());
		}

	}

}

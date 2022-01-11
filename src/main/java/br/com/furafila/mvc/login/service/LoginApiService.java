package br.com.furafila.mvc.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.dto.EntregadorDTO;
import br.com.furafila.mvc.login.dto.NovoLoginDTO;
import br.com.furafila.mvc.login.exception.LoginServerApiException;
import br.com.furafila.mvc.login.request.NovoLoginRequest;
import br.com.furafila.mvc.login.response.CredenciaisResponse;
import br.com.furafila.mvc.login.response.DuplicidadeCredencialResponse;
import br.com.furafila.mvc.login.response.EntregadoresResponse;
import br.com.furafila.mvc.login.response.NovoLoginResponse;
import br.com.furafila.utils.FuraFilaURLConstants;

public class LoginApiService {

	private static final Logger logger = LogManager.getLogger(LoginApiService.class);

	public CredenciaisDTO autenticarLogin(String usuario, String senha) {

		Client client = ClientBuilder.newClient();
		CredenciaisResponse credenciaisResponse = client.target(FuraFilaURLConstants.VALIDATE_CREDENTIALS)
				.queryParam("username", usuario).queryParam("password", senha).request(MediaType.APPLICATION_JSON)
				.get(CredenciaisResponse.class);

		return credenciaisResponse.getCredenciaisDTO();
	}

	public boolean verificarDuplicidade(Integer idLogin, String usuario, boolean isAlteracao) {

		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", idLogin.toString());
		parameters.put("username", usuario);
		UriComponents components = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.CHECK_CREDENTIAL_DUPLICITY)
				.buildAndExpand(parameters);

		Client client = ClientBuilder.newClient();
		DuplicidadeCredencialResponse duplicidadeCredencialResponse = client.target(components.toUri())
				.queryParam("include", isAlteracao).request(MediaType.APPLICATION_JSON)
				.get(DuplicidadeCredencialResponse.class);

		return duplicidadeCredencialResponse.getCredencialDuplicada();
	}

	public List<EntregadorDTO> listarEntregadores() {

		Client client = ClientBuilder.newClient();
		EntregadoresResponse entregadoresResponse = client.target(FuraFilaURLConstants.LIST_COURIERS)
				.request(MediaType.APPLICATION_JSON).get(EntregadoresResponse.class);

		return entregadoresResponse.getEntregadores();
	}

	public Long gravarLogin(NovoLoginDTO novoLoginDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(FuraFilaURLConstants.SAVE_CREDENTIAL).request(MediaType.APPLICATION_JSON)
				.post(Entity.json(new NovoLoginRequest(novoLoginDTO)));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			String statusMessage = String.format("%d - %s", response.getStatusInfo().getStatusCode(),
					response.getStatusInfo().getReasonPhrase());
			logger.error(statusMessage);
			throw new LoginServerApiException(statusMessage);
		}

		return Optional.ofNullable(response.readEntity(NovoLoginResponse.class)).orElseGet(NovoLoginResponse::new)
				.getId();
	}

}

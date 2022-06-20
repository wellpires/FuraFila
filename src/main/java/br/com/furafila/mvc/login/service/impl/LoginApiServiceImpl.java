package br.com.furafila.mvc.login.service.impl;

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

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.dto.EditarLoginDTO;
import br.com.furafila.mvc.login.dto.EntregadorDTO;
import br.com.furafila.mvc.login.dto.NovoLoginDTO;
import br.com.furafila.mvc.login.exception.LoginServerApiException;
import br.com.furafila.mvc.login.request.EditarLoginRequest;
import br.com.furafila.mvc.login.request.NovoLoginRequest;
import br.com.furafila.mvc.login.response.CredenciaisResponse;
import br.com.furafila.mvc.login.response.DuplicidadeCredencialResponse;
import br.com.furafila.mvc.login.response.EntregadoresResponse;
import br.com.furafila.mvc.login.response.NovoLoginResponse;
import br.com.furafila.utils.FuraFilaURLConstants;

public class LoginApiServiceImpl implements LoginApiService {

	@Override
	public CredenciaisDTO autenticarLogin(String usuario, String senha) {

		Client client = ClientBuilder.newClient();

		Response response = client.target(FuraFilaURLConstants.VALIDATE_CREDENTIALS).queryParam("username", usuario)
				.queryParam("password", senha).request(MediaType.APPLICATION_JSON).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

		return response.readEntity(CredenciaisResponse.class).getCredenciaisDTO();
	}

	@Override
	public boolean verificarDuplicidade(Integer idLogin, String usuario, boolean isAlteracao) {

		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", idLogin.toString());
		parameters.put("username", usuario);
		UriComponents components = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.CHECK_CREDENTIAL_DUPLICITY)
				.buildAndExpand(parameters);

		Client client = ClientBuilder.newClient();
		Response response = client.target(components.toUri()).queryParam("include", isAlteracao)
				.request(MediaType.APPLICATION_JSON).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

		return response.readEntity(DuplicidadeCredencialResponse.class).getCredencialDuplicada();
	}

	@Override
	public List<EntregadorDTO> listarEntregadores() {

		Client client = ClientBuilder.newClient();
		Response response = client.target(FuraFilaURLConstants.LIST_COURIERS).request(MediaType.APPLICATION_JSON).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

		return response.readEntity(EntregadoresResponse.class).getEntregadores();
	}

	@Override
	public Long gravarLogin(NovoLoginDTO novoLoginDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(FuraFilaURLConstants.SAVE_CREDENTIAL).request(MediaType.APPLICATION_JSON)
				.post(Entity.json(new NovoLoginRequest(novoLoginDTO)));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

		return Optional.ofNullable(response.readEntity(NovoLoginResponse.class)).orElseGet(NovoLoginResponse::new)
				.getId();
	}

	@Override
	public void alterar(Integer idLogin, EditarLoginDTO editarLoginDTO) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);
		String path = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.EDIT_CREDENTIAL).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_JSON)
				.put(Entity.json(new EditarLoginRequest(editarLoginDTO)));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void deletar(Integer idLogin) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);
		String path = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.DELETE_CREDENTIAL).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_JSON).delete();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterarStatus(Integer idLogin) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);
		String path = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.TOGGLE_COURIER_STATUS).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_JSON).delete();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterarDisponibilidade(Integer idLogin) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);
		String path = UriComponentsBuilder.fromHttpUrl(FuraFilaURLConstants.TOGGLE_COURIER_DELIVERY_AVAILABILITY)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_JSON).put(Entity.json(""));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new LoginServerApiException(response.getStatusInfo());
		}
	}

}

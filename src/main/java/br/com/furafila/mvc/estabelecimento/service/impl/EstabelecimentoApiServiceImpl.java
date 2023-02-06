package br.com.furafila.mvc.estabelecimento.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.estabelecimento.dto.EditarEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoUsuarioDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstablishmentStatusDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoUsuarioEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.exception.EstabelecimentoServerApiException;
import br.com.furafila.mvc.estabelecimento.request.EditarEstabelecimentoRequest;
import br.com.furafila.mvc.estabelecimento.request.EstablishmentStatusRequest;
import br.com.furafila.mvc.estabelecimento.request.NovoEstabelecimentoRequest;
import br.com.furafila.mvc.estabelecimento.request.NovoUsuarioEstabelecimentoRequest;
import br.com.furafila.mvc.estabelecimento.response.EstabelecimentoInformacoesIniciaisResponse;
import br.com.furafila.mvc.estabelecimento.response.EstabelecimentoResponse;
import br.com.furafila.mvc.estabelecimento.response.EstabelecimentoUsuarioResponse;
import br.com.furafila.mvc.estabelecimento.response.EstabelecimentosResponse;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoApiService;
import br.com.furafila.utils.EstabelecimentoUrlConstants;

public class EstabelecimentoApiServiceImpl implements EstabelecimentoApiService {

	@Override
	public void gravar(NovoEstabelecimentoDTO novoEstabelecimentoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstabelecimentoUrlConstants.SAVE_ESTABLISHMENT).request().post(
				Entity.entity(new NovoEstabelecimentoRequest(novoEstabelecimentoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public EstabelecimentoInformacoesIniciaisDTO buscarInformacoesIniciaisEstabelecimento(Integer idLogin) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.FIND_ESTABLISHMENT_INITIAL_INFO)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());

		}

		return response.readEntity(EstabelecimentoInformacoesIniciaisResponse.class)
				.getEstabelecimentoInformacoesIniciaisDTO();

	}

	@Override
	public EstabelecimentoDTO buscarEstabelecimento(Integer idEstabelecimento) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", idEstabelecimento);

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.FIND_ESTABLISHMENT)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(EstabelecimentoResponse.class).getEstabelecimentoDTO();
	}

	@Override
	public void editarEstabelecimento(EditarEstabelecimentoDTO editarEstabelecimentoDTO, Integer estabelecimentoId) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", estabelecimentoId);

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.EDIT_ESTABLISHMENT)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().put(
				Entity.entity(new EditarEstabelecimentoRequest(editarEstabelecimentoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void adicionarUsuarioEstabelecimento(NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstabelecimentoUrlConstants.ADD_USER_ESTABLISHMENT).request()
				.post(Entity.entity(new NovoUsuarioEstabelecimentoRequest(novoUsuarioEstabelecimentoDTO),
						MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public List<EstabelecimentoUsuarioDTO> listarUsuarios(Integer idEstabelecimento, Integer idLogin) {

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.LIST_USER_ESTABLISHMENT)
				.queryParam("establishmentId", idEstabelecimento).queryParam("loginId", idLogin).build().toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(EstabelecimentoUsuarioResponse.class).getEstabelecimentoUsuarioDTO();
	}

	@Override
	public void deletarEstabelecimentoUsuario(Integer idLogin) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("loginId", idLogin);

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.DELETE_USER_ESTABLISHMENT)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().delete();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public List<EstabelecimentoDTO> listarEstabelecimento() {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstabelecimentoUrlConstants.LIST_ESTABLISHMENTS).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());

		}

		return response.readEntity(EstabelecimentosResponse.class).getEstabelecimentoDTOs();
	}

	@Override
	public void alterarStatus(EstablishmentStatusDTO establishmentStatusDTO, Long establishmentId) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", establishmentId);

		String path = UriComponentsBuilder.fromHttpUrl(EstabelecimentoUrlConstants.EDIT_STATUS_ESTABLISHMENT)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request()
				.put(Entity.entity(new EstablishmentStatusRequest(establishmentStatusDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new EstabelecimentoServerApiException(response.getStatusInfo());
		}

	}

}

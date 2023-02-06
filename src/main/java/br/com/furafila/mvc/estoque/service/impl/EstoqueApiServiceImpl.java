package br.com.furafila.mvc.estoque.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.estoque.dto.NovoEstoqueDTO;
import br.com.furafila.mvc.estoque.request.NovoEstoqueRequest;
import br.com.furafila.mvc.estoque.service.EstoqueApiService;
import br.com.furafila.mvc.produto.exception.ProdutoServerApiException;
import br.com.furafila.utils.EstoqueUrlConstants;

public class EstoqueApiServiceImpl implements EstoqueApiService {

	@Override
	public Boolean verificarEstoqueExiste(Integer idEstabelecimento) {

		Map<String, Object> param = new HashMap<>();
		param.put("establishmentId", idEstabelecimento);

		String path = UriComponentsBuilder.fromHttpUrl(EstoqueUrlConstants.CHECK_ESTABLISHMENT_HAS_STOCK)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().head();

		if (HttpStatus.OK.value() == response.getStatus()) {
			return true;
		} else if (HttpStatus.NOT_FOUND.value() == response.getStatus()) {
			return false;
		}

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

		return true;
	}

	@Override
	public void gravar(NovoEstoqueDTO novoEstoqueDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(EstoqueUrlConstants.CREATE_STOCK).request()
				.post(Entity.entity(new NovoEstoqueRequest(novoEstoqueDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

}

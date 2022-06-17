package br.com.furafila.mvc.produto.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import br.com.furafila.mvc.produto.dto.NovoProdutoDTO;
import br.com.furafila.mvc.produto.exception.ProdutoServerApiException;
import br.com.furafila.mvc.produto.request.NovoProdutoRequest;
import br.com.furafila.mvc.produto.response.NovoProdutoResponse;
import br.com.furafila.mvc.produto.service.ProdutoApiService;
import br.com.furafila.utils.ProdutoUrlConstants;

public class ProdutoApiServiceImpl implements ProdutoApiService {

	@Override
	public Long gravar(NovoProdutoDTO novoProdutoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(ProdutoUrlConstants.SAVE_PRODUCT).request()
				.post(Entity.entity(new NovoProdutoRequest(novoProdutoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(NovoProdutoResponse.class).getId();
	}

}

package br.com.furafila.mvc.tipoProduto.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.produto.exception.ProdutoServerApiException;
import br.com.furafila.mvc.tipoProduto.dto.EditarTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.NovoTipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDTO;
import br.com.furafila.mvc.tipoProduto.dto.TipoProdutoDuplicadoDTO;
import br.com.furafila.mvc.tipoProduto.request.EditarTipoProdutoRequest;
import br.com.furafila.mvc.tipoProduto.request.NovoTipoProdutoRequest;
import br.com.furafila.mvc.tipoProduto.response.TipoProdutoDuplicadoResponse;
import br.com.furafila.mvc.tipoProduto.response.TipoProdutosResponse;
import br.com.furafila.mvc.tipoProduto.service.TipoProdutoApiService;
import br.com.furafila.utils.ProdutoUrlConstants;

public class TipoProdutoApiServiceImpl implements TipoProdutoApiService {

	@Override
	public TipoProdutoDuplicadoDTO verificarDuplicidade(String tipoProdutoDesc) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("productType", tipoProdutoDesc);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.CHECK_PRODUCT_TYPE_NAME_DUPLICITY)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().accept(MediaType.APPLICATION_JSON_VALUE).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(TipoProdutoDuplicadoResponse.class).getTipoProdutoDuplicadoDTO();
	}

	@Override
	public void gravar(NovoTipoProdutoDTO novoTipoProdutoDTO) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(ProdutoUrlConstants.SAVE_PRODUCT_TYPE).request()
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.post(Entity.entity(new NovoTipoProdutoRequest(novoTipoProdutoDTO), MediaType.APPLICATION_JSON_VALUE));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterar(EditarTipoProdutoDTO editarTipoProdutoDTO, Long idTipoProduto) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("productTypeId", idTipoProduto);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.EDIT_PRODUCT_TYPE).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().accept(MediaType.APPLICATION_JSON_VALUE).put(
				Entity.entity(new EditarTipoProdutoRequest(editarTipoProdutoDTO), MediaType.APPLICATION_JSON_VALUE));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterarStatus(Long idTipoProduto) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("productTypeId", idTipoProduto);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.EDIT_PRODUCT_TYPE).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().accept(MediaType.APPLICATION_JSON_VALUE).delete();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public List<TipoProdutoDTO> listarTipoProduto(boolean isAdministador) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromHttpUrl(ProdutoUrlConstants.LIST_PRODUCT_TYPES);

		if (!isAdministador) {
			uriBuilder.queryParam("active-only", Boolean.TRUE);
		}

		Client client = ClientBuilder.newClient();
		Response response = client.target(uriBuilder.build().toUriString()).request()
				.accept(MediaType.APPLICATION_JSON_VALUE).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(TipoProdutosResponse.class).getTipoProdutoDTOs();
	}

}

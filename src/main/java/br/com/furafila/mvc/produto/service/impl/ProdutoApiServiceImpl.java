package br.com.furafila.mvc.produto.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.estoqueProdutos.dto.ProdutosEstabelecimentoDTO;
import br.com.furafila.mvc.estoqueProdutos.response.ProdutosEstabelecimentoResponse;
import br.com.furafila.mvc.produto.dto.EditarProdutoDTO;
import br.com.furafila.mvc.produto.dto.EditarProdutoPrecoUnitarioDTO;
import br.com.furafila.mvc.produto.dto.NovoProdutoDTO;
import br.com.furafila.mvc.produto.exception.ProdutoServerApiException;
import br.com.furafila.mvc.produto.request.EditarProdutoPrecoUnitarioRequest;
import br.com.furafila.mvc.produto.request.EditarProdutoRequest;
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

	@Override
	public List<ProdutosEstabelecimentoDTO> listarProdutosEstabelecimento(Integer idEstabelecimento) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("establishmentId", idEstabelecimento);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.LIST_ESTABLISHMENT_PRODUCTS)
				.buildAndExpand(param).toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(ProdutosEstabelecimentoResponse.class).getProdutosEstabelecimentoDTO();
	}

	@Override
	public void alterar(EditarProdutoDTO editarProdutoDTO, Long productId) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.EDIT_PRODUCT).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request()
				.put(Entity.entity(new EditarProdutoRequest(editarProdutoDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterarStatus(Integer idProduto) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("productId", idProduto);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.TOGGLE_PRODUCT_STATUS).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request().delete();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}

	}

	@Override
	public void alterarPreco(EditarProdutoPrecoUnitarioDTO editarProdutoPrecoUnitarioDTO, Long idProduto) {
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("productId", idProduto);
		String path = UriComponentsBuilder.fromHttpUrl(ProdutoUrlConstants.EDIT_UNIT_PRICE).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request()
				.put(Entity.entity(new EditarProdutoPrecoUnitarioRequest(editarProdutoPrecoUnitarioDTO), MediaType.APPLICATION_JSON));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ProdutoServerApiException(response.getStatusInfo());
		}
		
	}

}

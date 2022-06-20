package br.com.furafila.mvc.pedidos.service.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.pedidos.dto.ComandaAprovadaDTO;
import br.com.furafila.mvc.pedidos.exception.PedidoServerApiException;
import br.com.furafila.mvc.pedidos.response.ComandasAprovadasResponse;
import br.com.furafila.mvc.pedidos.service.PedidoApiService;
import br.com.furafila.utils.PedidoUrlConstants;

public class PedidoApiServiceImpl implements PedidoApiService {

	@Override
	public List<ComandaAprovadaDTO> listarComandasAprovadas(Integer idEstabelecimento) {

		String path = UriComponentsBuilder.fromHttpUrl(PedidoUrlConstants.LIST_APPROVED_ORDERS)
				.queryParam("establishmentId", idEstabelecimento).build().toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_JSON).get();

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new PedidoServerApiException(response.getStatusInfo());
		}

		return response.readEntity(ComandasAprovadasResponse.class).getComandasAprovadas();
	}

}

package br.com.furafila.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import br.com.furafila.mvc.cep.dto.LocationDTO;
import br.com.furafila.mvc.cep.model.Distancia;
import br.com.furafila.mvc.cep.response.CepResponse;
import br.com.furafila.mvc.cep.response.GeoCodeResponse;
import br.com.furafila.mvc.logradouro.model.Logradouro;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class WebServices {

	public CepResponse pesquisarCep(Logradouro logradouro) throws MalformedURLException, Exception {

		Client client = ClientBuilder.newClient();
		return client.target(FuraFilaURLConstants.SEARCH_CEP).queryParam("cep", logradouro.getNroCepFormatado())
				.queryParam("formato", "json").request(MediaType.APPLICATION_JSON).get(CepResponse.class);
	}

	public Element calcularDistancia(Distancia distancia) throws MalformedURLException, DocumentException {

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ distancia.getOrigem().getLatiLongGoogle() + "&destinations="
				+ distancia.getDestino().getLatiLongGoogle() + "&key=".concat(FuraFilaUtils.getGoogleApiKey()));

		return getDocumento(url).getRootElement();
	}

	public LocationDTO pesquisarCepGoogle(Logradouro logradouro) throws MalformedURLException, Exception {

		Client client = ClientBuilder.newClient();
		GeoCodeResponse geoCodeResponse = client.target(FuraFilaURLConstants.GEO_CODE)
				.queryParam("address", FuraFilaUtils.removerAcentos(logradouro.getLogradouroFormatadoGoogle()))
				.queryParam("key", FuraFilaUtils.getGoogleApiKey()).request(MediaType.APPLICATION_JSON)
				.get(GeoCodeResponse.class);

		return geoCodeResponse.getGeoCodeResultDTO().get(0).getGeometryDTO().getLocationDTO();
	}

	public Element pesquisarCepViaLongLati(Logradouro logradouro) throws DocumentException, MalformedURLException {
		return getDocumento(webServiceGoogle(logradouro.getLatiLongGoogle())).getRootElement();
	}

	public URL webServiceGoogle(String parametros) throws MalformedURLException {
		return new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + parametros
				+ "&key=".concat(FuraFilaUtils.getGoogleApiKey()));
	}

	private Document getDocumento(URL url) throws DocumentException {
		return new SAXReader().read(url);
	}

}

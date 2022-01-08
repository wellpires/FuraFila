package br.com.furafila.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import br.com.furafila.mvc.cep.model.Distancia;
import br.com.furafila.mvc.logradouro.model.Logradouro;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class WebServices {

	public Element pesquisarCep(Logradouro logradouro) throws MalformedURLException, Exception {

		URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + logradouro.getNroCepFormatado()
				+ "&formato=xml");

		return getDocumento(url).getRootElement();
	}

	public Element calcularDistancia(Distancia distancia) throws MalformedURLException, DocumentException {

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/xml?" + "origins="
				+ distancia.getOrigem().getLatiLongGoogle() + "&destinations="
				+ distancia.getDestino().getLatiLongGoogle() + "&key=");

		return getDocumento(url).getRootElement();
	}

	public Element pesquisarCepGoogle(Logradouro logradouro) throws MalformedURLException, Exception {
		return getDocumento(webServiceGoogle(FuraFilaUtils.removerAcentos(logradouro.getLogradouroFormatadoGoogle())))
				.getRootElement();
	}

	public Element pesquisarCepViaLongLati(Logradouro logradouro) throws DocumentException, MalformedURLException {
		return getDocumento(webServiceGoogle(logradouro.getLatiLongGoogle())).getRootElement();
	}

	public URL webServiceGoogle(String parametros) throws MalformedURLException {
		return new URL("https://maps.googleapis.com/maps/api/geocode/xml?address=" + parametros + "&key=");
	}

	private Document getDocumento(URL url) throws DocumentException {
		return new SAXReader().read(url);
	}

}

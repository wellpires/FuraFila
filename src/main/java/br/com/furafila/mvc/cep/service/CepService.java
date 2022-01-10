package br.com.furafila.mvc.cep.service;

import java.util.Iterator;

import org.dom4j.Element;

import br.com.furafila.mvc.cep.dto.LocationDTO;
import br.com.furafila.mvc.cep.model.Distancia;
import br.com.furafila.mvc.cep.response.CepResponse;
import br.com.furafila.mvc.logradouro.model.Logradouro;
import br.com.furafila.utils.WebServices;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CepService {

	private WebServices webService = new WebServices();

	public Logradouro pesquisarCep(Logradouro logradouro) throws Exception {

		CepResponse cepResponse = getWebService().pesquisarCep(logradouro);

		logradouro.getBairro().getCidade().getUf().setSiglaUf(cepResponse.getUf());
		logradouro.getBairro().getCidade().setDescCidade(cepResponse.getCidade());
		logradouro.getBairro().setDescBairro(cepResponse.getBairro());
		logradouro.getTipoLogradouro().setDescTipoLogradouro(cepResponse.getTipoLogradouro());
		logradouro.setLogradouro(cepResponse.getLogradouro());

		return logradouro;

	}

	public LocationDTO pegarGeolocalizacao(Logradouro logradouro) throws Exception {
		return this.webService.pesquisarCepGoogle(logradouro);

	}

	public void pegarCepLatiLong(Logradouro logradouro) throws Exception {

		Element raiz = getWebService().pesquisarCepViaLongLati(logradouro);
		Element resultado;

		for (Iterator<?> status = raiz.elementIterator(); status.hasNext();) {
			resultado = (Element) status.next();

			if (resultado.getQualifiedName().equals("status") & "OK".equalsIgnoreCase(resultado.getTextTrim())) {
				for (Iterator<?> result = raiz.elementIterator(); result.hasNext();) {

					// TERMINAR ESTA BAGAÇA DEPOIS
				}

			}

		}

	}

	public void calcularDistancia(Distancia distancia) throws Exception {

		Element raiz = getWebService().calcularDistancia(distancia);

		for (Iterator<?> i = raiz.elementIterator(); i.hasNext();) {
			Element resultado = (Element) i.next();

			for (Iterator<?> iRow = resultado.elementIterator(); iRow.hasNext();) {
				Element row = (Element) iRow.next();

				for (Iterator<?> iElement = row.elementIterator(); iElement.hasNext();) {
					Element element = (Element) iElement.next();

					if (element.getQualifiedName().equals("duration")) {
						for (Iterator<?> iDuration = element.elementIterator(); iDuration.hasNext();) {
							Element duration = (Element) iDuration.next();
							if (duration.getQualifiedName().equals("value")) {
								distancia.setDuracao(Integer.parseInt(duration.getTextTrim()));
							}
						}
					}

					if (element.getQualifiedName().equals("distance")) {
						for (Iterator<?> iDistance = element.elementIterator(); iDistance.hasNext();) {
							Element distance = (Element) iDistance.next();
							if (distance.getQualifiedName().equals("value")) {
								distancia.setDistancia(Integer.parseInt(distance.getTextTrim()));
							}
						}
					}
				}
			}
		}
	}

	public WebServices getWebService() {
		return webService;
	}

	public void setWebService(WebServices webService) {
		this.webService = webService;
	}

}

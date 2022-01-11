package br.com.furafila.mvc.logradouro.service;

import java.util.List;

import br.com.furafila.mvc.logradouro.builder.NovoLogradouroDTOBuilder;
import br.com.furafila.mvc.logradouro.business.LogradouroBusiness;
import br.com.furafila.mvc.logradouro.dto.EnderecoCompletoDTO;
import br.com.furafila.mvc.logradouro.dto.NovoLogradouroDTO;
import br.com.furafila.mvc.logradouro.model.Logradouro;
import br.com.furafila.mvc.logradouro.service.impl.LogradouroApiServiceImpl;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LogradouroService {

	private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();

	private LogradouroApiService logradouroApiService = new LogradouroApiServiceImpl();

	public Logradouro buscarLogradouro(Logradouro logradouro) throws Exception {

		List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);

		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
			logradouro.setNroCep(Integer.parseInt(lstDados.get(0)));
			logradouro.setLogradouro(lstDados.get(1));
			logradouro.getTipoLogradouro().setDescTipoLogradouro(lstDados.get(2));
		} else {
			logradouro = new Logradouro();
		}

		return logradouro;

	}

	public boolean logradouroExiste(Logradouro logradouro) throws Exception {

		List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);

		return !FuraFilaUtils.listaVaziaNula(lstDados);

	}

	public Logradouro buscarEnderecoCompleto(Logradouro logradouro) throws Exception {

		EnderecoCompletoDTO enderecoCompletoDTO = logradouroApiService.buscarEnderecoCompleto(logradouro.getNroCep());

		logradouro.setNroCep(enderecoCompletoDTO.getPostalCode());
		logradouro.getTipoLogradouro().setDescTipoLogradouro(enderecoCompletoDTO.getAddressType());
		logradouro.setLogradouro(enderecoCompletoDTO.getAddress());
		logradouro.getBairro().setDescBairro(enderecoCompletoDTO.getDistrict());
		logradouro.getBairro().getCidade().setDescCidade(enderecoCompletoDTO.getCity());
		logradouro.getBairro().getCidade().getUf().setSiglaUf(enderecoCompletoDTO.getPostalAbbreviation());

		return logradouro;

	}

	public void gravarLogradouro(Logradouro logradouro) {

		NovoLogradouroDTO novoLogradouroDTO = new NovoLogradouroDTOBuilder().postalCode(logradouro.getNroCep())
				.address(logradouro.getLogradouro()).addressType(logradouro.getTipoLogradouro().getDescTipoLogradouro())
				.city(logradouro.getBairro().getCidade().getDescCidade())
				.postalAbbreviation(logradouro.getBairro().getCidade().getUf().getSiglaUf())
				.district(logradouro.getBairro().getDescBairro()).latitude(logradouro.getLatitude())
				.longitude(logradouro.getLongitude()).build();

		logradouroApiService.gravarLogradouro(novoLogradouroDTO);

	}

	public LogradouroBusiness getLogradouroBusiness() {
		return logradouroBusiness;
	}

	public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
		this.logradouroBusiness = logradouroBusiness;
	}

}

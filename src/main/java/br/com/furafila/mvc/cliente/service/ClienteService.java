package br.com.furafila.mvc.cliente.service;

import java.util.List;

import br.com.furafila.mvc.cliente.builder.NovoClienteDTOBuilder;
import br.com.furafila.mvc.cliente.business.ClienteBusiness;
import br.com.furafila.mvc.cliente.dto.NovoClienteDTO;
import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.cliente.service.impl.ClienteApiServiceImpl;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ClienteService {

	private ClienteBusiness clienteBusiness = new ClienteBusiness();

	private ClienteApiService clienteApiService = new ClienteApiServiceImpl();

	public Cliente buscarDadosCliente(Cliente cliente) throws Exception {

		List<String> lstDados = getClienteBusiness().buscarDadosCliente(cliente);
		Integer indice = 0;

		Cliente c = new Cliente();
		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
			c.setIdCliente(Integer.parseInt(lstDados.get(indice++)));
			c.setNome(lstDados.get(indice++));
			c.setDataNascimentoSQL(lstDados.get(indice++));
			c.setCpf(Long.parseLong(lstDados.get(indice++)));
			c.setSexo(lstDados.get(indice++).charAt(0));
			c.setTelRes(Long.parseLong(lstDados.get(indice++)));
			c.setTelCom(Long.parseLong(lstDados.get(indice++)));
			c.setCelular(Long.parseLong(lstDados.get(indice++)));
			c.setEmail(lstDados.get(indice++));
			c.setComplemento(lstDados.get(indice++));
			c.setNroCasa(Integer.parseInt(lstDados.get(indice++)));
			c.setNroApto(Integer.parseInt(lstDados.get(indice++)));
			c.getLogradouro().setNroCep(Integer.parseInt(lstDados.get(indice++)));
			c.getLogradouro().getTipoLogradouro().setDescTipoLogradouro(lstDados.get(indice++));
			c.getLogradouro().setLogradouro(lstDados.get(indice++));
			c.getLogradouro().getBairro().setDescBairro(lstDados.get(indice++));
			c.getLogradouro().getBairro().getCidade().setDescCidade(lstDados.get(indice++));
			c.getLogradouro().getBairro().getCidade().getUf().setSiglaUf(lstDados.get(indice++));
			c.getLogin().setUsuario(lstDados.get(indice++));
		}

		return c;

	}

	public Cliente buscarDadosBasicosCliente(Cliente cliente) throws Exception {

		List<String> lstDados = getClienteBusiness().buscarDadosBasicosCliente(cliente);
		Integer indice = 0;

		Cliente c = new Cliente();

		if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
			c.setIdCliente(Integer.parseInt(lstDados.get(indice++)));
			c.setNome(lstDados.get(indice++));
			c.setDataNascimentoSQL(lstDados.get(indice++));
			c.setCpf(Long.parseLong(lstDados.get(indice++)));
			c.setSexo(lstDados.get(indice++).charAt(0));
			c.setTelRes(Long.parseLong(lstDados.get(indice++)));
			c.setTelCom(Long.parseLong(lstDados.get(indice++)));
			c.setCelular(Long.parseLong(lstDados.get(indice++)));
			c.setEmail(lstDados.get(indice++));
			c.setComplemento(lstDados.get(indice++));
			c.setNroCasa(Integer.parseInt(lstDados.get(indice++)));
			c.setNroApto(Integer.parseInt(lstDados.get(indice++)));
			c.getLogradouro().setNroCep(Integer.parseInt(lstDados.get(indice++)));
			c.getLogradouro().getTipoLogradouro().setDescTipoLogradouro(lstDados.get(indice++));
			c.getLogradouro().setLogradouro(lstDados.get(indice++));
			c.getLogradouro().getBairro().setDescBairro(lstDados.get(indice++));
			c.getLogradouro().getBairro().getCidade().setDescCidade(lstDados.get(indice++));
			c.getLogradouro().getBairro().getCidade().getUf().setSiglaUf(lstDados.get(indice++));
			c.getLogin().setUsuario(lstDados.get(indice++));
		}

		return c;

	}

	public void gravar(Cliente cliente) {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().name(cliente.getNome())
				.birthday(cliente.getDataNascimento()).cpf(cliente.getCpf()).gender(cliente.getSexo())
				.homePhone(cliente.getTelRes()).commercialPhone(cliente.getTelCom()).mobilePhone(cliente.getCelular())
				.email(cliente.getEmail()).addressMoreInfo(cliente.getComplemento()).houseNumber(cliente.getNroCasa())
				.doorNumber(cliente.getNroApto()).postalCode(cliente.getLogradouro().getNroCep())
				.idLogin(cliente.getLogin().getIdLogin()).idImage(cliente.getImagem().getIdImagem()).build();

		clienteApiService.gravar(novoClienteDTO);

	}

	public ClienteBusiness getClienteBusiness() {
		return clienteBusiness;
	}

	public void setClienteBusiness(ClienteBusiness clienteBusiness) {
		this.clienteBusiness = clienteBusiness;
	}

}

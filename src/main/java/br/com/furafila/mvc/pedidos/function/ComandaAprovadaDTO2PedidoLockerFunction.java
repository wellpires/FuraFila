package br.com.furafila.mvc.pedidos.function;

import java.util.function.Function;

import br.com.furafila.mvc.pedidoLocker.model.PedidoLocker;
import br.com.furafila.mvc.pedidos.dto.ComandaAprovadaDTO;

public class ComandaAprovadaDTO2PedidoLockerFunction implements Function<ComandaAprovadaDTO, PedidoLocker> {

	@Override
	public PedidoLocker apply(ComandaAprovadaDTO comandaAprovadaDTO) {

		PedidoLocker pedidoLocker = new PedidoLocker();

		pedidoLocker.getPedidos().getComanda().setIdComanda(comandaAprovadaDTO.getBucketId().toString());
		pedidoLocker.getPedidos().getComanda().setCodigoPedido(comandaAprovadaDTO.getNumber());
		pedidoLocker.getPedidos().getComanda().getStatus().setIdStatus(comandaAprovadaDTO.getStatusId().intValue());
		pedidoLocker.getPedidos().getComanda().getStatus().setStatus(comandaAprovadaDTO.getStatus());
		pedidoLocker.getPedidos().getComanda().getEstabelecimento()
				.setIdEstabelecimento(comandaAprovadaDTO.getEstablishmentId().intValue());
		pedidoLocker.getPedidos().getComanda().getEstabelecimento()
				.setRazaoSocial(comandaAprovadaDTO.getCorporateName());
		pedidoLocker.getPedidos().getComanda().getEstabelecimento()
				.setCnpj(Long.parseLong(comandaAprovadaDTO.getCnpj()));
		pedidoLocker.getPedidos().getComanda().getCliente().setNome(comandaAprovadaDTO.getCustomerName());
		pedidoLocker.getPedidos().getComanda().getCliente().setEmail(comandaAprovadaDTO.getCustomerEmail());

		pedidoLocker.getLocker().getConjuntoLocker().setConjuntoLockerDesc(comandaAprovadaDTO.getLockerGroupName());
		pedidoLocker.getLocker().setIdLocker(comandaAprovadaDTO.getLockerId().intValue());
		pedidoLocker.getLocker().setLockerDesc(comandaAprovadaDTO.getLockerName());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getTipoLogradouro()
				.setDescTipoLogradouro(comandaAprovadaDTO.getAddressTypeName());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro()
				.setNroCep(comandaAprovadaDTO.getPostalCode().intValue());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().setLogradouro(comandaAprovadaDTO.getAddressName());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().setLatitude(comandaAprovadaDTO.getLatitude());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().setLongitude(comandaAprovadaDTO.getLongitude());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getBairro()
				.setDescBairro(comandaAprovadaDTO.getDistrictName());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade()
				.setDescCidade(comandaAprovadaDTO.getCityName());
		pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade().getUf()
				.setSiglaUf(comandaAprovadaDTO.getPostalAbbreviation());

		return pedidoLocker;
	}

}

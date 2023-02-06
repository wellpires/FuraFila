package br.com.furafila.mvc.estabelecimento.function;

import java.util.function.Function;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;

public class EstabelecimentoDTO2EstabelecimentoFuncion implements Function<EstabelecimentoDTO, Estabelecimento> {

	@Override
	public Estabelecimento apply(EstabelecimentoDTO estabelecimentoDTO) {

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setIdEstabelecimento(estabelecimentoDTO.getId().intValue());
		estabelecimento.setRazaoSocial(estabelecimentoDTO.getCorporateName());
		estabelecimento.setCnpj(Long.parseLong(estabelecimentoDTO.getCnpj()));
		estabelecimento.setInscricaoEstadual(Long.parseLong(estabelecimentoDTO.getStateRegistration()));
		estabelecimento.setStatus(estabelecimentoDTO.getStatus());
		estabelecimento.setEmail(estabelecimentoDTO.getEmail());
		return estabelecimento;
	}

}

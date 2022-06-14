package br.com.furafila.mvc.estabelecimentoLogin.function;

import java.util.function.Function;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoUsuarioDTO;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;

public class EstabelecimentoUsuarioDTO2EstabelecimentoLoginFunction
		implements Function<EstabelecimentoUsuarioDTO, EstabelecimentoLogin> {

	@Override
	public EstabelecimentoLogin apply(EstabelecimentoUsuarioDTO estabelecimentoUsuarioDTO) {
		EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
		estabelecimentoLogin.getLogin().setIdLogin(estabelecimentoUsuarioDTO.getUserId());
		estabelecimentoLogin.getLogin().setUsuario(estabelecimentoUsuarioDTO.getUsername());
		return estabelecimentoLogin;
	}

}

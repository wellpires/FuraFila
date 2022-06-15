package br.com.furafila.mvc.estabelecimentoLogin.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoUsuarioDTO;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoApiService;
import br.com.furafila.mvc.estabelecimento.service.impl.EstabelecimentoApiServiceImpl;
import br.com.furafila.mvc.estabelecimentoLogin.function.EstabelecimentoUsuarioDTO2EstabelecimentoLoginFunction;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoLoginService {

	private EstabelecimentoApiService estabelecimentoApiService = new EstabelecimentoApiServiceImpl();

	public List<EstabelecimentoLogin> listarUsuarios(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

		List<EstabelecimentoUsuarioDTO> estabelecimentoUsuarios = estabelecimentoApiService.listarUsuarios(
				estabelecimentoLogin.getEstabelecimento().getIdEstabelecimento(),
				estabelecimentoLogin.getLogin().getIdLogin());

		return estabelecimentoUsuarios.stream().map(new EstabelecimentoUsuarioDTO2EstabelecimentoLoginFunction())
				.collect(Collectors.toList());

	}

	public void deletarEstabelecimentoUsuario(EstabelecimentoLogin estabelecimentoLogin) {
		estabelecimentoApiService.deletarEstabelecimentoUsuario(estabelecimentoLogin.getLogin().getIdLogin());
	}

}

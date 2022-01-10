package br.com.furafila.mvc.login.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.login.builder.NovoLoginDTOBuilder;
import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.dto.EntregadorDTO;
import br.com.furafila.mvc.login.dto.NovoLoginDTO;
import br.com.furafila.mvc.login.model.Login;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LoginService implements ILoginService {

	private LoginApiService loginApiService = new LoginApiService();

	@Override
	public Login logarSe(Login login) throws Exception {

		CredenciaisDTO credenciaisDTO = loginApiService.autenticarLogin(login.getUsuario(),
				login.getSenhaCriptografada());

		Login newLogin = new Login();
		newLogin.setIdLogin(credenciaisDTO.getId().intValue());
		newLogin.setUsuario(credenciaisDTO.getUsername());
		newLogin.setSenha(credenciaisDTO.getPassword());
		newLogin.getPermissao().setIdPermissao(credenciaisDTO.getLevelId().intValue());
		newLogin.getPermissao().setSiglaPermissao(credenciaisDTO.getInitials());
		newLogin.getPermissao().setPermissao(credenciaisDTO.getLevel());

		return newLogin;

	}

	@Override
	public boolean verificarDuplicidade(Login login, boolean isAlteracao) throws Exception {
		return loginApiService.verificarDuplicidade(login.getIdLogin(), login.getUsuario(), isAlteracao);
	}

	@Override
	public List<Login> listarEntregador() throws Exception {

		List<EntregadorDTO> entregadores = loginApiService.listarEntregadores();

		List<Login> lstEntregadores = new ArrayList<>();
		for (EntregadorDTO entregador : entregadores) {

			Login login = new Login();
			login.setIdLogin(entregador.getId().intValue());
			login.setUsuario(entregador.getUsername());
			login.setStatus(entregador.getStatus());
			login.setDisponivelEntrega(entregador.getDeliveryAvailable());

			lstEntregadores.add(login);
		}

		return lstEntregadores;

	}

	@Override
	public Long gravarLogin(Login login) throws Exception {

		NovoLoginDTO novoLoginDTO = new NovoLoginDTOBuilder().username(login.getUsuario()).password(login.getSenhaCriptografada())
				.status(login.getStatus()).deliveryAvailable(login.getDisponivelEntrega())
				.levelId(login.getPermissao().getIdPermissao()).build();

		return this.loginApiService.gravarLogin(novoLoginDTO);
	}

}

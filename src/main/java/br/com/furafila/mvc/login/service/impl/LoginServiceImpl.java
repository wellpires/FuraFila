package br.com.furafila.mvc.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.login.builder.EditarLoginDTOBuilder;
import br.com.furafila.mvc.login.builder.NovoLoginDTOBuilder;
import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.dto.EditarLoginDTO;
import br.com.furafila.mvc.login.dto.EntregadorDTO;
import br.com.furafila.mvc.login.dto.NovoLoginDTO;
import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.mvc.login.service.LoginService;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LoginServiceImpl implements LoginService {

	private LoginApiService loginApiService = new LoginApiServiceImpl();

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
	public int gravarLogin(Login login) throws Exception {

		NovoLoginDTO novoLoginDTO = new NovoLoginDTOBuilder().username(login.getUsuario())
				.password(login.getSenhaCriptografada()).status(login.getStatus())
				.deliveryAvailable(login.getDisponivelEntrega()).levelId(login.getPermissao().getIdPermissao()).build();

		return this.loginApiService.gravarLogin(novoLoginDTO).intValue();
	}

	@Override
	public void alterar(Login login) throws Exception {

		EditarLoginDTO editarLoginDTO = new EditarLoginDTOBuilder().username(login.getUsuario())
				.password(login.getSenhaCriptografada()).build();

		this.loginApiService.alterar(login.getIdLogin(), editarLoginDTO);

	}

	@Override
	public void deletar(Login login) {
		this.loginApiService.deletar(login.getIdLogin());
	}

	@Override
	public void alterarStatus(Login login) {
		this.loginApiService.alterarStatus(login.getIdLogin());

	}

	@Override
	public void alterarDisponibilidade(Login login) {
		this.loginApiService.alterarDisponibilidade(login.getIdLogin());

	}

}
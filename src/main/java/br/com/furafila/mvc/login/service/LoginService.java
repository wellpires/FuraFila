package br.com.furafila.mvc.login.service;

import java.util.ArrayList;
import java.util.List;

import br.com.furafila.mvc.login.business.LoginBusiness;
import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.utils.FuraFilaConstants;
import br.com.furafila.utils.FuraFilaUtils;
import br.com.furafila.utils.WebServices;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LoginService implements ILoginService {

	private LoginBusiness loginBusiness = new LoginBusiness();

	private WebServices webService = new WebServices();

	@Override
	public Login logarSe(Login login) throws Exception {

		CredenciaisDTO credenciaisDTO = webService.autenticarLogin(login.getUsuario(), login.getSenhaCriptografada());

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
	public int verificarDuplicidade(Login login, boolean isAlteracao) throws Exception {
		return getLoginBusiness().obterUsuario(login, isAlteracao).size();
	}

	@Override
	public List<Login> listarEntregador() throws Exception {

		List<List<String>> lstDados = getLoginBusiness().listarEntregadores();
		List<Login> lstEntregadores = new ArrayList<>();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
			for (List<String> lstValores : lstDados) {

				int index = 0;
				Login login = new Login();
				login.setIdLogin(Integer.parseInt(lstValores.get(index++)));
				login.setUsuario(lstValores.get(index++));
				login.setStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
				login.setDisponivelEntrega(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);

				lstEntregadores.add(login);
			}
		}

		return lstEntregadores;

	}

	public LoginBusiness getLoginBusiness() {
		return loginBusiness;
	}

	public void setLoginBusiness(LoginBusiness loginBusiness) {
		this.loginBusiness = loginBusiness;
	}

}

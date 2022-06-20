package br.com.furafila.mvc.login.business;

import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class LoginBusiness {

	public void alterar(Login login) throws Exception {

		String strQuery = "UPDATE " + "LOGIN " + "SET " + "usuario = '" + login.getUsuario() + "', " + "senha = '"
				+ login.getSenhaCriptografada() + "' " + "WHERE id_login = " + login.getIdLogin();

		BancoDados.executaComando(strQuery);
	}

}

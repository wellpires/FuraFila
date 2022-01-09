package br.com.furafila.mvc.login.business;

import br.com.furafila.mvc.login.model.Login;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class LoginBusiness {

	public int gravar(Login login) throws Exception {

		String strQuery = "INSERT INTO LOGIN usuario, senha, status, disponivel_entrega, id_permissao_FK) VALUES ("
				+ "'" + login.getUsuario() + "', '" + login.getSenhaCriptografada() + "'," + login.getStatusSQL() + ","
				+ login.getDisponivel_entregaSQL() + ", '" + login.getPermissao().getIdPermissao()
				+ "') RETURNING id_login";

		return BancoDados.inserirRetornaID(strQuery);

	}

	public void alterar(Login login) throws Exception {

		String strQuery = "UPDATE " + "LOGIN " + "SET " + "usuario = '" + login.getUsuario() + "', " + "senha = '"
				+ login.getSenhaCriptografada() + "' " + "WHERE id_login = " + login.getIdLogin();

		BancoDados.executaComando(strQuery);
	}

	public void excluir(Login login) throws Exception {

		String strQuery = "DELETE FROM LOGIN WHERE id_login = " + login.getIdLogin();

		BancoDados.executaComando(strQuery);

	}

	public void alterarStatus(Login login) throws Exception {

		String strQuery = "UPDATE LOGIN SET status = " + login.getStatusSQL() + " WHERE id_login = "
				+ login.getIdLogin();
		BancoDados.executaComando(strQuery);

	}

	public void alterarDisponibilidade(Login login) throws Exception {

		String strQuery = "UPDATE LOGIN SET disponivel_entrega = " + login.getDisponivel_entregaSQL()
				+ " WHERE id_login = " + login.getIdLogin();
		BancoDados.executaComando(strQuery);

	}

}

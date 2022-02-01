
package br.com.furafila.mvc.login.service;

import java.util.List;

import br.com.furafila.mvc.login.model.Login;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface ILoginService {

	public Login logarSe(Login login) throws Exception;

	public boolean verificarDuplicidade(Login login, boolean isAlteracao) throws Exception;

	public List<Login> listarEntregador() throws Exception;

	public int gravarLogin(Login login) throws Exception;

}

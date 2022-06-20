package br.com.furafila.mvc.login.service.impl;

import java.util.List;

import br.com.furafila.mvc.login.dto.CredenciaisDTO;
import br.com.furafila.mvc.login.dto.EditarLoginDTO;
import br.com.furafila.mvc.login.dto.EntregadorDTO;
import br.com.furafila.mvc.login.dto.NovoLoginDTO;

public interface LoginApiService {

	CredenciaisDTO autenticarLogin(String usuario, String senha);

	boolean verificarDuplicidade(Integer idLogin, String usuario, boolean isAlteracao);

	List<EntregadorDTO> listarEntregadores();

	Long gravarLogin(NovoLoginDTO novoLoginDTO);

	void alterar(Integer idLogin, EditarLoginDTO editarLoginDTO);

	void deletar(Integer idLogin);

	void alterarStatus(Integer idLogin);

	void alterarDisponibilidade(Integer idLogin);

}
package br.com.furafila.mvc.logradouro.service;

import br.com.furafila.mvc.logradouro.dto.EnderecoCompletoDTO;
import br.com.furafila.mvc.logradouro.dto.NovoLogradouroDTO;

public interface LogradouroApiService {

	void gravarLogradouro(NovoLogradouroDTO novoLogradouroDTO);

	EnderecoCompletoDTO buscarEnderecoCompleto(Integer nroCep);

}

package br.com.furafila.mvc.estabelecimento.service;

import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.login.model.Login;

public interface EstabelecimentoApiService {

	void gravar(NovoEstabelecimentoDTO novoEstabelecimentoDTO);

	EstabelecimentoInformacoesIniciaisDTO buscarInformacoesIniciaisEstabelecimento(Login login);

}

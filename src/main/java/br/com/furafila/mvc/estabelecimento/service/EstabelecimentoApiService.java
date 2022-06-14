package br.com.furafila.mvc.estabelecimento.service;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.dto.EditarEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoUsuarioDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoUsuarioEstabelecimentoDTO;

public interface EstabelecimentoApiService {

	void gravar(NovoEstabelecimentoDTO novoEstabelecimentoDTO);

	EstabelecimentoInformacoesIniciaisDTO buscarInformacoesIniciaisEstabelecimento(Integer idLogin);

	EstabelecimentoDTO buscarEstabelecimento(Integer idEstabelecimento);

	void editarEstabelecimento(EditarEstabelecimentoDTO editarEstabelecimentoDTO, Integer estabelecimentoId);

	void adicionarUsuarioEstabelecimento(NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO);

	List<EstabelecimentoUsuarioDTO> listarUsuarios(Integer idEstabelecimento, Integer idLogin);

}

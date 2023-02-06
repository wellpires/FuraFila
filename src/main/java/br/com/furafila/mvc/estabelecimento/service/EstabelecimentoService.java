package br.com.furafila.mvc.estabelecimento.service;

import java.util.List;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.pedidos.model.Pedidos;

public interface EstabelecimentoService {

	List<Estabelecimento> listarEstabelecimentos() throws Exception;

	Estabelecimento buscarInformacaoEstabelecimento(Integer idEstabelecimento) throws Exception;

	List<Pedidos> listarEstabelecimentoMaisVendem() throws Exception;

	void gravar(Estabelecimento estabelecimento);

	Estabelecimento buscarInformacoesIniciaisEstabelecimento(EstabelecimentoLogin estabelecimentoLogin);

	void alterar(Estabelecimento estabelecimento);

	int verificarDuplicidadeRazaoSocial(Estabelecimento estabelecimento) throws Exception;

	int verificarDuplicidadeEmail(Estabelecimento estabelecimento) throws Exception;

	int verificarDuplicidadeCnpj(Estabelecimento estabelecimento) throws Exception;

	int verificarDuplicidadeInscricaoEstadual(Estabelecimento estabelecimento) throws Exception;

	void adicionarUsuarioEstabelecimento(EstabelecimentoLogin estabelecimentoLogin);

	void alterarStatus(Estabelecimento estabelecimento);


}

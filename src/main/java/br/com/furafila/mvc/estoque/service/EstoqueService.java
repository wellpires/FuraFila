package br.com.furafila.mvc.estoque.service;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.estoqueEntrada.model.EstoqueEntrada;
import br.com.furafila.mvc.estoqueSaida.model.EstoqueSaida;

public interface EstoqueService {

	boolean estoqueExiste(Estoque estoque) throws Exception;

	void buscarCodigoEstoque(Estoque estoque) throws Exception;

	void gravarEstoqueSaida(EstoqueSaida es);

	void gravarEstoqueEntrada(EstoqueEntrada estoqueEntrada, Estabelecimento estabelecimento);

	void criarEstoque(Estoque estoque);

}
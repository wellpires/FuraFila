package br.com.furafila.mvc.estoqueEntrada.service.impl;

import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.produto.model.Produto;

public interface EstoqueEntradaService {

	void gravar(Produto produtos, Estabelecimento estabelecimento);

}

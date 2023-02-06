package br.com.furafila.mvc.estoque.business;

import java.util.List;

import br.com.furafila.mvc.estoque.model.Estoque;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueBusiness {

	public List<String> buscarCodigoEstoque(Estoque estoque) throws Exception {

		String strQuery = "SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = "
				+ estoque.getEstabelecimento().getIdEstabelecimento();

		return BancoDados.retornaRegistro(strQuery);

	}

}

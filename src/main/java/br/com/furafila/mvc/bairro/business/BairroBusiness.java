package br.com.furafila.mvc.bairro.business;

import java.io.Serializable;
import java.util.List;

import br.com.furafila.mvc.bairro.model.Bairro;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class BairroBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	public int gravar(Bairro bairro) throws Exception {

		String strQuery = "INSERT INTO BAIRRO " + "(" + "desc_bairro," + "id_cidade_FK)" + " VALUES " + "('"
				+ bairro.getDescBairro() + "'" + "," + bairro.getCidade().getIdCidade() + ")";

		BancoDados.executaComando(strQuery);

		List<String> bairroCadastrado = BancoDados.retornaRegistro(String.format(
				"select b.id_bairro from bairro b where b.desc_bairro = '%s' and b.id_cidade_fk = %d limit 1;",
				bairro.getDescBairro(), bairro.getCidade().getIdCidade()));

		return Integer.parseInt(bairroCadastrado.get(0));

	}

	public List<String> buscarBairro(Bairro bairro) throws Exception {

		String strQuery = "SELECT B.id_bairro AS [CD],  B.desc_bairro AS [BAIRRO] "
				+ "FROM BAIRRO B WHERE B.desc_bairro LIKE '" + bairro.getDescBairro() + "'";

		return BancoDados.retornaRegistro(strQuery);

	}

}

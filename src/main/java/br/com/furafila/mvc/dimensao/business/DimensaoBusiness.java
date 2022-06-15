package br.com.furafila.mvc.dimensao.business;

import java.io.Serializable;

import br.com.furafila.mvc.dimensao.model.Dimensao;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class DimensaoBusiness implements Serializable {

	private static final long serialVersionUID = -1544375948677077177L;

	public void alterar(Dimensao dimensao) throws Exception {

		String strQuery = "UPDATE DIMENSAO SET altura = " + dimensao.getAltura() + ",  largura =  "
				+ dimensao.getLargura() + ",  profundidade = " + dimensao.getProfundidade() + " WHERE id_dimensao = "
				+ dimensao.getIdDimensao();

		BancoDados.executaComando(strQuery);

	}

}

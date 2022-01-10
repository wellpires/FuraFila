package br.com.furafila.mvc.imagem.business;

import java.io.File;

import org.apache.commons.io.FileUtils;

import br.com.furafila.mvc.imagem.model.Imagem;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ImagemBusiness {

	public void gravar(Imagem imagem) throws Exception {
		imagem.setIdImagem(BancoDados.inserirImagem(imagem.getImagem()));
	}

	public void alterar(Imagem imagem) throws Exception {
		BancoDados.alterarImagem(imagem.getImagem(), imagem.getIdImagem());
	}

	public File buscarImagemPorId(Integer idImagem) throws Exception {

		String strQuery = String.format("select i.imagem from imagem i where i.id_imagem = %d", idImagem);

		byte[] imagem = BancoDados.retornaImagem(strQuery);
		File file = new File(String.format("/home/wellington/imagens_fura_fila/%d.jpg", System.currentTimeMillis()));
		FileUtils.writeByteArrayToFile(file, imagem);

		return file;
	}

}

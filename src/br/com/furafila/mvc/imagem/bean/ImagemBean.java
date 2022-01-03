package br.com.furafila.mvc.imagem.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.furafila.mvc.imagem.model.Imagem;

/**
 *
 * @author Wellington
 */
@ManagedBean
@SessionScoped
public class ImagemBean implements Serializable {

	private static final long serialVersionUID = -6555445218593648839L;

	private UploadedFile uploadedFile;
	private Imagem imagem = new Imagem();
	private StreamedContent imagemVisualizar_2;

	public void carregarTeste(ActionEvent ae) {
		String imageFile = "/home/wellington/imagens_fura_fila/".concat(uploadedFile.getFileName());
		try {
			FileUtils.writeByteArrayToFile(new File(imageFile), uploadedFile.getContents());
		} catch (IOException e) {
			e.printStackTrace();
		}

		imagem.setImagem(imageFile);

	}

	public StreamedContent getImagemVisualizar_2() {
		return imagemVisualizar_2;
	}

	public void setImagemVisualizar_2(StreamedContent imagemVisualizar_2) {
		this.imagemVisualizar_2 = imagemVisualizar_2;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}

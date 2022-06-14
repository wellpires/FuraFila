package br.com.furafila.mvc.cliente.service.impl;

import java.io.File;
import java.io.IOException;

import br.com.furafila.mvc.cliente.dto.EditarImagemDTO;
import br.com.furafila.mvc.cliente.service.ImagemApiService;
import br.com.furafila.mvc.cliente.service.ImagemService;
import br.com.furafila.mvc.imagem.dto.NovaImagemDTO;
import br.com.furafila.mvc.imagem.model.Imagem;

public class ImagemServiceImpl implements ImagemService {

	private ImagemApiService imagemApiService = new ImagemApiServiceImpl();

	@Override
	public Long gravar(Imagem imagem) throws IOException {

		NovaImagemDTO novaImagemDTO = new NovaImagemDTO();
		novaImagemDTO.setImagem(new File(imagem.getImagem()));

		return imagemApiService.gravar(novaImagemDTO);
	}

	@Override
	public File buscarImagem(Long idImage) {
		return imagemApiService.buscarImagem(idImage);
	}

	@Override
	public void alterar(Imagem imagem) {

		EditarImagemDTO editarImagemDTO = new EditarImagemDTO();
		editarImagemDTO.setImagem(new File(imagem.getImagem()));

		imagemApiService.alterar(editarImagemDTO, imagem.getIdImagem());
	}

}

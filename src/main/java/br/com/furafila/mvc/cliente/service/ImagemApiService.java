package br.com.furafila.mvc.cliente.service;

import java.io.File;

import br.com.furafila.mvc.imagem.dto.NovaImagemDTO;

public interface ImagemApiService {

	Long gravar(NovaImagemDTO novaImagemDTO);

	File buscarImagem(Long idImage);

}

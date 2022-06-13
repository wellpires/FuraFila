package br.com.furafila.mvc.cliente.service;

import java.io.File;
import java.io.IOException;

import br.com.furafila.mvc.imagem.model.Imagem;

public interface ImagemService {

	Long gravar(Imagem imagem) throws IOException;

	File buscarImagem(Long idImage);

}

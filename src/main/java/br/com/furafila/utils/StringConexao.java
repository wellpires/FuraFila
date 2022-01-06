package br.com.furafila.utils;

/*
 * @Author: Wellington Gonçalves Pires
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringConexao {

	private static final Logger logger = LogManager.getLogger(StringConexao.class);

	private String server;
	private String nomeBancoDados;
	private String usuario;
	private String senha;
	private String caminho;

	public void lerBancoDados() throws Exception, FileNotFoundException, IOException {

		try {

			Properties propriedades = new Properties();

			InputStream projetoCaminho = getClass().getResourceAsStream(FuraFilaConstants.NOME_ARQUIVO_BANCO_DADOS);
			
			propriedades.load(projetoCaminho);

			nomeBancoDados = propriedades.getProperty("banco");
			usuario = propriedades.getProperty("usuario");
			senha = propriedades.getProperty("senha");
			server = propriedades.getProperty("servidor");

			caminho = String.format("jdbc:postgresql://%s:%s/%s?ssl=true", server, "5432", nomeBancoDados);
		} catch (IOException ioEx) {
			logger.error(ioEx.getMessage(), ioEx);
			throw ioEx;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}

	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getNomeBancoDados() {
		return nomeBancoDados;
	}

	public void setNomeBancoDados(String nomeBancoDados) {
		this.nomeBancoDados = nomeBancoDados;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}

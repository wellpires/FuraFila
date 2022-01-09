package br.com.furafila.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringConexao {

	private static final Logger logger = LogManager.getLogger(StringConexao.class);

	private final String usuario;
	private final String senha;
	private final String host;
	private final String bancoDados;
	private final String caminho;

	public StringConexao() {
		try {
			host = System.getenv("JDBC_DATABASE_HOST");
			bancoDados = System.getenv("JDBC_DATABASE_NAME");
			usuario = System.getenv("JDBC_DATABASE_USERNAME");
			senha = System.getenv("JDBC_DATABASE_PASSWORD");

			caminho = String.format("jdbc:postgresql://%s:5432/%s", host, bancoDados);
//			caminho = "jdbc:postgresql://localhost:5432/fura_fila";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}

	}

	public String getHost() {
		return host;
	}

	public String getBancoDados() {
		return bancoDados;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public String getCaminho() {
		return caminho;
	}

}

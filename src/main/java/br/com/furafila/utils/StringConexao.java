package br.com.furafila.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringConexao {

	private static final Logger logger = LogManager.getLogger(StringConexao.class);

	private final String usuario;
	private final String senha;
	private final String caminho;

	public StringConexao() {
		try {

			usuario = System.getenv("JDBC_DATABASE_USERNAME");
			senha = System.getenv("JDBC_DATABASE_PASSWORD");

			caminho = "jdbc:postgresql://ec2-34-239-196-254.compute-1.amazonaws.com:5432/dc9dta6b8a4aek?sslmode=require";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}

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

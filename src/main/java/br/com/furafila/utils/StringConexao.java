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
			
			logger.info("Usuario: {}, Senha: {}", usuario, senha);
			
//			"jdbc:postgresql://ec2-34-239-196-254.compute-1.amazonaws.com:5432/dc9dta6b8a4aek?password=dd7182203eb54aae8fa2da8f0446ce3fbf8d17e65cf3dcd8b1f3ca9acee42128&sslmode=require&user=pcbzndywvfentf";

			caminho = String.format(
					"jdbc:postgresql://ec2-34-239-196-254.compute-1.amazonaws.com:5432/dc9dta6b8a4aek?password=%s&sslmode=require&user=%s");
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

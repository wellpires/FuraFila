package br.com.furafila.mvc.produto.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProdutoServerApiException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(ProdutoServerApiException.class);

	private static final long serialVersionUID = 1237388420127730595L;

	public ProdutoServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

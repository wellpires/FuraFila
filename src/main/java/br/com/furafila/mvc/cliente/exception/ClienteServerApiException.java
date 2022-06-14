package br.com.furafila.mvc.cliente.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClienteServerApiException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(ClienteServerApiException.class);

	private static final long serialVersionUID = 2140600281494667928L;

	public ClienteServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());

	}

}

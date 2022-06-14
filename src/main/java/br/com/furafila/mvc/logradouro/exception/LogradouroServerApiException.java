package br.com.furafila.mvc.logradouro.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogradouroServerApiException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(LogradouroServerApiException.class);

	private static final long serialVersionUID = 7922572538974298993L;

	public LogradouroServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

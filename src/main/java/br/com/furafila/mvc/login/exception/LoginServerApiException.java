package br.com.furafila.mvc.login.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginServerApiException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(LoginServerApiException.class);

	private static final long serialVersionUID = 1056803226557332385L;

	public LoginServerApiException(String statusMessage) {
		super(statusMessage);
	}

	public LoginServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));

		logger.error(this.getMessage());
	}

}

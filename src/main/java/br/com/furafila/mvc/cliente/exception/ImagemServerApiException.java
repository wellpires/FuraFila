package br.com.furafila.mvc.cliente.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImagemServerApiException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(ImagemServerApiException.class);

	private static final long serialVersionUID = 4316979061489536168L;

	public ImagemServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

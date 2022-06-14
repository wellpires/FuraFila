package br.com.furafila.mvc.estabelecimento.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EstabelecimentoServerApiException extends RuntimeException {

	private static final long serialVersionUID = -3251187328227171570L;

	private static final Logger logger = LogManager.getLogger(EstabelecimentoServerApiException.class);

	public EstabelecimentoServerApiException(String statusMessage) {
		super(statusMessage);
	}

	public EstabelecimentoServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

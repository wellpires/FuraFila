package br.com.furafila.mvc.estoque.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.estabelecimento.exception.EstabelecimentoServerApiException;

public class EstoqueSaidaServerApiException extends RuntimeException {

	private static final long serialVersionUID = 6483443905781228486L;

	private static final Logger logger = LogManager.getLogger(EstabelecimentoServerApiException.class);

	public EstoqueSaidaServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

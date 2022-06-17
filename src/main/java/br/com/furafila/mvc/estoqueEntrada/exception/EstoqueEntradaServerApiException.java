package br.com.furafila.mvc.estoqueEntrada.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.estabelecimento.exception.EstabelecimentoServerApiException;

public class EstoqueEntradaServerApiException extends RuntimeException {

	private static final long serialVersionUID = 8246170818465755416L;

	private static final Logger logger = LogManager.getLogger(EstabelecimentoServerApiException.class);

	public EstoqueEntradaServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

package br.com.furafila.mvc.pedidos.exception;

import javax.ws.rs.core.Response.StatusType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.mvc.estabelecimento.exception.EstabelecimentoServerApiException;

public class PedidoServerApiException extends RuntimeException {

	private static final long serialVersionUID = -3183215652730359715L;

	private static final Logger logger = LogManager.getLogger(EstabelecimentoServerApiException.class);

	public PedidoServerApiException(StatusType statusInfo) {
		super(String.format("%d - %s", statusInfo.getStatusCode(), statusInfo.getReasonPhrase()));
		logger.error(this.getMessage());
	}

}

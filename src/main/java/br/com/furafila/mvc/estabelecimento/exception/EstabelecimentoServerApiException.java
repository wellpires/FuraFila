package br.com.furafila.mvc.estabelecimento.exception;

public class EstabelecimentoServerApiException extends RuntimeException {

	private static final long serialVersionUID = -3251187328227171570L;

	public EstabelecimentoServerApiException(String statusMessage) {
		super(statusMessage);
	}

}

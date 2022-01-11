package br.com.furafila.mvc.logradouro.exception;

public class LogradouroServerApiException extends RuntimeException {

	private static final long serialVersionUID = 7922572538974298993L;

	public LogradouroServerApiException(String statusMessage) {
		super(statusMessage);
	}

}

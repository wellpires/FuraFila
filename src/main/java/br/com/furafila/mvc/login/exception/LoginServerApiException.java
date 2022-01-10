package br.com.furafila.mvc.login.exception;

public class LoginServerApiException extends RuntimeException {

	private static final long serialVersionUID = 1056803226557332385L;

	public LoginServerApiException(int statusCode) {
		super("");
	}
	
}

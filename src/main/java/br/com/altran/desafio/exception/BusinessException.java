package br.com.altran.desafio.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -621363784695994474L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
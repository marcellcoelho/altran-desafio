package br.com.altran.desafio.exception;

/**
 * The Class BusinessException.
 */
public class BusinessException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -621363784695994474L;

	/**
	 * Instantiates a new business exception.
	 *
	 * @param mensagem the mensagem
	 */
	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
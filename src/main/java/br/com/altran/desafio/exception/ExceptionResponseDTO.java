package br.com.altran.desafio.exception;

import java.util.Set;

/**
 * The Class ExceptionResponseDTO.
 */
public class ExceptionResponseDTO {

	/** The mensagem. */
	private Set<String> mensagem;

	/**
	 * Instantiates a new exception response DTO.
	 *
	 * @param mensagem the mensagem
	 */
	public ExceptionResponseDTO(Set<String> mensagem) {
		super();
		this.mensagem = mensagem;
	}

	/**
	 * Gets the mensagem.
	 *
	 * @return the mensagem
	 */
	public Set<String> getMensagem() {
		return mensagem;
	}

	/**
	 * Sets the mensagem.
	 *
	 * @param mensagem the new mensagem
	 */
	public void setMensagem(Set<String> mensagem) {
		this.mensagem = mensagem;
	}

}

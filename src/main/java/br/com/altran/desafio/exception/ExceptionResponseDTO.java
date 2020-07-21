package br.com.altran.desafio.exception;

import java.util.Set;

public class ExceptionResponseDTO {

	private Set<String> mensagem;

	public ExceptionResponseDTO(Set<String> mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public Set<String> getMensagem() {
		return mensagem;
	}

	public void setMensagem(Set<String> mensagem) {
		this.mensagem = mensagem;
	}

}

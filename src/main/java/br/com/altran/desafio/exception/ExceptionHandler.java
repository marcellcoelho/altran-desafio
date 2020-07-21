package br.com.altran.desafio.exception;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<ExceptionResponseDTO> handlerTransactionSystemException(Exception ex, WebRequest request) {
		Throwable cause = ((TransactionSystemException) ex).getRootCause();
		Set<String> mensagem = new HashSet<>();
		if (cause instanceof ConstraintViolationException) {
	        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
	        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
	        	mensagem.add(constraintViolation.getMessage());
			}
	    }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(new ExceptionResponseDTO(mensagem));
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ PropertyReferenceException.class })
	public ResponseEntity<ExceptionResponseDTO> handlerPropertyReferenceException(Exception ex, WebRequest request) {
		Set<String> mensagem = new HashSet<>();
		if (ex instanceof PropertyReferenceException) {
			PropertyReferenceException propertyReferenceException = ((PropertyReferenceException) ex);
			mensagem.add(MessageFormat.format("Propriedade {0} não foi encontrada.", propertyReferenceException.getPropertyName()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(new ExceptionResponseDTO(mensagem));
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ BusinessException.class })
	public ResponseEntity<ExceptionResponseDTO> handlerBusinessException(RuntimeException ex, WebRequest request) {
		Set<String> mensagem = new HashSet<>();
		if (ex instanceof BusinessException) {
	        mensagem.add(((BusinessException) ex).getMessage());
	    }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(new ExceptionResponseDTO(mensagem));
	}
	
	
	
}
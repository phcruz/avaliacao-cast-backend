package br.com.cast.avaliacao.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cast.avaliacao.exception.CustomException;

/**
 * Classe interceptadora de exceções
 * @author paulo
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> erroCustomizadoException(CustomException e, HttpServletRequest request) {
		return new ResponseEntity<Object>(e, new HttpHeaders(), e.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> erroCustomizadoException(Exception e, HttpServletRequest request) {
		return new ResponseEntity<Object>(e, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

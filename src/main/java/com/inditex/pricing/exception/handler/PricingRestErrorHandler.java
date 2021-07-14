package com.inditex.pricing.exception.handler;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLTransientConnectionException;
import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manejador de errores de respuestas REST.
 * 
 * @author Antonio Troyano
 *
 */
@Slf4j
@ControllerAdvice
public class PricingRestErrorHandler extends ResponseEntityExceptionHandler {

	/**
	 * Método manejador errores para elementos no encontrados.
	 *
	 * @param ex         Excepción a manejar {@link Exception}
	 * @param webRequest Solicitud al servicio
	 * @return Retorna una respuesta Http con status y body
	 */
	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {

		log.error("{}", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Controlar excepciones producidas por la conexión a base de datos.
	 *
	 * @param ex         Objeto de tipo {@link Exception}
	 * @param webRequest Objeto de tipo {@link WebRequest}
	 * @return Objeto de tipo {@link ResponseEntity}
	 */
	@ExceptionHandler(value = { SQLTransientConnectionException.class, CannotGetJdbcConnectionException.class,
			DataAccessResourceFailureException.class, CannotCreateTransactionException.class })
	public ResponseEntity<Object> handleSqlTransientConnectionException(Exception ex, WebRequest webRequest) {

		log.error("DB connection error: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Método manejador errores genéricos.
	 *
	 * @param ex         Excepción a manejar {@link Exception}
	 * @param webRequest Solicitud al servicio
	 * @return Retorna una respuesta Http con status y body
	 */
	@ExceptionHandler(value = { RuntimeException.class, Exception.class })
	public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest webRequest) {

		log.error("General error: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
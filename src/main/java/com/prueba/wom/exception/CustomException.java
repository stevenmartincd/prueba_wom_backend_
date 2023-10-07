package com.prueba.wom.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para gestionar errores específicos dentro de la aplicación.
 * Esta clase permite que las excepciones generadas en la aplicación contengan
 * un mensaje detallado y un código de estado HTTP específico.
 *
 * @author Steven Cuevas
 * @version 1.0
 * @since 10/2023
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    private HttpStatus status;

    /**
     * Constructor de la excepción.
     */
    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Obtiene el mensaje de la excepción.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Obtiene el código de estado HTTP de la excepción.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Obtiene el código de estado.
     */
    public HttpStatus getStatus() {
        return status;
    }
}

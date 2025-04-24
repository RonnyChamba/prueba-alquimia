package com.cursos.ec.testalquimia.exceptions;

import com.cursos.ec.testalquimia.messages.response.GenericRespDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ApiExceptionHandler {


    private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({GenericException.class})
    public ResponseEntity<GenericRespDTO<String>> httpGenericException(GenericException exception) {
        LOGGER.error("Error capturado:", exception);
        GenericRespDTO<String> body = GenericRespDTO.<String>builder()
                .status("ERROR")
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(body, HttpStatusCode.valueOf(exception.getErrorCode()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public GenericRespDTO
            <String> serverException(Exception exception) {
        LOGGER.error("Error capturado en el servidor:", exception);

        return GenericRespDTO.<String>
                        builder()
                .status("ERROR")
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<GenericRespDTO<HashMap<String, String>>> errorValidations(MethodArgumentNotValidException exception) {
        LOGGER.error("Error de validacion:", exception);

        var map = new HashMap<String, String>();
        exception.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        GenericRespDTO<HashMap<String, String>> body = GenericRespDTO.<HashMap<String, String>>builder()
                .status("ERROR")
                .data(map)
                .message("Error de validacion")
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

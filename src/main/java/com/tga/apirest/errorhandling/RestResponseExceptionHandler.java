package com.tga.apirest.errorhandling;

import com.tga.apirest.errorhandling.model.ErrorBody;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class RestResponseExceptionHandler
        extends ResponseEntityExceptionHandler {

    final private static HttpHeaders httpHeaders;

    static {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<ErrorBody> handleBadRequestException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje(ex.getCause().getMessage()).build(),
                httpHeaders,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorBody> handleAnyOtherException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                ErrorBody.builder().mensaje(ex.getMessage()).build(),
                httpHeaders,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

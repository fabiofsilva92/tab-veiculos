package com.itau.desafio.framework.exceptions;


import com.itau.desafio.framework.adapter.in.dtos.ErrosDetalhes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {


    @ExceptionHandler({VeiculosException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleVeiculosException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrosDetalhes detalhesDoErro = new ErrosDetalhes(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhesDoErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<?> handleConnectException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrosDetalhes detalhesDoErro = new ErrosDetalhes(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhesDoErro, HttpStatus.SERVICE_UNAVAILABLE);
    }

}

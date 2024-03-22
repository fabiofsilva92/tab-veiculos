package com.itau.desafio.framework.exceptions;


import com.itau.desafio.framework.adapter.in.dtos.ErrosDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(VeiculosException.class)
    public ResponseEntity<?> handleVeiculosException(VeiculosException ex, org.springframework.web.context.request.WebRequest request) {
        ErrosDetalhes detalhesDoErro = new ErrosDetalhes(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhesDoErro, HttpStatus.BAD_REQUEST);
    }
}

package com.itau.desafio.framework.adapter.exceptions;

import com.itau.desafio.framework.exceptions.CustomExceptionHandler;
import com.itau.desafio.framework.exceptions.VeiculosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomExceptionHandlerTest {

    private CustomExceptionHandler exceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        exceptionHandler = new CustomExceptionHandler();
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void handleVeiculosException_deveRetornarBadRequest() {
        Exception ex = new VeiculosException("Erro específico de veículos");

        ResponseEntity<?> response = exceptionHandler.handleVeiculosException(ex, webRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void handleConnectException_deveRetornarServiceUnavailable() {
        Exception ex = new ConnectException("Falha ao conectar");

        ResponseEntity<?> response = exceptionHandler.handleConnectException(ex, webRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(503);
    }
}

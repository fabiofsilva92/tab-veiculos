package com.itau.desafio.framework.adapter.in.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ErrosDetalhes {
    private Date timestamp;
    private String mensagem;
    private String detalhes;

    public ErrosDetalhes(Date timestamp, String mensagem, String detalhes) {
        super();
        this.timestamp = timestamp;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }
}

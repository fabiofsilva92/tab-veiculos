package com.itau.desafio.framework.adapter.in.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private String marca;
    private String decada;
    private String quantidade;
}

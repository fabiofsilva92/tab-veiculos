package com.itau.desafio.framework.adapter.in.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itau.desafio.domain.enums.TiposVeiculoEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VeiculoResponseDTO {
    private Long id;
    private TiposVeiculoEnum tipo;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private Integer capacidadeTanque;
    private Integer consumo;
    private Double autonomia;
    private Boolean checkup;
    private Date created;
    private Date updated;
}

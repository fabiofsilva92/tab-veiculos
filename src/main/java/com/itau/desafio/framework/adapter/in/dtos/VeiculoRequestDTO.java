package com.itau.desafio.framework.adapter.in.dtos;

import com.itau.desafio.domain.enums.TiposVeiculoEnum;
import com.itau.desafio.framework.util.validators.MarcaValida;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Valid
public class VeiculoRequestDTO {
    private TiposVeiculoEnum tipo;
    private String veiculo;
    @MarcaValida
    private String marca;
    private Integer ano;
    private String descricao;
    private Integer capacidadeTanque;
    private Integer autonomia;
}

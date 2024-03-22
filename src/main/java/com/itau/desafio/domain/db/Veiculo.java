package com.itau.desafio.domain.db;

import com.itau.desafio.domain.enums.TiposVeiculoEnum;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;


@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "veiculo")
public class Veiculo {
    //Ajustar modelo Veiculos para aceitar diversos tipos de veiculos
    //Para aplicar o modelo solid, fazer classes especificas para calcular autonomia, realizar checkup
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TiposVeiculoEnum tipo;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private Integer capacidadeTanque;
    private Integer autonomia;
    private Date created;
    private Date updated;


}

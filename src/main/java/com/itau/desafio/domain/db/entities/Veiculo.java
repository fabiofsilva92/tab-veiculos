package com.itau.desafio.domain.db.entities;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TiposVeiculoEnum tipo;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    @Column(name = "capacidade_tanque")
    private Integer capacidadeTanque;
    private Integer consumo;
    private Double autonomia;
    private Boolean checkup;
    private Date created;
    private Date updated;


}

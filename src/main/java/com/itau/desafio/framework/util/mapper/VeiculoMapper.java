package com.itau.desafio.framework.util.mapper;



import com.itau.desafio.domain.db.Veiculo;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;

import java.util.Date;

public class VeiculoMapper {

    public static Veiculo toVeiculo(VeiculoRequestDTO veiculoRequestDTO) {
        return Veiculo
                .builder()
                .tipo(checkIfIsNull(veiculoRequestDTO.getTipo()))
                .veiculo(checkIfIsNull(veiculoRequestDTO.getVeiculo()))
                .marca(checkIfIsNull(veiculoRequestDTO.getMarca()))
                .ano(checkIfIsNull(veiculoRequestDTO.getAno()))
                .descricao(checkIfIsNull(veiculoRequestDTO.getDescricao()))
                .capacidadeTanque(checkIfIsNull(veiculoRequestDTO.getCapacidadeTanque()))
                .autonomia(checkIfIsNull(veiculoRequestDTO.getAutonomia()))
                .build();
    }

    public static VeiculoResponseDTO toVeiculoResponseDTO(Veiculo veiculo) {
        return VeiculoResponseDTO
                .builder()
                .id(veiculo.getId())
                .tipo(checkIfIsNull(veiculo.getTipo()))
                .veiculo(checkIfIsNull(veiculo.getVeiculo()))
                .marca(checkIfIsNull(veiculo.getMarca()))
                .ano(checkIfIsNull(veiculo.getAno()))
                .descricao(checkIfIsNull(veiculo.getDescricao()))
                .capacidadeTanque(checkIfIsNull(veiculo.getCapacidadeTanque()))
                .autonomia(checkIfIsNull(veiculo.getAutonomia()))
                .created(checkIfIsNull(veiculo.getCreated()))
                .updated(checkIfIsNull(veiculo.getUpdated()))
                .build();
    }

    //Prevenir NullPointerException
    private static <T> T checkIfIsNull(T obj) {
        return obj == null ? null : obj;
    }
}

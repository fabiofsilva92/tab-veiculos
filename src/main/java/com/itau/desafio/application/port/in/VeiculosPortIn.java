package com.itau.desafio.application.port.in;


import com.itau.desafio.framework.adapter.in.dtos.GenericResponse;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;

import java.util.List;

public interface VeiculosPortIn {

    List<VeiculoResponseDTO> getAllVeiculos();
    List<VeiculoResponseDTO> getVeiculosByMarcaAndAno(String marca, Integer ano);

    VeiculoResponseDTO getVeiculoById(Long id);

    VeiculoResponseDTO saveVeiculo(VeiculoRequestDTO veiculoRequestDTO);

    VeiculoResponseDTO updateVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO);
    VeiculoResponseDTO patchVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO);
    void deleteVeiculo(Long id);

    GenericResponse getQuantidadeByMarca(String marca);

    GenericResponse getQuantidadeByDecada(Integer ano);
}

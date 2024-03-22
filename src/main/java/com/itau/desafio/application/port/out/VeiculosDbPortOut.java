package com.itau.desafio.application.port.out;


import com.itau.desafio.domain.db.Veiculo;

import java.util.List;

public interface VeiculosDbPortOut {
    List<Veiculo> getAllVeiculos();
    List<Veiculo> getVeiculosByMarcaAndAno(String marca, Integer ano);

    Veiculo getVeiculoById(Long id);

    Veiculo saveVeiculo(Veiculo veiculo);

    Veiculo updateVeiculo(Long id, Veiculo veiculo);
    Veiculo patchVeiculo(Long id, Veiculo veiculo);
    void deleteVeiculo(Long id);

    List<Veiculo> getQuantidadeByMarca(String marca);

    List<Veiculo> getQuantidadeByDecada(Integer inicioDecada, Integer fimDecada);
}

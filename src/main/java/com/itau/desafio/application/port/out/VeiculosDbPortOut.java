package com.itau.desafio.application.port.out;


import com.itau.desafio.domain.db.entities.Veiculo;

import java.util.List;

public interface VeiculosDbPortOut {
    List<Veiculo> getAllVeiculos();

    Veiculo getVeiculoById(Long id);

    Veiculo saveVeiculo(Veiculo veiculo);

    Veiculo updateVeiculo(Long id, Veiculo veiculo);
    Veiculo patchVeiculo(Long id, Veiculo veiculo);
    void deleteVeiculo(Long id);

}

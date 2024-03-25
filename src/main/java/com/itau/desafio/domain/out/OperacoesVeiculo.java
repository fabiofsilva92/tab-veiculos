package com.itau.desafio.domain.out;

import com.itau.desafio.domain.db.entities.Veiculo;

public interface OperacoesVeiculo {

    void realizarCheckup(Veiculo veiculo);
    void calcularAutonomiaTotal(Veiculo veiculo);

}

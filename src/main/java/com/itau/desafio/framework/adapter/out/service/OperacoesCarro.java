package com.itau.desafio.framework.adapter.out.service;

import com.itau.desafio.domain.db.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OperacoesCarro implements OperacoesVeiculo {
    @Override
    public void realizarCheckup(Veiculo veiculo) {
        log.info("Checkup do carro realizado -> " +veiculo);
        veiculo.setCheckup(true);
    }

    @Override
    public void calcularAutonomiaTotal(Veiculo veiculo) {
        //TODO abaixo colocar um coeficiente
        //Podemos imaginar regras especificas para cada veiculo, como por exemplo, o carro deveria ter um consumo de combustivel mais alto.
        log.info("Calculando autonomia total do carro -> " +veiculo);
        veiculo.setAutonomia((veiculo.getConsumo()*veiculo.getCapacidadeTanque())*0.9);
    }
}

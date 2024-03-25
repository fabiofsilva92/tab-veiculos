package com.itau.desafio.framework.adapter.out.service;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OperacoesMoto implements OperacoesVeiculo {

    private final Double  FATOR_EFICIENCIA = 1.1;

    @Override
    public void realizarCheckup(Veiculo veiculo) {
        log.info("Verificando componentes da moto {}", veiculo.getVeiculo());
        veiculo.setCheckup(true);
    }

    @Override
    public void calcularAutonomiaTotal(Veiculo veiculo) {
        //Podemos imaginar regras especificas para cada veiculo, como por exemplo, a moto deveria ter um consumo de combustivel mais eficiente.
        log.info("Calculando autonomia total da moto -> " +veiculo);
        veiculo.setAutonomia((veiculo.getConsumo()*veiculo.getCapacidadeTanque())*FATOR_EFICIENCIA);
        log.info("\n-> Fator de eficiencia -> "+FATOR_EFICIENCIA+" " +
                "\n-> Consumo -> "+veiculo.getConsumo()+" " +
                "\n-> Capacidade do tanque -> "+veiculo.getCapacidadeTanque()+" " +
                "\n-> Autonomia total -> "+veiculo.getAutonomia()+" " +
                "\n-> Consumo * Capacidade do tanque * Fator de eficiencia -> ("+veiculo.getConsumo()+"*"+veiculo.getCapacidadeTanque()+") * "+FATOR_EFICIENCIA+" = "+veiculo.getAutonomia()
        );
        log.info("Autonomia total da moto calculada-> " +veiculo.getAutonomia());
    }
}

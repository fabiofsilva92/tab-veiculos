package com.itau.desafio.framework.adapter.out.service;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperacoesOnibus implements OperacoesVeiculo {

    private final Double FATOR_EFICIENCIA = 0.85;

    @Override
    public void realizarCheckup(Veiculo veiculo) {
        log.info("Verificando componentes do onibus {}", veiculo.getVeiculo());
        veiculo.setCheckup(true);
    }

    @Override
    public void calcularAutonomiaTotal(Veiculo veiculo) {
        //Podemos imaginar regras especificas para cada veiculo, como por exemplo, o carro deveria ter um consumo de combustivel mais alto.
        log.info("Calculando autonomia total do onibus -> " +veiculo);
        veiculo.setAutonomia((veiculo.getConsumo()*veiculo.getCapacidadeTanque())*0.85);
        log.info("\n-> Fator de eficiencia -> "+FATOR_EFICIENCIA+" " +
                "\n-> Consumo -> "+veiculo.getConsumo()+" " +
                "\n-> Capacidade do tanque -> "+veiculo.getCapacidadeTanque()+" " +
                "\n-> Autonomia total -> "+veiculo.getAutonomia()+" " +
                "\n-> Consumo * Capacidade do tanque * Fator de eficiencia -> ("+veiculo.getConsumo()+"*"+veiculo.getCapacidadeTanque()+") * "+FATOR_EFICIENCIA+" = "+veiculo.getAutonomia()
        );
        log.info("Autonomia total do onibus calculada-> " +veiculo.getAutonomia());
    }
}

package com.itau.desafio.application.service;

import com.itau.desafio.domain.db.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import com.itau.desafio.framework.adapter.out.service.OperacoesCaminhao;
import com.itau.desafio.framework.adapter.out.service.OperacoesCarro;
import com.itau.desafio.framework.adapter.out.service.OperacoesMoto;
import com.itau.desafio.framework.adapter.out.service.OperacoesOnibus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class VeiculoService {
    private final Map<String, OperacoesVeiculo> operacoesPorTipo;

    public VeiculoService() {
        this.operacoesPorTipo = new HashMap<>();
        operacoesPorTipo.put("carro", new OperacoesCarro());
        operacoesPorTipo.put("moto", new OperacoesMoto());
        operacoesPorTipo.put("caminhao", new OperacoesCaminhao());
        operacoesPorTipo.put("onibus", new OperacoesOnibus());
    }

    public OperacoesVeiculo getOperacoesPorTipo(String tipo) {
        return operacoesPorTipo.get(tipo);
    }

    //Receber veiculo, realizar checkup e retornar veiculo
    public void realizarCheckup(Veiculo veiculo){
        log.info("Realizando checkup do veiculo: {}", veiculo);
        getOperacoesPorTipo(veiculo.getTipo().toString().toLowerCase()).realizarCheckup(veiculo);
        log.info("Checkup realizado com sucesso: {}", veiculo);
    }

    public void calcularAutonomia(Veiculo veiculo){
        log.info("Calculando autonomia do veiculo: {}", veiculo);
        getOperacoesPorTipo(veiculo.getTipo().toString().toLowerCase()).calcularAutonomiaTotal(veiculo);
        log.info("Autonomia calculada com sucesso: {}", veiculo);
    }
}

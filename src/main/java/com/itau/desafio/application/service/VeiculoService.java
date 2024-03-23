package com.itau.desafio.application.service;

import com.itau.desafio.domain.db.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import com.itau.desafio.framework.adapter.out.service.OperacoesCaminhao;
import com.itau.desafio.framework.adapter.out.service.OperacoesCarro;
import com.itau.desafio.framework.adapter.out.service.OperacoesMoto;
import com.itau.desafio.framework.adapter.out.service.OperacoesOnibus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
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
        getOperacoesPorTipo(veiculo.getTipo().toString().toLowerCase()).realizarCheckup(veiculo);;
    }

    public void calcularAutonomia(Veiculo veiculo){
        getOperacoesPorTipo(veiculo.getTipo().toString().toLowerCase()).calcularAutonomiaTotal(veiculo);
    }
}

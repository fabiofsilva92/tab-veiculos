package com.itau.desafio.framework.adapter.out.service;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.framework.adapter.util.VeiculoFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Slf4j
public class OperacoesVeiculoTest {

    @InjectMocks
    private OperacoesCaminhao operacoesCaminhao;

    @InjectMocks
    private OperacoesCarro  operacoesCarroMock;
    @InjectMocks
    private OperacoesMoto operacoesMotoMock;

    @InjectMocks
    private OperacoesOnibus operacoesOnibusMock;

    private Veiculo veiculoMock = VeiculoFactory.createVeiculo();

    @Test
    @DisplayName("Caminhão -> Teste de checkup")
    public void testRealizarCheckupCaminhao() {
        operacoesCaminhao.realizarCheckup(veiculoMock);
        Assertions.assertEquals(veiculoMock.getCheckup(), true);
    }

    @Test
    @DisplayName("Caminhão -> Calculo de autonomia")
    public void testCalcularAutonomiaTotalCaminhao() {

        operacoesCaminhao.calcularAutonomiaTotal(veiculoMock);
        //Fator de 0,8
        Assertions.assertEquals(veiculoMock.getAutonomia(), 400d);
    }


    @Test
    @DisplayName("Carro -> Teste de checkup")
    public void testRealizarCheckupCarro() {

        operacoesCarroMock.realizarCheckup(veiculoMock);
        Assertions.assertEquals(veiculoMock.getCheckup(), true);
    }

    @Test
    @DisplayName("Carro -> Calculo de autonomia")
    public void testCalcularAutonomiaTotalCarro() {

        operacoesCarroMock.calcularAutonomiaTotal(veiculoMock);
        //Fator de 0,9
        Assertions.assertEquals(veiculoMock.getAutonomia(), 450d);
    }

    @Test
    @DisplayName("Moto -> Teste de checkup")
    public void testRealizarCheckupMoto() {

        operacoesMotoMock.realizarCheckup(veiculoMock);
        Assertions.assertEquals(veiculoMock.getCheckup(), true);
    }

    @Test
    @DisplayName("Moto -> Calculo de autonomia")
    public void testCalcularAutonomiaTotalMoto() {

        operacoesMotoMock.calcularAutonomiaTotal(veiculoMock);
        //Fator de 1,1
        Assertions.assertEquals(veiculoMock.getAutonomia(), 550d);
    }

    @Test
    @DisplayName("Onibus -> Teste de checkup")
    public void testRealizarCheckupOnibus() {

        operacoesOnibusMock.realizarCheckup(veiculoMock);
        Assertions.assertEquals(veiculoMock.getCheckup(), true);
    }

    @Test
    @DisplayName("Onibus -> Calculo de autonomia")
    public void testCalcularAutonomiaTotalOnibus() {

        operacoesOnibusMock.calcularAutonomiaTotal(veiculoMock);
        //Fator de 0.85
        Assertions.assertEquals(veiculoMock.getAutonomia(), 425d);
    }

}

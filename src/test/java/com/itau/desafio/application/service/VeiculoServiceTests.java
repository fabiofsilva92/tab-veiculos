package com.itau.desafio.application.service;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.domain.out.OperacoesVeiculo;
import com.itau.desafio.framework.adapter.out.service.OperacoesCaminhao;
import com.itau.desafio.framework.adapter.out.service.OperacoesCarro;
import com.itau.desafio.framework.adapter.out.service.OperacoesMoto;
import com.itau.desafio.framework.adapter.out.service.OperacoesOnibus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;
import java.util.HashMap;

import static com.itau.desafio.domain.enums.TiposVeiculoEnum.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VeiculoServiceTests {

    @InjectMocks
    private VeiculoService veiculoService;
    @Mock
    private OperacoesCarro  operacoesCarroMock;
    @Mock
    private OperacoesMoto operacoesMotoMock;

    @Mock
    private OperacoesOnibus operacoesOnibusMock;

    @Mock
    private OperacoesCaminhao operacoesCaminhaoMock;

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        Field field = VeiculoService.class.getDeclaredField("operacoesPorTipo");
        field.setAccessible(true);
        field.set(veiculoService, new HashMap<String, OperacoesVeiculo>() {{
            put("carro", operacoesCarroMock);
            put("moto", operacoesMotoMock);
            put("caminhao", operacoesCaminhaoMock);
            put("onibus", operacoesOnibusMock);
        }});

    }

    @Test
    void realizarCheckupDeveChamarRealizarCheckupDoVeiculoCarro() {
        Veiculo veiculoCarro = new Veiculo();
        veiculoCarro.setTipo(CARRO);

        veiculoService.realizarCheckup(veiculoCarro);

        verify(operacoesCarroMock).realizarCheckup(veiculoCarro);
    }

    @Test
    void calcularAutonomiaDeveChamarCalcularAutonomiaDoVeiculoCarro() {
        Veiculo veiculoCarro = new Veiculo();
        veiculoCarro.setTipo(CARRO);

        veiculoService.calcularAutonomia(veiculoCarro);

        verify(operacoesCarroMock).calcularAutonomiaTotal(veiculoCarro);
    }

    @Test
    void realizarCheckupDeveChamarRealizarCheckupDoVeiculoMoto() {
        Veiculo veiculoMoto = new Veiculo();
        veiculoMoto.setTipo(MOTO);

        veiculoService.realizarCheckup(veiculoMoto);

        verify(operacoesMotoMock).realizarCheckup(veiculoMoto);
    }

    @Test
    void calcularAutonomiaDeveChamarCalcularAutonomiaDoVeiculoMoto() {
        Veiculo veiculoMoto = new Veiculo();
        veiculoMoto.setTipo(MOTO);

        veiculoService.calcularAutonomia(veiculoMoto);

        verify(operacoesMotoMock).calcularAutonomiaTotal(veiculoMoto);
    }


    @Test
    void realizarCheckupDeveChamarRealizarCheckupDoVeiculoOnibus() {
        Veiculo veiculoOnibus = new Veiculo();
        veiculoOnibus.setTipo(ONIBUS);

        veiculoService.realizarCheckup(veiculoOnibus);

        verify(operacoesOnibusMock).realizarCheckup(veiculoOnibus);
    }

    @Test
    void calcularAutonomiaDeveChamarCalcularAutonomiaDoVeiculoOnibus() {
        Veiculo veiculoOnibus = new Veiculo();
        veiculoOnibus.setTipo(ONIBUS);

        veiculoService.calcularAutonomia(veiculoOnibus);

        verify(operacoesOnibusMock).calcularAutonomiaTotal(veiculoOnibus);
    }

    @Test
    void realizarCheckupDeveChamarRealizarCheckupDoVeiculoCaminhao() {
        Veiculo veiculoCaminhao = new Veiculo();
        veiculoCaminhao.setTipo(CAMINHAO);

        veiculoService.realizarCheckup(veiculoCaminhao);

        verify(operacoesCaminhaoMock).realizarCheckup(veiculoCaminhao);
    }

    @Test
    void calcularAutonomiaDeveChamarCalcularAutonomiaDoVeiculoCaminhao() {
        Veiculo veiculoCaminhao = new Veiculo();
        veiculoCaminhao.setTipo(CAMINHAO);

        veiculoService.calcularAutonomia(veiculoCaminhao);

        verify(operacoesCaminhaoMock).calcularAutonomiaTotal(veiculoCaminhao);
    }

}

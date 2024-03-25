package com.itau.desafio.framework.adapter.util;

import com.itau.desafio.domain.db.entities.Veiculo;

import static com.itau.desafio.domain.enums.TiposVeiculoEnum.*;

public class VeiculoFactory {

    public static Veiculo createVeiculo(){
        return Veiculo.builder()
                .id(1L)
                .tipo(CARRO)
                .veiculo("toyota")
                .marca("Toyota")
                .ano(2020)
                .descricao("descricao")
                .capacidadeTanque(50)
                .consumo(10)
                .checkup(false)
                .build();
    }

    public static String createVeiculoJson(){
        return "{\n" +
                "  \"tipo\": \"CARRO\",\n" +
                "  \"veiculo\": \"toyota\",\n" +
                "  \"marca\": \"toyota\",\n" +
                "  \"ano\": 2020,\n" +
                "  \"descricao\": \"descricao10\",\n" +
                "  \"capacidadeTanque\": 50,\n" +
                "  \"consumo\": 10,\n" +
                "  \"checkup\": false\n" +
                "}";
    }

    public static String createVeiculoMarcaInvalidaJson(){
        return "{\n" +
                "  \"tipo\": \"CARRO\",\n" +
                "  \"veiculo\": \"toyota\",\n" +
                "  \"marca\": \"toyota2\",\n" +
                "  \"ano\": 2020,\n" +
                "  \"descricao\": \"descricao10\",\n" +
                "  \"capacidadeTanque\": 50,\n" +
                "  \"consumo\": 10,\n" +
                "  \"checkup\": false\n" +
                "}";
    }

    public static String updateVeiculoJson(){
        return "{\n" +
                "  \"tipo\": \"CARRO\",\n" +
                "  \"veiculo\": \"Corolla\",\n" +
                "  \"marca\": \"Toyota\",\n" +
                "  \"ano\": 2021,\n" +
                "  \"descricao\": \"Veículo potente e elegante - ATUALIZADO\",\n" +
                "  \"capacidadeTanque\": 50,\n" +
                "  \"consumo\": 12,\n" +
                "  \"checkup\": false\n" +
                "}";
    }

    public static String patchVeiculoJson(){
        return "{\n" +
                "  \"veiculo\": \"Corolla\",\n" +
                "  \"ano\": 2021,\n" +
                "  \"descricao\": \"PATCH NO VEICULO - ATUALIZADO\"\n" +
                "}";
    }

    public static Veiculo veiculoWithUpdateNulos(){
        return Veiculo.builder().build();
    }

    public static Veiculo veiculoWithUpdateNaoNulos(){
        return Veiculo.builder()
                .tipo(CARRO)
                .veiculo("toyota corolla")
                .marca("toyota")
                .ano(1992)
                .descricao("descricao")
                .capacidadeTanque(50)
                .consumo(12)
                .build();
    }

}

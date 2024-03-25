package com.itau.desafio.framework.adapter.util;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.domain.enums.TiposVeiculoEnum;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import com.itau.desafio.framework.util.mapper.VeiculoMapper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VeiculoMapperTests {

    @Test
    void toVeiculo_DeveMapearCorretamente() {
        VeiculoMapper veiculoMapper = new VeiculoMapper();
        VeiculoRequestDTO veiculoRequestDTO = VeiculoRequestDTO.builder()
                .tipo(TiposVeiculoEnum.CARRO)
                .veiculo("Fusca")
                .marca("VW")
                .ano(1976)
                .descricao("Descrição do Fusca")
                .capacidadeTanque(40)
                .consumo(10)
                .build();

        Veiculo veiculo = veiculoMapper.toVeiculo(veiculoRequestDTO);

        assertEquals(TiposVeiculoEnum.CARRO, veiculo.getTipo());
        assertEquals("Fusca", veiculo.getVeiculo());
        assertEquals("VW", veiculo.getMarca());
        assertEquals(Integer.valueOf(1976), veiculo.getAno());
        assertEquals("Descrição do Fusca", veiculo.getDescricao());
        assertEquals(Integer.valueOf(40), veiculo.getCapacidadeTanque());
        assertEquals(Integer.valueOf(10), veiculo.getConsumo());
    }

    @Test
    void toVeiculo_DeveRetornarNullParaCamposNulos() {
        VeiculoRequestDTO veiculoRequestDTO = VeiculoRequestDTO.builder().build();

        Veiculo veiculo = VeiculoMapper.toVeiculo(veiculoRequestDTO);

        assertNull(veiculo.getTipo(), "Campo 'tipo' deveria ser null");
        assertNull(veiculo.getVeiculo(), "Campo 'veiculo' deveria ser null");
        assertNull(veiculo.getMarca(), "Campo 'marca' deveria ser null");
        assertNull(veiculo.getAno(), "Campo 'ano' deveria ser null");
        assertNull(veiculo.getDescricao(), "Campo 'descricao' deveria ser null");
        assertNull(veiculo.getCapacidadeTanque(), "Campo 'capacidadeTanque' deveria ser null");
        assertNull(veiculo.getConsumo(), "Campo 'consumo' deveria ser null");

    }

    @Test
    void toVeiculoResponseDTO_DeveMapearCorretamente() {
        // Preparação: criando um objeto Veiculo com valores específicos
        Date dataAtual = new Date();
        Veiculo veiculo = Veiculo.builder()
                .id(1L)
                .tipo(TiposVeiculoEnum.CARRO)
                .veiculo("Fusca")
                .marca("VW")
                .ano(1976)
                .descricao("Descrição do Fusca")
                .capacidadeTanque(40)
                .consumo(10)
                .autonomia(400d)
                .checkup(true)
                .created(dataAtual)
                .updated(dataAtual)
                .build();


        VeiculoResponseDTO veiculoResponseDTO = VeiculoMapper.toVeiculoResponseDTO(veiculo);

        assertEquals(veiculo.getId(), veiculoResponseDTO.getId());
        assertEquals(veiculo.getTipo(), veiculoResponseDTO.getTipo());
        assertEquals(veiculo.getVeiculo(), veiculoResponseDTO.getVeiculo());
        assertEquals(veiculo.getMarca(), veiculoResponseDTO.getMarca());
        assertEquals(veiculo.getAno(), veiculoResponseDTO.getAno());
        assertEquals(veiculo.getDescricao(), veiculoResponseDTO.getDescricao());
        assertEquals(veiculo.getCapacidadeTanque(), veiculoResponseDTO.getCapacidadeTanque());
        assertEquals(veiculo.getConsumo(), veiculoResponseDTO.getConsumo());
        assertEquals(veiculo.getAutonomia(), veiculoResponseDTO.getAutonomia());
        assertEquals(veiculo.getCheckup(), veiculoResponseDTO.getCheckup());
        assertEquals(veiculo.getCreated(), veiculoResponseDTO.getCreated());
        assertEquals(veiculo.getUpdated(), veiculoResponseDTO.getUpdated());
    }
}

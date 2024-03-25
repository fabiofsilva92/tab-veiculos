package com.itau.desafio.framework.adapter.in.rest;

import com.itau.desafio.framework.adapter.util.VeiculoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculosControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET ALL - Buscar todos veículos")
    public void listarTodosVeiculos_DeveRetornarOk() throws Exception {
        mockMvc.perform(get("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET by ID - Buscar veículo por id")
    public void  buscarVeiculoPorId_QuandoVeiculoExistir_DeveRetornarOk() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET by ID - Bad request")
    public void  buscarVeiculoPorId_QuandoVeiculoNaoExistir_DeveRetornarBadRequest() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST - Persistir veículo")
    public void salvarVeiculo_DeveRetornarCriadoComLocalizacao() throws Exception {
        String veiculoJson = VeiculoFactory.createVeiculoJson();

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("POST - Bad request - Marca inválida ao persistir veículo")
    public void salvarVeiculoInvalido_DeveRetornarBadRequest() throws Exception {
        String veiculoJson = VeiculoFactory.createVeiculoMarcaInvalidaJson();

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT - Atualizar veículo")
    public void atualizarVeiculo_QuandoVeiculoExistir_DeveRetornarOk() throws Exception {
        Long veiculoId = 1L;
        String veiculoJson = VeiculoFactory.updateVeiculoJson();

        mockMvc.perform(put("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT - Bad request ao atualizar veículo")
    public void atualizarVeiculo_QuandoVeiculoNaoExistir_DeveRetornarBadRequest() throws Exception {
        Long veiculoId = 10L;
        String veiculoJson = VeiculoFactory.updateVeiculoJson();

        mockMvc.perform(put("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("PUT - Atualizar parcialmente veículo")
    public void atualizacaoParcialVeiculo_QuandoVeiculoExistir_DeveRetornarOk() throws Exception {
        Long veiculoId = 2L;
        String veiculoPatchJson = VeiculoFactory.patchVeiculoJson();

        mockMvc.perform(patch("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoPatchJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT - Bad request ao atualizar parcialmente veículo")
    public void atualizacaoParcialVeiculo_QuandoVeiculoNaoExistir_DeveRetornarBadRequest() throws Exception {
        Long veiculoId = 20L;
        String veiculoPatchJson = VeiculoFactory.patchVeiculoJson();

        mockMvc.perform(patch("/veiculos/{id}", veiculoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoPatchJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Delete - Deletar veículo")
    public void excluirVeiculo_QuandoVeiculoExistir_DeveRetornarSemConteudo() throws Exception {
        Long veiculoId = 3L;

        mockMvc.perform(delete("/veiculos/{id}", veiculoId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete - Bad request ao deletar veículo")
    public void excluirVeiculo_QuandoVeiculoNaoExistir_DeveRetornarBadRequest() throws Exception {
        Long veiculoId = 30L;

        mockMvc.perform(delete("/veiculos/{id}", veiculoId))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST - Realizar checkup de veículo")
    public void realizarCheckup_QuandoVeiculoExistir_DeveRetornarOk() throws Exception {
        Long veiculoId = 1L;

        mockMvc.perform(post("/veiculos/checkup")
                        .param("id", veiculoId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST - Bad request ao realizar checkup de veículo que não existe ")
    public void realizarCheckup_QuandoVeiculoNaoExistir_DeveRetornarBadRequest() throws Exception {
        Long veiculoId = 10L;

        mockMvc.perform(post("/veiculos/checkup")
                        .param("id", veiculoId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST - Realizar calculo de autonomia de veículo")
    public void calcularAutonomia_QuandoVeiculoExistir_DeveRetornarOk() throws Exception {
        Long veiculoId = 1L;

        mockMvc.perform(post("/veiculos/autonomia")
                        .param("id", veiculoId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST - Bad request ao realizar calculo de autonomia de veículo")
    public void calcularAutonomia_QuandoVeiculoNaoExistir_DeveBadRequest() throws Exception {
        Long veiculoId = 10L;

        mockMvc.perform(post("/veiculos/autonomia")
                        .param("id", veiculoId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
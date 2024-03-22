package com.itau.desafio.framework.util.validators;

import com.itau.desafio.domain.db.Veiculo;

public class VeiculoValidatorUtil {

    public static Veiculo validate(Veiculo veiculoWithUpdates, Veiculo existingVeiculo) {


        if(veiculoWithUpdates.getTipo() != null){
            existingVeiculo.setTipo(veiculoWithUpdates.getTipo());
        }
        if(veiculoWithUpdates.getVeiculo() != null) {
            existingVeiculo.setVeiculo(veiculoWithUpdates.getVeiculo());
        }
        if(veiculoWithUpdates.getMarca() != null) {
            existingVeiculo.setMarca(veiculoWithUpdates.getMarca());
        }
        if(veiculoWithUpdates.getAno() != null && veiculoWithUpdates.getAno() != 0) {
            existingVeiculo.setAno(veiculoWithUpdates.getAno());
        }
        if(veiculoWithUpdates.getDescricao() != null) {
            existingVeiculo.setDescricao(veiculoWithUpdates.getDescricao());
        }
        if(veiculoWithUpdates.getCapacidadeTanque() != null && veiculoWithUpdates.getCapacidadeTanque() != 0){
            existingVeiculo.setCapacidadeTanque(veiculoWithUpdates.getCapacidadeTanque());
        }
        if(veiculoWithUpdates.getAutonomia() != null && veiculoWithUpdates.getAutonomia() != 0){
            existingVeiculo.setAutonomia(veiculoWithUpdates.getAutonomia());
        }


        return existingVeiculo;
    }

}

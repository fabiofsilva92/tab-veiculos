package com.itau.desafio.framework.adapter.validators;

import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.framework.adapter.util.VeiculoFactory;
import com.itau.desafio.framework.util.validators.VeiculoValidatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VeiculoValidatorUtilTests {

    @Test
    void validate_DeveNaoAtualizarCamposNulos() {
        VeiculoFactory veiculoFactory = new VeiculoFactory();
        VeiculoValidatorUtil veiculoValidatorUtil = new VeiculoValidatorUtil();
        Veiculo veiculoComUpdates = VeiculoFactory.veiculoWithUpdateNulos();
        Veiculo veiculo = VeiculoFactory.createVeiculo();
        Veiculo updatedVeiculo = VeiculoValidatorUtil.validate(veiculoComUpdates,veiculo);
        veiculoComUpdates.setAno(0);
        veiculoComUpdates.setCapacidadeTanque(0);
        veiculoComUpdates.setConsumo(0);
        veiculoValidatorUtil.validate(veiculoComUpdates, veiculo);


        Assertions.assertEquals(updatedVeiculo, veiculo);
    }



    @Test
    void validate_DeveAtualizarCamposNaoNulos() {
        Veiculo veiculoComUpdates = VeiculoFactory.veiculoWithUpdateNaoNulos();
        Veiculo veiculo = VeiculoFactory.createVeiculo();
        Veiculo updatedVeiculo = VeiculoValidatorUtil.validate(veiculoComUpdates,veiculo);

        Assertions.assertEquals(updatedVeiculo.getAno(), veiculoComUpdates.getAno());
    }
}

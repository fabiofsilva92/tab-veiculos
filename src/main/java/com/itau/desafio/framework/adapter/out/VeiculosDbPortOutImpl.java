package com.itau.desafio.framework.adapter.out;



import com.itau.desafio.application.port.out.VeiculosDbPortOut;
import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.framework.adapter.out.persistence.VeiculoRepository;
import com.itau.desafio.framework.exceptions.VeiculosException;
import com.itau.desafio.framework.util.validators.VeiculoValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class VeiculosDbPortOutImpl implements VeiculosDbPortOut {

    @Autowired
    private VeiculoRepository repository;

    @Override
    public List<Veiculo> getAllVeiculos() {
        return repository.findAll();
    }


    @Override
    public Veiculo getVeiculoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new VeiculosException("Veiculo não encontrado -> id: "+id));
    }

    @Override
    public Veiculo saveVeiculo(Veiculo veiculo) {
        veiculo.setCreated(new Date());
        veiculo.setCheckup(false);
        veiculo.setAutonomia(0d);
        return repository.save(veiculo);
    }

    @Override
    public Veiculo updateVeiculo(Long id, Veiculo veiculo) {

        if(!repository.existsById(id)) {
            throw new VeiculosException("Veiculo não encontrado para atualizar");
        }

        veiculo.setId(id);
        veiculo.setUpdated(new Date());
        return repository.save(veiculo);

    }

    @Override
    public Veiculo patchVeiculo(Long id, Veiculo veiculo) {

        Optional<Veiculo> byId = repository.findById(id);

        Veiculo existingVeiculo = byId.orElseThrow(() ->
                new VeiculosException("Veiculo não encontrado para atualização -> id: " + id));

        VeiculoValidatorUtil.validate(veiculo, existingVeiculo);
        existingVeiculo.setUpdated(new Date());
        return repository.save(existingVeiculo);
    }

    @Override
    public void deleteVeiculo(Long id) {
        if(!repository.existsById(id)) {
            throw new VeiculosException("Veiculo não encontrado para deletar -> id:"+id);
        }
        repository.deleteById(id);
    }

}

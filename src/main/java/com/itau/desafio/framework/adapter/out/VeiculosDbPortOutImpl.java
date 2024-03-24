package com.itau.desafio.framework.adapter.out;



import com.itau.desafio.application.port.out.VeiculosDbPortOut;
import com.itau.desafio.domain.db.Veiculo;
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
    public List<Veiculo> getVeiculosByMarcaAndAno(String marca, Integer ano) {
        return repository.buscarByMarcaAndAno(marca, ano);
    }

    @Override
    public Veiculo getVeiculoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new VeiculosException("Veiculo não encontrado"));
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

        if(byId.isPresent()){
            Veiculo existingVeiculo = byId.get();
            VeiculoValidatorUtil.validate(veiculo, existingVeiculo);
            existingVeiculo.setUpdated(new Date());
            return repository.save(existingVeiculo);
        }else{
            throw new RuntimeException("Veiculo não encontrado para o id: "+id);
        }

    }

    @Override
    public void deleteVeiculo(Long id) {
        if(!repository.existsById(id)) {
            throw new VeiculosException("Veiculo não encontrado para deletar");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Veiculo> getQuantidadeByMarca(String marca) {
        return repository.findByMarca(marca);
    }

    @Override
    public List<Veiculo> getQuantidadeByDecada(Integer inicioDecada, Integer fimDecada){
        return repository.findByAnoBetween(inicioDecada, fimDecada);
    }
}

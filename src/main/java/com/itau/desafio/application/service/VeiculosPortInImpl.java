package com.itau.desafio.application.service;

import com.itau.desafio.application.port.in.VeiculosPortIn;
import com.itau.desafio.application.port.out.VeiculosDbPortOut;
import com.itau.desafio.domain.db.entities.Veiculo;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import com.itau.desafio.framework.util.mapper.VeiculoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VeiculosPortInImpl implements VeiculosPortIn {

    @Autowired
    private VeiculosDbPortOut portOut;
    @Autowired
    private VeiculoService service;

    @Override
    public List<VeiculoResponseDTO> getAllVeiculos() {
        log.info("Solicitando todos os veículos");
        List<Veiculo> allVeiculos = portOut.getAllVeiculos();
        log.info("Retornando todos os veículos mapeados para DTO");
        return allVeiculos.stream().map(VeiculoMapper::toVeiculoResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public VeiculoResponseDTO getVeiculoById(Long id) {
        log.info("GET - Buscando veiculo por ID {}"+id);
        Veiculo veiculoById = portOut.getVeiculoById(id);
        log.info("Veiculo encontrado com sucesso");
        return VeiculoMapper.toVeiculoResponseDTO(veiculoById);
    }

    @Override
    public VeiculoResponseDTO saveVeiculo(VeiculoRequestDTO veiculoRequestDTO) {
        log.info("POST - Persistindo veiculo no bd");
        Veiculo veiculo = portOut.saveVeiculo(VeiculoMapper.toVeiculo(veiculoRequestDTO));
        log.info("Veiculo incluído com sucesso");
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public VeiculoResponseDTO updateVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO) {
        log.info("PUT - Atualizando veículo no bd");
        Veiculo veiculo = portOut.updateVeiculo(id, VeiculoMapper.toVeiculo(veiculoRequestDTO));
        log.info("Veículo atualizado com sucesso");
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public VeiculoResponseDTO patchVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO) {
        log.info("PATCH - Atualizando veículo no bd");
        Veiculo veiculo = portOut.patchVeiculo(id, VeiculoMapper.toVeiculo(veiculoRequestDTO));
        log.info("Veículo atualizado com sucesso");
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public void deleteVeiculo(Long id) {
        log.info("DELETE - Deletando veículo do bd");
        portOut.deleteVeiculo(id);
        log.info("Veículo deletado com sucesso");
    }

    @Override
    public VeiculoResponseDTO realizarCheckup(Long id) {
        Veiculo veiculoById = portOut.getVeiculoById(id);
        service.realizarCheckup(veiculoById);
        portOut.updateVeiculo(id, veiculoById);
        return VeiculoMapper.toVeiculoResponseDTO(veiculoById);
    }

    @Override
    public VeiculoResponseDTO calcularAutonomia(Long id) {
        Veiculo veiculoById = portOut.getVeiculoById(id);
        service.calcularAutonomia(veiculoById);
        portOut.updateVeiculo(id, veiculoById);
        return VeiculoMapper.toVeiculoResponseDTO(veiculoById);
    }

}

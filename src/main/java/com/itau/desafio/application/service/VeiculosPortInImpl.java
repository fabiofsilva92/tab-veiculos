package com.itau.desafio.application.service;

import com.itau.desafio.application.port.in.VeiculosPortIn;
import com.itau.desafio.application.port.out.VeiculosDbPortOut;
import com.itau.desafio.domain.db.Veiculo;
import com.itau.desafio.framework.adapter.in.dtos.GenericResponse;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import com.itau.desafio.framework.util.mapper.VeiculoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculosPortInImpl implements VeiculosPortIn {

    @Autowired
    private VeiculosDbPortOut portOut;
    @Autowired
    private VeiculoService service;

    @Override
    public List<VeiculoResponseDTO> getAllVeiculos() {
        List<Veiculo> allVeiculos = portOut.getAllVeiculos();
        return allVeiculos.stream().map(VeiculoMapper::toVeiculoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VeiculoResponseDTO> getVeiculosByMarcaAndAno(String marca, Integer ano) {
        List<Veiculo> veiculosByMarcaAndAno = portOut.getVeiculosByMarcaAndAno(marca, ano);
        return veiculosByMarcaAndAno.stream().map(VeiculoMapper::toVeiculoResponseDTO).collect(Collectors.toList());
    }

    @Override
    public VeiculoResponseDTO getVeiculoById(Long id) {
        return VeiculoMapper.toVeiculoResponseDTO(portOut.getVeiculoById(id));
    }

    @Override
    public VeiculoResponseDTO saveVeiculo(VeiculoRequestDTO veiculoRequestDTO) {
        Veiculo veiculo = portOut.saveVeiculo(VeiculoMapper.toVeiculo(veiculoRequestDTO));
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public VeiculoResponseDTO updateVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO) {
        Veiculo veiculo = portOut.updateVeiculo(id, VeiculoMapper.toVeiculo(veiculoRequestDTO));
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public VeiculoResponseDTO patchVeiculo(Long id, VeiculoRequestDTO veiculoRequestDTO) {
        Veiculo veiculo = portOut.patchVeiculo(id, VeiculoMapper.toVeiculo(veiculoRequestDTO));
        return VeiculoMapper.toVeiculoResponseDTO(veiculo);
    }

    @Override
    public void deleteVeiculo(Long id) {
        portOut.deleteVeiculo(id);
    }

    @Override
    public GenericResponse getQuantidadeByMarca(String marca) {
        List<Veiculo> list = portOut.getQuantidadeByMarca(marca);
        if(list.isEmpty()){
            return GenericResponse.builder().quantidade("0").marca("Marca não encontrada").build();
        }
        return GenericResponse.builder().quantidade(String.valueOf(list.size())).marca(marca).build();
    }

    @Override
    public GenericResponse getQuantidadeByDecada(Integer ano){
        Integer inicioDecada =(ano / 10) * 10;
        Integer fimDecada = inicioDecada + 9;
        List<Veiculo> retorno = portOut.getQuantidadeByDecada(inicioDecada, fimDecada);
        if(retorno.isEmpty()){
            return GenericResponse.builder().quantidade("0").decada("Não encontrado registros nesta decada").build();
        }
        return GenericResponse.builder().quantidade(String.valueOf(retorno.size())).decada("Decada de "+inicioDecada).build();
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

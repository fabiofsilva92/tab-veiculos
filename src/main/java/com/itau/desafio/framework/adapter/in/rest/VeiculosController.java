package com.itau.desafio.framework.adapter.in.rest;

import com.itau.desafio.application.port.in.VeiculosPortIn;
import com.itau.desafio.framework.adapter.in.dtos.GenericResponse;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Slf4j
public class VeiculosController {

    @Autowired
    private VeiculosPortIn portIn;

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getAll() {
        return ResponseEntity.ok(portIn.getAllVeiculos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getVeiculoById(@PathVariable("id") Long id){
        log.info("GET - Procurando veiculo por ID {}"+id);
        return ResponseEntity.ok(portIn.getVeiculoById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> saveVeiculo(@RequestBody @Valid VeiculoRequestDTO veiculo){
        log.info("POST - Salvando veiculo no bd");
        return ResponseEntity.ok(portIn.saveVeiculo(veiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> updateVeiculo(@PathVariable("id") Long id,
                                                            @RequestBody VeiculoRequestDTO veiculo){
        log.info("PUT - Atualizando veículo no bd");
        return ResponseEntity.ok(portIn.updateVeiculo(id, veiculo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> patchVeiculo(@PathVariable("id") Long id,
                                                           @RequestBody VeiculoRequestDTO veiculo){
        log.info("PATCH - Atualizando veículo no bd");
        return ResponseEntity.ok(portIn.patchVeiculo(id, veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id){
        log.info("DELETE - Deletando veículo do bd");
        portIn.deleteVeiculo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkup")
    public ResponseEntity<VeiculoResponseDTO> realizarCheckup(@RequestParam("id") Long id){
        return ResponseEntity.ok(portIn.realizarCheckup(id));
    }

    @PostMapping("/autonomia")
    public ResponseEntity<VeiculoResponseDTO> calcularAutonomia(@RequestParam("id") Long id){
        return ResponseEntity.ok(portIn.calcularAutonomia(id));
    }

}

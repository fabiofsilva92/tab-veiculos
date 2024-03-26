package com.itau.desafio.framework.adapter.in.rest;

import com.itau.desafio.application.port.in.VeiculosPortIn;
import com.itau.desafio.framework.adapter.in.dtos.GenericResponse;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veiculos REST")
public class VeiculosController {

    @Autowired
    private VeiculosPortIn portIn;

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getAll() {
        return ResponseEntity.ok(portIn.getAllVeiculos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getVeiculoById(@PathVariable("id") Long id){

        return ResponseEntity.ok(portIn.getVeiculoById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> saveVeiculo(@RequestBody @Valid VeiculoRequestDTO veiculo){
        VeiculoResponseDTO veiculoCriado = portIn.saveVeiculo(veiculo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculoCriado.getId())
                .toUri();

        return ResponseEntity.created(uri).body(veiculoCriado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> updateVeiculo(@PathVariable("id") Long id, @RequestBody VeiculoRequestDTO veiculo){
        return ResponseEntity.ok(portIn.updateVeiculo(id, veiculo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> patchVeiculo(@PathVariable("id") Long id,
                                                           @RequestBody VeiculoRequestDTO veiculo){
        return ResponseEntity.ok(portIn.patchVeiculo(id, veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id){
        portIn.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
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

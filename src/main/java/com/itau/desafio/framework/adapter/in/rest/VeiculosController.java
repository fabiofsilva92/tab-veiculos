package com.itau.desafio.framework.adapter.in.rest;

import com.itau.desafio.application.port.in.VeiculosPortIn;
import com.itau.desafio.framework.adapter.in.dtos.GenericResponse;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoRequestDTO;
import com.itau.desafio.framework.adapter.in.dtos.VeiculoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculosPortIn portIn;

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getAll() {
        return ResponseEntity.ok(portIn.getAllVeiculos());
    }

    @GetMapping(params = {"marca", "ano"})
    public ResponseEntity<List<VeiculoResponseDTO>> getVeiculosMarcaAndAno(@RequestParam(value = "marca", required = true) String marca,
                                                                           @RequestParam(value = "ano", required = true) Integer ano){
        return ResponseEntity.ok(portIn.getVeiculosByMarcaAndAno(marca, ano));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getVeiculoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(portIn.getVeiculoById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> saveVeiculo(@RequestBody @Valid VeiculoRequestDTO veiculo){
        return ResponseEntity.ok(portIn.saveVeiculo(veiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> updateVeiculo(@PathVariable("id") Long id, @RequestBody VeiculoRequestDTO veiculo){
        return ResponseEntity.ok(portIn.updateVeiculo(id, veiculo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> patchVeiculo(@PathVariable("id") Long id, @RequestBody VeiculoRequestDTO veiculo){
        return ResponseEntity.ok(portIn.patchVeiculo(id, veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable("id") Long id){
        portIn.deleteVeiculo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = {"marca"})
    public ResponseEntity<GenericResponse> getQuantidadeByMarca(@RequestParam(value = "marca", required = true) String marca){
        return ResponseEntity.ok(portIn.getQuantidadeByMarca(marca));
    }

    @GetMapping(params = {"ano"})
    public ResponseEntity<GenericResponse> getQuantidadeByAno(@RequestParam("ano") Integer ano){
        return ResponseEntity.ok(portIn.getQuantidadeByDecada(ano));
    }

}

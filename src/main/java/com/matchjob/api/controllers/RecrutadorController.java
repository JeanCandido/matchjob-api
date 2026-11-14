package com.matchjob.api.controllers;

import com.matchjob.api.dtos.RecrutadorResponseDTO;
import com.matchjob.api.models.Recrutador;
import com.matchjob.api.services.RecrutadorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recrutadores")
@AllArgsConstructor
public class RecrutadorController {

    private final RecrutadorService recrutadorService;

    @PostMapping
    public Recrutador criar(@RequestBody Recrutador recrutador) {
        return recrutadorService.criarRecrutador(recrutador);
    }

    @GetMapping
    public List<RecrutadorResponseDTO> listar() {
        return recrutadorService.listarRecrutadores();
    }

    @GetMapping("/{id}")
    public RecrutadorResponseDTO buscar(@PathVariable Long id) {
        return recrutadorService.buscarRecrutador(id);
    }
}
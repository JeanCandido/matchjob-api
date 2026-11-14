package com.matchjob.api.controllers;

import com.matchjob.api.dtos.CandidatoResponseDTO;
import com.matchjob.api.models.Candidato;
import com.matchjob.api.services.CandidatoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@AllArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    @PostMapping
    public Candidato criar(@RequestBody Candidato candidato) {
        return candidatoService.criarCandidato(candidato);
    }

    @GetMapping
    public List<CandidatoResponseDTO> listar() {
        return candidatoService.listarCandidatos();
    }

    @GetMapping("/{id}")
    public CandidatoResponseDTO buscar(@PathVariable Long id) {
        return candidatoService.buscarCandidato(id);
    }
}
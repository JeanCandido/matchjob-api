package com.matchjob.api.controllers;

import com.matchjob.api.dtos.VagaRequestDTO;
import com.matchjob.api.dtos.VagaResponseDTO;
import com.matchjob.api.models.Vaga;
import com.matchjob.api.services.CandidatoService;
import com.matchjob.api.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@AllArgsConstructor
public class VagaController {

    private final VagaService vagaService;
    private final CandidatoService candidatoService;

    @PostMapping
    public Vaga criar(@RequestBody VagaRequestDTO dados) {
        return vagaService.criarVaga(dados);
    }

    @GetMapping
    public List<VagaResponseDTO> listar() {
        return vagaService.listarVagas();
    }

    @GetMapping("/{id}")
    public VagaResponseDTO buscar(@PathVariable Long id) {
        return vagaService.buscarVaga(id);
    }

    @PostMapping("/{idVaga}/candidatar/{idCandidato}")
    public void candidatar(
            @PathVariable Long idVaga,
            @PathVariable Long idCandidato) {

        candidatoService.candidatarVaga(idVaga, idCandidato);
    }
}
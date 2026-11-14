package com.matchjob.api.services;

import com.matchjob.api.dtos.CandidatoResponseDTO;
import com.matchjob.api.dtos.RecrutadorResponseDTO;
import com.matchjob.api.dtos.VagaRequestDTO;
import com.matchjob.api.dtos.VagaResponseDTO;
import com.matchjob.api.models.Recrutador;
import com.matchjob.api.models.Vaga;
import com.matchjob.api.repositories.RecrutadorRepository;
import com.matchjob.api.repositories.VagaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final RecrutadorRepository recrutadorRepository;

    public Vaga criarVaga(VagaRequestDTO dados) {

        Recrutador recrutador = recrutadorRepository
                .findById(dados.idRecrutador())
                .orElseThrow(() ->
                        new RuntimeException("Recrutador não encontrado"));

        Vaga vaga = new Vaga();
        vaga.setNome(dados.nome());
        vaga.setDescricao(dados.descricao());
        vaga.setRecrutador(recrutador);

        return vagaRepository.save(vaga);
    }

    public List<VagaResponseDTO> listarVagas() {

        return vagaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public VagaResponseDTO buscarVaga(Long id) {

        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vaga não encontrada"));

        return converterParaDTO(vaga);
    }

    private VagaResponseDTO converterParaDTO(Vaga vaga) {

        RecrutadorResponseDTO recrutadorDTO =
                new RecrutadorResponseDTO(
                        vaga.getRecrutador().getId(),
                        vaga.getRecrutador().getNome(),
                        vaga.getRecrutador().getEmail()
                );

        List<CandidatoResponseDTO> candidatosDTO =
                vaga.getCandidatos()
                        .stream()
                        .map(c -> new CandidatoResponseDTO(
                                c.getId(),
                                c.getNome(),
                                c.getEmail()
                        ))
                        .toList();

        return new VagaResponseDTO(
                vaga.getId(),
                vaga.getNome(),
                vaga.getDescricao(),
                recrutadorDTO,
                candidatosDTO
        );
    }
}
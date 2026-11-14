
package com.matchjob.api.services;

import com.matchjob.api.dtos.CandidatoResponseDTO;
import com.matchjob.api.models.Candidato;
import com.matchjob.api.models.Vaga;
import com.matchjob.api.repositories.CandidatoRepository;
import com.matchjob.api.repositories.VagaRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final VagaRepository vagaRepository;

    // Criar candidato
    public Candidato criarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    // Listar todos os candidatos
    @Transactional(readOnly = true)
    public List<CandidatoResponseDTO> listarCandidatos() {

        return candidatoRepository.findAll()
                .stream()
                .map(c -> new CandidatoResponseDTO(
                        c.getId(),
                        c.getNome(),
                        c.getEmail()
                ))
                .toList();
    }

    // Buscar candidato por ID
    @Transactional(readOnly = true)
    public CandidatoResponseDTO buscarCandidato(Long id) {

        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Candidato não encontrado"));

        return new CandidatoResponseDTO(
                candidato.getId(),
                candidato.getNome(),
                candidato.getEmail()
        );
    }

    // Candidatar candidato em uma vaga
    @Transactional
    public void candidatarVaga(Long idVaga, Long idCandidato) {

        // Buscar vaga
        Vaga vaga = vagaRepository.findById(idVaga)
                .orElseThrow(() ->
                        new RuntimeException("Vaga não encontrada"));

        // Buscar candidato
        Candidato candidato = candidatoRepository.findById(idCandidato)
                .orElseThrow(() ->
                        new RuntimeException("Candidato não encontrado"));

        // Verificar se já existe candidatura
        boolean jaCandidatado = vaga.getCandidatos()
                .stream()
                .anyMatch(c -> c.getId().equals(idCandidato));

        if (jaCandidatado) {
            throw new RuntimeException(
                    "Candidato já está inscrito nesta vaga");
        }

        // Adicionar candidato à vaga
        vaga.getCandidatos().add(candidato);

        // Salvar alteração
        vagaRepository.save(vaga);
    }
}

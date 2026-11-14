package com.matchjob.api.services;

import com.matchjob.api.dtos.RecrutadorResponseDTO;
import com.matchjob.api.models.Recrutador;
import com.matchjob.api.repositories.RecrutadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecrutadorService {

    private final RecrutadorRepository recrutadorRepository;

    public Recrutador criarRecrutador(Recrutador recrutador) {
        return recrutadorRepository.save(recrutador);
    }

    public List<RecrutadorResponseDTO> listarRecrutadores() {
        return recrutadorRepository.findAll()
                .stream()
                .map(r -> new RecrutadorResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getEmail()
                ))
                .toList();
    }

    public RecrutadorResponseDTO buscarRecrutador(Long id) {

        Recrutador r = recrutadorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Recrutador não encontrado"));

        return new RecrutadorResponseDTO(
                r.getId(),
                r.getNome(),
                r.getEmail()
        );
    }
}
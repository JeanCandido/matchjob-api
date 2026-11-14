package com.matchjob.api.dtos;

import java.util.List;

public record VagaResponseDTO(
        Long id,
        String nome,
        String descricao,
        RecrutadorResponseDTO recrutador,
        List<CandidatoResponseDTO> candidatos
) {}
package com.matchjob.api.dtos;

public record VagaRequestDTO(
        String nome,
        String descricao,
        Long idRecrutador
) {}
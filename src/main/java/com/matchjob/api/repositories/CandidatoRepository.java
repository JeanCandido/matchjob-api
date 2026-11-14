package com.matchjob.api.repositories;

import com.matchjob.api.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}

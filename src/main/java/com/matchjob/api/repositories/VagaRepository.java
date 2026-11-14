package com.matchjob.api.repositories;

import com.matchjob.api.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}

package com.matchjob.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "recrutador_id")
    private Recrutador recrutador;

    @ManyToMany
    @JoinTable(
            name = "candidaturas",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id"),
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = {"vaga_id", "candidato_id"}
                    )
            }
    )
    private List<Candidato> candidatos = new ArrayList<>();
}
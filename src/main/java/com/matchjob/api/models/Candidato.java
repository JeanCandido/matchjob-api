package com.matchjob.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidatos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    // Candidato.java
    @JsonIgnore
    @ManyToMany(mappedBy = "candidatos")
    private List<Vaga> vagas = new ArrayList<>();
}
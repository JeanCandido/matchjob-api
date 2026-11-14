package com.matchjob.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recrutadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recrutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    // Recrutador.java
    @JsonIgnore
    @OneToMany(mappedBy = "recrutador")
    private List<Vaga> vagas = new ArrayList<>();
}
package com.api.campeonatovolei.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class TimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column
    @ManyToMany(mappedBy = "times")
    private List<CampeonatoModel> campeonatos;

    public TimeModel() {
    }

    public TimeModel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

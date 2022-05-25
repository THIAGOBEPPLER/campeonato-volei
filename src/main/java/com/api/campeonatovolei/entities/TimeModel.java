package com.api.campeonatovolei.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Time")

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

    public TimeModel(Integer id, String nome, List<CampeonatoModel> campeonatos) {
        this.id = id;
        this.nome = nome;
        this.campeonatos = campeonatos;
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

    public List<CampeonatoModel> getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(List<CampeonatoModel> campeonatos) {
        this.campeonatos = campeonatos;
    }
}

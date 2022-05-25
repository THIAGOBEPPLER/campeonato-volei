package com.api.campeonatovolei.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Campeonato")
public class CampeonatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean finalizado;

    @Column
    @ManyToMany
    @JoinTable(name = "Campeonato_Time", joinColumns = @JoinColumn(name = "campeonato_id"), inverseJoinColumns = @JoinColumn(name = "time_id"))
    @JsonIgnore
    private List<TimeModel> times;

    public CampeonatoModel() {
    }

    public CampeonatoModel(Integer id, String nome, Boolean finalizado, List<TimeModel> times) {
        this.id = id;
        this.nome = nome;
        this.finalizado = finalizado;
        this.times = times;
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

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public List<TimeModel> getTimes() {
        return times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }
}

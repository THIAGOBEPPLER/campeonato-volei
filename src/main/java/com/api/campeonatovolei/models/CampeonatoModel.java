package com.api.campeonatovolei.models;

import javax.persistence.*;
import java.util.List;

@Entity
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
    private List<TimeModel> times;

    public CampeonatoModel() {
    }

    public CampeonatoModel(Integer id, String nome, Boolean finalizado) {
        this.id = id;
        this.nome = nome;
        this.finalizado = finalizado;
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
}

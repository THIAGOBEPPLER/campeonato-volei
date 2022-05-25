package com.api.campeonatovolei.dtos;

import com.api.campeonatovolei.entities.TimeModel;

import java.util.List;

public class CriarCampeonatoDto {

    private String nome;
    private List<Integer> times;

    public CriarCampeonatoDto() {
    }

    public CriarCampeonatoDto(String nome, List<Integer> times) {
        this.nome = nome;
        this.times = times;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getTimes() {
        return times;
    }

    public void setTimes(List<Integer> times) {
        this.times = times;
    }
}

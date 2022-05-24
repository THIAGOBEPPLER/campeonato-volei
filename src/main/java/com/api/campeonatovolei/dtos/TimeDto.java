package com.api.campeonatovolei.dtos;

import javax.persistence.*;

public class TimeDto {

    private String nome;

    public TimeDto() {
    }

    public TimeDto(Integer id, String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

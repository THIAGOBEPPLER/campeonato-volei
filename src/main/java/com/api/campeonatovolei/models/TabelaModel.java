package com.api.campeonatovolei.models;

public class TabelaModel {


    private Integer timeId;
    private Integer saldo;
    private Integer vitorias;
    private Integer jogos;
    private Integer pontos;

    public TabelaModel() {
    }

    public TabelaModel(Integer timeId, Integer saldo, Integer vitorias, Integer jogos, Integer pontos) {
        this.timeId = timeId;
        this.saldo = saldo;
        this.vitorias = vitorias;
        this.jogos = jogos;
        this.pontos = pontos;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getJogos() {
        return jogos;
    }

    public void setJogos(Integer jogos) {
        this.jogos = jogos;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}


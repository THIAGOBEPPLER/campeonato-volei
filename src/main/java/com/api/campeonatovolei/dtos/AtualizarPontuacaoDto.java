package com.api.campeonatovolei.dtos;

public class AtualizarPontuacaoDto {

    private Integer jogoId;
    private Integer time;


    public AtualizarPontuacaoDto() {
    }

    public AtualizarPontuacaoDto(Integer jogoId, Integer time) {
        this.jogoId = jogoId;
        this.time = time;
    }

    public Integer getJogoId() {
        return jogoId;
    }

    public void setJogoId(Integer jogoId) {
        this.jogoId = jogoId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}

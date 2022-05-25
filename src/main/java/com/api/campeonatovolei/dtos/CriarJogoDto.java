package com.api.campeonatovolei.dtos;

public class CriarJogoDto {

    private Integer campeonatoId;
    private Integer timeId1;
    private Integer timeId2;

    public CriarJogoDto() {
    }

    public CriarJogoDto(Integer campeonatoId, Integer timeId1, Integer timeId2) {
        this.campeonatoId = campeonatoId;
        this.timeId1 = timeId1;
        this.timeId2 = timeId2;
    }

    public Integer getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Integer campeonatoId) {
        this.campeonatoId = campeonatoId;
    }

    public Integer getTimeId1() {
        return timeId1;
    }

    public void setTimeId1(Integer timeId1) {
        this.timeId1 = timeId1;
    }

    public Integer getTimeId2() {
        return timeId2;
    }

    public void setTimeId2(Integer timeId2) {
        this.timeId2 = timeId2;
    }
}

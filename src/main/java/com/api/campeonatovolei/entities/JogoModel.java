package com.api.campeonatovolei.entities;

import javax.persistence.*;


@Entity(name = "Jogo")
public class JogoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private Integer time1;

    @Column(nullable = false)
    private Integer time2;

    @Column(nullable = false)
    private Integer pontuacaoTime1;

    @Column(nullable = false)
    private Integer pontuacaoTime2;

    @Column(nullable = false)
    private Boolean finalizado;

    @Column()
    private Integer vencedor;

    @Column()
    private Integer campeonatoId;

    public JogoModel() {
    }

    public JogoModel(Integer id, Integer time1, Integer time2, Integer pontuacaoTime1, Integer pontuacaoTime2, Boolean finalizado, Integer vencedor, Integer campeonatoId) {
        this.id = id;
        this.time1 = time1;
        this.time2 = time2;
        this.pontuacaoTime1 = pontuacaoTime1;
        this.pontuacaoTime2 = pontuacaoTime2;
        this.finalizado = finalizado;
        this.vencedor = vencedor;
        this.campeonatoId = campeonatoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime1() {
        return time1;
    }

    public void setTime1(Integer time1) {
        this.time1 = time1;
    }

    public Integer getTime2() {
        return time2;
    }

    public void setTime2(Integer time2) {
        this.time2 = time2;
    }

    public Integer getPontuacaoTime1() {
        return pontuacaoTime1;
    }

    public void setPontuacaoTime1(Integer pontuacaoTime1) {
        this.pontuacaoTime1 = pontuacaoTime1;
    }

    public Integer getPontuacaoTime2() {
        return pontuacaoTime2;
    }

    public void setPontuacaoTime2(Integer pontuacaoTime2) {
        this.pontuacaoTime2 = pontuacaoTime2;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getVencedor() {
        return vencedor;
    }

    public void setVencedor(Integer vencedor) {
        this.vencedor = vencedor;
    }

    public Integer getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Integer campeonatoId) {
        this.campeonatoId = campeonatoId;
    }
}

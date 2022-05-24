package com.api.campeonatovolei.models;

import javax.persistence.*;

@Entity
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

    public JogoModel() {
    }

}

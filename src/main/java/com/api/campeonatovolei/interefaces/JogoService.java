package com.api.campeonatovolei.interefaces;

import com.api.campeonatovolei.dtos.AtualizarPontuacaoDto;
import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.entities.JogoModel;

import java.util.ArrayList;
import java.util.Optional;

public interface JogoService {

    public abstract Object criarJogo(CriarJogoDto jogoDto);

    public abstract ArrayList<JogoModel> listarJogos();

    public abstract ArrayList<JogoModel> listarJogosPorCampeonato(Integer campeonatoId);
    public abstract Optional<JogoModel> buscarJogo(Integer jogoId);

    public abstract Object atualizarPontuacao(AtualizarPontuacaoDto atualizarPontuacaoDto);
}

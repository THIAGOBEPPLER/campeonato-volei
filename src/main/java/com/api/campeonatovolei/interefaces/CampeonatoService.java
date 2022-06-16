package com.api.campeonatovolei.interefaces;

import com.api.campeonatovolei.dtos.CriarCampeonatoDto;
import com.api.campeonatovolei.dtos.FinalizarCampeonatoDto;
import com.api.campeonatovolei.entities.CampeonatoModel;
import com.api.campeonatovolei.models.TabelaModel;

import java.util.ArrayList;

public interface CampeonatoService {


    public abstract CampeonatoModel criarCampeonato(CriarCampeonatoDto campeonato);

    public abstract ArrayList<CampeonatoModel> listarCampeonatos();

    public abstract ArrayList<TabelaModel> finalizarCampeonato(FinalizarCampeonatoDto finalizarCampeonato);

    public abstract ArrayList<TabelaModel> tabela(Integer campeonatoId);
}

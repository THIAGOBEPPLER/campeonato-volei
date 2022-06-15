package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.AtualizarPontuacaoDto;
import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.entities.JogoModel;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.repositories.CampeonatoRepository;
import com.api.campeonatovolei.repositories.JogoRepository;
import com.api.campeonatovolei.repositories.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogoService {

    final JogoRepository jogoRepository;
    final CampeonatoRepository campeonatoRepository;
    final TimeRepository timeRepository;

    public JogoService(JogoRepository jogoRepository, CampeonatoRepository campeonatoRepository, TimeRepository timeRepository) {
        this.jogoRepository = jogoRepository;
        this.campeonatoRepository = campeonatoRepository;
        this.timeRepository = timeRepository;
    }

    public Object criarJogo(CriarJogoDto jogoDto){

        if(Objects.equals(jogoDto.getTimeId1(), jogoDto.getTimeId2()))
            throw new IllegalArgumentException( "Um time não pode jogar contra ele mesmo!");

        var campeonato = campeonatoRepository.findById(jogoDto.getCampeonatoId()).orElse(null);

        if(campeonato == null)
            throw new IllegalArgumentException( "Campeonato não cadastrado!");

        if(campeonato.getFinalizado())
            throw new IllegalArgumentException( "Campeonato já foi finalizado!");


        var times = campeonato.getTimes();

        var timesIds = times.stream().map(TimeModel::getId).collect(Collectors.toList());

        if(!timesIds.contains(jogoDto.getTimeId1()))
            throw new IllegalArgumentException( "Time1 não está nesse campeonato!");

        if(!timesIds.contains(jogoDto.getTimeId2()))
            throw new IllegalArgumentException( "Time2 não está nesse campeonato!");

        var jogos = jogoRepository.findByCampeonatoIdAndFinalizado(jogoDto.getCampeonatoId(), false);

        var times1Ids = jogos.stream().map(JogoModel::getTime1).collect(Collectors.toList());
        var times2Ids = jogos.stream().map(JogoModel::getTime2).collect(Collectors.toList());

        if(times1Ids.contains(jogoDto.getTimeId1()) || times2Ids.contains(jogoDto.getTimeId1()))
            throw new IllegalArgumentException( "Time1 já está jogando!");

        if(times1Ids.contains(jogoDto.getTimeId2()) || times2Ids.contains(jogoDto.getTimeId2()))
            throw new IllegalArgumentException( "Time2 já está jogando!");

        var jogo = new JogoModel();

        jogo.setFinalizado(false);
        jogo.setPontuacaoTime1(0);
        jogo.setPontuacaoTime2(0);
        jogo.setTime1(jogoDto.getTimeId1());
        jogo.setTime2(jogoDto.getTimeId2());
        jogo.setCampeonatoId(jogoDto.getCampeonatoId());

        jogoRepository.save(jogo);

        return jogo;
    }

    public ArrayList<JogoModel> listarJogos(){

        return (ArrayList<JogoModel>) jogoRepository.findAll();
    }

    public ArrayList<JogoModel> listarJogosPorCampeonato(Integer campeonatoId){
        return (ArrayList<JogoModel>) jogoRepository.findByCampeonatoId(campeonatoId);
    }

    public Optional<JogoModel> buscarJogo(Integer jogoId){

        return jogoRepository.findById(jogoId);
    }

    public Object atualizarPontuacao(AtualizarPontuacaoDto atualizarPontuacaoDto){

        if (atualizarPontuacaoDto.getTime() != 1 && atualizarPontuacaoDto.getTime() != 2)
            throw new IllegalArgumentException( "Campo time deve ser 1 ou 2!");

        var jogo = jogoRepository.findById(atualizarPontuacaoDto.getJogoId()).orElse(null);

        if (jogo == null)
            throw new IllegalArgumentException( "Jogo não encontrado!");

        if(jogo.getFinalizado())
            throw new IllegalArgumentException( "Jogo já finalizado!");

        if(atualizarPontuacaoDto.getTime() == 1)
            jogo.setPontuacaoTime1(jogo.getPontuacaoTime1() + 1);
        else
            jogo.setPontuacaoTime2(jogo.getPontuacaoTime2() + 1);

        if((jogo.getPontuacaoTime1() >= 10 || jogo.getPontuacaoTime2() >= 10) && ( Math.abs( jogo.getPontuacaoTime1() - jogo.getPontuacaoTime2()) > 1)){
            jogo.setFinalizado(true);
            if(jogo.getPontuacaoTime1() > jogo.getPontuacaoTime2())
                jogo.setVencedor(jogo.getTime1());
            else
                jogo.setVencedor(jogo.getTime2());
        }

        jogoRepository.save(jogo);

        return jogo;
    }
}

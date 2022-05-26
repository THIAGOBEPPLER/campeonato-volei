package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.entities.JogoModel;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.repositories.CampeonatoRepository;
import com.api.campeonatovolei.repositories.JogoRepository;
import com.api.campeonatovolei.repositories.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
            return "Um time não pode jogar contra ele mesmo!";

        var campeonato = campeonatoRepository.findById(jogoDto.getCampeonatoId()).orElse(null);

        if(campeonato == null)
            return "Campeonato não cadastrado!";

        if(campeonato.getFinalizado())
            return "Campeonato já foi finalizado";


        var times = campeonato.getTimes();

        var timesIds = times.stream().map(TimeModel::getId).collect(Collectors.toList());

        if(!timesIds.contains(jogoDto.getTimeId1()))
            return "Time1 não está nesse campeonato";

        if(!timesIds.contains(jogoDto.getTimeId2()))
            return "Time2 não está nesse campeonato";

        var jogos = jogoRepository.findByCampeonatoIdAndFinalizado(jogoDto.getCampeonatoId(), false);

        var times1Ids = jogos.stream().map(JogoModel::getTime1).collect(Collectors.toList());
        var times2Ids = jogos.stream().map(JogoModel::getTime2).collect(Collectors.toList());

        if(times1Ids.contains(jogoDto.getTimeId1()) || times2Ids.contains(jogoDto.getTimeId1()))
            return "Time1 já está jogando.";

        if(times1Ids.contains(jogoDto.getTimeId2()) || times2Ids.contains(jogoDto.getTimeId2()))
            return "Time2 já está jogando.";

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

    public Object listarJogos(){
        return jogoRepository.findAll();
    }

    public Object listarJogosPorCampeonato(Integer campeonatoId){
        return jogoRepository.findByCampeonatoId(campeonatoId);
    }

    public Object buscarJogo(Integer jogoId){
        return jogoRepository.findById(jogoId);
    }


    //TODO: atualizarPontuacao
}

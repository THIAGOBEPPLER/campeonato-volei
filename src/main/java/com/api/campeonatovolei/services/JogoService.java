package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.entities.JogoModel;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.repositories.CampeonatoRepository;
import com.api.campeonatovolei.repositories.JogoRepository;
import com.api.campeonatovolei.repositories.TimeRepository;
import org.springframework.stereotype.Service;

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

        //TODO: Validar se o campeonato existe
        var campeonato = campeonatoRepository.findById(jogoDto.getCampeonatoId()).orElse(null);

        if(campeonato == null)
            return "Campeonato não cadastrado!";

        //TODO: Validar se o campeonato está em andamnento
        if(campeonato.getFinalizado())
            return "Campeonato já foi finalizado";

        //TODO: Validar se os times estão no campeonato

        var times = campeonato.getTimes();

        var timesIds = times.stream().map(TimeModel::getId).collect(Collectors.toList());

        if(!timesIds.contains(jogoDto.getTimeId1()))
            return "Time1 não está nesse campeonato";

        if(!timesIds.contains(jogoDto.getTimeId2()))
            return "Time2 não está nesse campeonato";


        //TODO: Validar se os times não estão jogando
        var jogos = jogoRepository.findByCampeonatoId(jogoDto.getCampeonatoId());


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

//    public Object listarTimes(){
//        return timeRepository.findAll();
//    }
}

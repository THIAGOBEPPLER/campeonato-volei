package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.CriarCampeonatoDto;
import com.api.campeonatovolei.dtos.FinalizarCampeonatoDto;
import com.api.campeonatovolei.entities.CampeonatoModel;
import com.api.campeonatovolei.entities.JogoModel;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.models.TabelaModel;
import com.api.campeonatovolei.repositories.CampeonatoRepository;
import com.api.campeonatovolei.repositories.JogoRepository;
import com.api.campeonatovolei.repositories.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CampeonatoService {

    final CampeonatoRepository campeonatoRepository;
    final TimeRepository timeRepository;
    final JogoRepository jogoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, TimeRepository timeRepository, JogoRepository jogoRepository) {
        this.campeonatoRepository = campeonatoRepository;
        this.timeRepository = timeRepository;
        this.jogoRepository = jogoRepository;
    }


    public CampeonatoModel criarCampeonato(CriarCampeonatoDto campeonato){

        var  campeonatoModel= new CampeonatoModel();


        ArrayList<TimeModel> listaTimes = new ArrayList<>();

        for ( Integer time: campeonato.getTimes()) {

            var timeModel = timeRepository.findById(time).orElse(null);

            if(timeModel != null)
                listaTimes.add(timeModel);
        }

        campeonatoModel.setNome(campeonato.getNome());
        campeonatoModel.setTimes(listaTimes);
        campeonatoModel.setFinalizado(false);

        return campeonatoRepository.save(campeonatoModel);
    }

    public ArrayList<CampeonatoModel> listarCampeonatos(){
        return (ArrayList<CampeonatoModel>) campeonatoRepository.findAll();
    }

    public ArrayList<TabelaModel> finalizarCampeonato(FinalizarCampeonatoDto finalizarCampeonato)  {

        var id = finalizarCampeonato.getId();
        var campeonato = campeonatoRepository.findById(id).orElse(null);

        if (campeonato == null)
            throw new IllegalArgumentException("Campeonato não cadastrado");

        var jogos = jogoRepository.findByCampeonatoIdAndFinalizado(finalizarCampeonato.getId(), false);

        if (jogos.size() != 0)
            throw new IllegalArgumentException("Esse campeonato possui jogos em andamento");

        if (campeonato.getFinalizado())
            throw new IllegalArgumentException("Campeonato já finalizado");

        campeonato.setFinalizado(true);
        campeonatoRepository.save(campeonato);

        return tabela(campeonato.getId());
    }

    public ArrayList<TabelaModel> tabela(Integer campeonatoId){

        var campeonato = campeonatoRepository.findById(campeonatoId).orElse(null);

        if (campeonato == null)
            throw new IllegalArgumentException( "Campeonato não cadastrado");

        var times = campeonato.getTimes();

        var jogos = jogoRepository.findByCampeonatoIdAndFinalizado(campeonatoId, true);

        var times1Ids = jogos.stream().map(JogoModel::getTime1).collect(Collectors.toList());
        var times2Ids = jogos.stream().map(JogoModel::getTime2).collect(Collectors.toList());
        var vencedores = jogos.stream().map(JogoModel::getVencedor).collect(Collectors.toList());

        var tabela = new ArrayList<TabelaModel>();


        for (var time : times) {

            var timeJogos1 = times1Ids.stream()
                    .filter(x -> x.equals(time.getId()))
                    .count();

            var timeJogos2 = times2Ids.stream()
                    .filter(x -> x.equals(time.getId()))
                    .count();

            var vitorias = vencedores.stream()
                    .filter(x -> x.equals(time.getId()))
                    .count();

            var numeroJogos = timeJogos1 + timeJogos2;

            var saldo = 0;

            for ( var jogo: jogos) {

                if(jogo.getTime1() == time.getId())
                    saldo = saldo +( jogo.getPontuacaoTime1() - jogo.getPontuacaoTime2());
                else if(jogo.getTime2() == time.getId())
                    saldo = saldo +( jogo.getPontuacaoTime2() - jogo.getPontuacaoTime1());
            }

            TabelaModel tabelaTime = new TabelaModel();
            tabelaTime.setJogos((int) numeroJogos);
            tabelaTime.setVitorias((int) vitorias);
            tabelaTime.setTimeId(time.getId());
            tabelaTime.setPontos((int) vitorias * 3);
            tabelaTime.setSaldo(saldo);

            tabela.add(tabelaTime);
        }

        tabela.sort(Comparator.comparing((TabelaModel::getPontos)).reversed());

        return tabela;
    }
}

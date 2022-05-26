package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.CriarCampeonatoDto;
import com.api.campeonatovolei.dtos.FinalizarCampeonatoDto;
import com.api.campeonatovolei.entities.CampeonatoModel;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.repositories.CampeonatoRepository;
import com.api.campeonatovolei.repositories.JogoRepository;
import com.api.campeonatovolei.repositories.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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


    public Object CriarCampeonato(CriarCampeonatoDto campeonato){

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

    public Object listarCampeonatos(){
        return campeonatoRepository.findAll();
    }

    public Object finalizarCampeonato(FinalizarCampeonatoDto finalizarCampeonato){

        var id = finalizarCampeonato.getId();
        var campeonato = campeonatoRepository.findById(id).orElse(null);

        if (campeonato == null)
            return "Campeonato não cadastrado";

        var jogos = jogoRepository.findByCampeonatoIdAndFinalizado(finalizarCampeonato.getId(), false);

        if (jogos.size() != 0)
            return "Esse campeonato possui jogos em andamento";

        if (campeonato.getFinalizado())
            return "Campeonato já finalizado";

        campeonato.setFinalizado(true);
        campeonatoRepository.save(campeonato);

        //TODO: Retornar tabela calculada
        return campeonato;
    }

    public Object tabela(Integer id){
        //TODO: Retornar tabela calculada
        return "Tabela campeonato " + id;
    }
}

package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.TimeDto;
import com.api.campeonatovolei.models.TimeModel;
import org.springframework.stereotype.Service;
import com.api.campeonatovolei.repositories.TimeRepository;

@Service
public class TimeService {

    final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public Object adicionarTime(TimeDto time){

        var timeModel = new TimeModel();

        timeModel.setNome(time.getNome());

        return timeRepository.save(timeModel);
    }

    public Object listarTimes(){
        return timeRepository.findAll();
    }
}

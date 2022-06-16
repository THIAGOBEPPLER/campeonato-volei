package com.api.campeonatovolei.services;

import com.api.campeonatovolei.dtos.TimeDto;
import com.api.campeonatovolei.entities.TimeModel;
import com.api.campeonatovolei.interefaces.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.campeonatovolei.repositories.TimeRepository;

import java.util.ArrayList;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
   TimeRepository timeRepository;

    public TimeModel adicionarTime(TimeDto time){

        var timeModel = new TimeModel();

        timeModel.setNome(time.getNome());

        return timeRepository.save(timeModel);
    }

    public ArrayList<TimeModel> listarTimes(){

        return (ArrayList<TimeModel>) timeRepository.findAll();
    }
}

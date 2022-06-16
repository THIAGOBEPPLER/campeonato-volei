package com.api.campeonatovolei.interefaces;

import com.api.campeonatovolei.dtos.TimeDto;
import com.api.campeonatovolei.entities.TimeModel;

import java.util.ArrayList;

public interface TimeService {
    public abstract TimeModel adicionarTime(TimeDto time);
    public abstract ArrayList<TimeModel> listarTimes();
}

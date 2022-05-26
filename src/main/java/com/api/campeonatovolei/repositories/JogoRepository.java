package com.api.campeonatovolei.repositories;

import com.api.campeonatovolei.entities.JogoModel;
import com.api.campeonatovolei.entities.TimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<JogoModel, Integer> {

    List<JogoModel> findByCampeonatoId(Integer campeonatoId);

    List<JogoModel> findByCampeonatoIdAndFinalizado(Integer campeonatoId, Boolean finalizado);


}

package com.api.campeonatovolei.repositories;

import com.api.campeonatovolei.entities.CampeonatoModel;
import com.api.campeonatovolei.entities.TimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<CampeonatoModel, Integer> {
}

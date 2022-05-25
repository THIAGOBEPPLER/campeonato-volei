package com.api.campeonatovolei.controllers;

import com.api.campeonatovolei.dtos.CriarCampeonatoDto;
import com.api.campeonatovolei.dtos.FinalizarCampeonatoDto;
import com.api.campeonatovolei.dtos.TimeDto;
import com.api.campeonatovolei.services.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/campeonato")
public class CampeonatoController {

    final CampeonatoService campeonatoService;

    @Autowired
    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<Object> adicionarTime(@RequestBody CriarCampeonatoDto model){
        return ResponseEntity.status(HttpStatus.CREATED).body(campeonatoService.CriarCampeonato(model));
    }

    @GetMapping("/lista")
    public ResponseEntity<Object> listarCampeonatos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService.listarCampeonatos());
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Object> adicionarTime(@RequestBody FinalizarCampeonatoDto model){
        return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.finalizarCampeonato(model));
    }

    @GetMapping("/tabela/{id}")
    public ResponseEntity<Object> listarCampeonatos(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService.tabela(id));
    }
}

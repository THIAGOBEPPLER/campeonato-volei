package com.api.campeonatovolei.controllers;

import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/jogo")
public class JogoController {

    final JogoService jogoService;

    @Autowired
    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarJogo(@RequestBody CriarJogoDto body){
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarJogo(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService.buscarJogo(id));
    }

    @GetMapping("")
    public ResponseEntity<Object> listarJogos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService.listarJogos());
    }

    @GetMapping("/campeonato/{id}")
    public ResponseEntity<Object> listarJogosPorCampeonato(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(jogoService.listarJogosPorCampeonato(id));
    }

}

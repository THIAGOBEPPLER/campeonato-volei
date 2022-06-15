package com.api.campeonatovolei.controllers;

import com.api.campeonatovolei.dtos.AtualizarPontuacaoDto;
import com.api.campeonatovolei.dtos.CriarJogoDto;
import com.api.campeonatovolei.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    final JogoService jogoService;

    @Autowired
    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarJogo(@RequestBody CriarJogoDto body){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(body));

        } catch (IllegalArgumentException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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

    @PostMapping("/atualizarPontuacao")
    public ResponseEntity<Object> atualizarPontuacao(@RequestBody AtualizarPontuacaoDto body){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.atualizarPontuacao(body));
        } catch (IllegalArgumentException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

}

package com.api.campeonatovolei.controllers;

import com.api.campeonatovolei.dtos.TimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.campeonatovolei.services.TimeService;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/time")
public class TimeController {

    final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<Object> adicionarTime(@RequestBody TimeDto time){
        return ResponseEntity.status(HttpStatus.CREATED).body(timeService.adicionarTime(time));
    }

    @GetMapping
    public ResponseEntity<Object> listarTimes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(timeService.listarTimes());
    }

//    @GetMapping("/teste")
//    public String index(){
//        return "Olá Mundo!";
//    }
}

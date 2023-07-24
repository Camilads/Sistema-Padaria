package com.ifpe.edu.paokentin.controller;

import com.ifpe.edu.paokentin.model.entities.Pao;
import com.ifpe.edu.paokentin.model.repositories.FacadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/pao")
public class PaoController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pao pao){

        try {
            FacadeRepository.getCurrentInstance().createPao(pao);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro do pão realizado com sucesso!");
        }catch (SQLException e){

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível cadastrar o pão.") ;
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pao> read(@PathVariable("id") int id){

        try {
            Pao pao = FacadeRepository.getCurrentInstance().readPao(id);
            if (pao != null){
                return ResponseEntity.ok(pao);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pao>>readAll(){

        try {
            List<Pao> paes = FacadeRepository.getCurrentInstance().readAllPao();
            return ResponseEntity.ok(paes);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}


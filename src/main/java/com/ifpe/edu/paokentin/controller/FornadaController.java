package com.ifpe.edu.paokentin.controller;

import com.ifpe.edu.paokentin.model.entities.Fornada;
import com.ifpe.edu.paokentin.model.entities.Pao;
import com.ifpe.edu.paokentin.model.repositories.FacadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/fornada")
public class FornadaController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Fornada fornada) {
        try {

            Pao p = FacadeRepository.getCurrentInstance().readPao(fornada.getPao().getId());

            if (p == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pão não encontrado.");
            }

            fornada.setHoraInicio(new Timestamp(System.currentTimeMillis()));
            FacadeRepository.getCurrentInstance().createFornada(fornada);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fornada criada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível criar a fornada.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornada> read(@PathVariable int id) {
        try {
            Fornada fornada = FacadeRepository.getCurrentInstance().readFornada(id);
            if (fornada != null) {
                return ResponseEntity.ok(fornada);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Fornada fornada) {
        fornada.setId(id);
        try {
            FacadeRepository.getCurrentInstance().updateFornada(fornada);
            return ResponseEntity.ok("Fornada atualizada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível atualizar a fornada.");
        }
    }


    @GetMapping
    public ResponseEntity<List<Fornada>> readAll() {
        try {
            List<Fornada> fornadas = FacadeRepository.getCurrentInstance().readAllFornada();
            return ResponseEntity.ok(fornadas);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}

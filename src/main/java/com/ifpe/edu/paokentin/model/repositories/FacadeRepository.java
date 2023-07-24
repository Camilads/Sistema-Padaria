package com.ifpe.edu.paokentin.model.repositories;

import com.ifpe.edu.paokentin.model.entities.Fornada;
import com.ifpe.edu.paokentin.model.entities.Pao;

import java.sql.SQLException;
import java.util.List;

public class FacadeRepository {

    private static FacadeRepository instance = new FacadeRepository();

    private GenericRepository<Pao, Integer> paoRepository = null;
    private GenericRepository<Fornada, Integer> fornadaRepository = null;

    private FacadeRepository() {
        this.paoRepository = new PaoRepository();
        this.fornadaRepository = new FornadaRepository();
    }

    public static FacadeRepository getCurrentInstance(){
        return instance;
    }

    public void createPao(Pao pao) throws SQLException{
        this.paoRepository.create(pao);
    }
    public void createFornada(Fornada fornada) throws SQLException{
        this.fornadaRepository.create(fornada);
    }

    public void updatePao(Pao pao) throws SQLException{
        this.paoRepository.update(pao);
    }
    public void updateFornada(Fornada fornada) throws SQLException{
        this.fornadaRepository.update(fornada);
    }

    public Pao readPao(int id) throws SQLException {
        return this.paoRepository.read(id);
    }
    public Fornada readFornada(int id) throws SQLException{
        return this.fornadaRepository.read(id);
    }

    public List<Pao> readAllPao() throws SQLException {
        return this.paoRepository.readAll();
    }
    public List<Fornada> readAllFornada() throws SQLException{
        return this.fornadaRepository.readAll();
    }


}

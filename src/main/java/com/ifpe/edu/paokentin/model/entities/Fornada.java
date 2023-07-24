package com.ifpe.edu.paokentin.model.entities;

import java.sql.Timestamp;

public class Fornada {

    private int id;
    private Pao pao;
    private Timestamp horaInicio;
    private boolean pronta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pao getPao() {
        return pao;
    }

    public void setPao(Pao pao) {
        this.pao = pao;
    }

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public boolean isPronta() {
        return pronta;
    }

    public void setPronta(boolean pronta) {
        this.pronta = pronta;
    }
}

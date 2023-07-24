package com.ifpe.edu.paokentin.model.entities;


public class Pao {

    private int id;
    private String tipoPao;

    private Long tempoPreparo;
    private String descricao;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoPao() {
        return tipoPao;
    }
    public void setTipoPao(String tipoPao) {
        this.tipoPao = tipoPao;
    }
    public Long getTempoPreparo() {
        return tempoPreparo;
    }
    public void setTempoPreparo(Long tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

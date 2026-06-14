package com.eGamelobby.eGame.model;

public class Time {

    private String nome;
    private String grupo;

    public Time() {
    }

    public Time(String nome, String grupo) {
        this.nome = nome;
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
package com.eGamelobby.eGame.model;

public class Time {

    private String nome;
    private String grupo;
    private int pontos;

    public Time() {
        this.pontos = 0; // padrão quando criar time
    }

    public Time(String nome, String grupo) {
        this.nome = nome;
        this.grupo = grupo;
        this.pontos = 0;
    }

    public Time(String nome, String grupo, int pontos) {
        this.nome = nome;
        this.grupo = grupo;
        this.pontos = pontos;
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

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}

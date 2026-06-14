package com.eGamelobby.eGame.service;

import com.eGamelobby.eGame.model.Time;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    private final List<Time> times = new ArrayList<>();

    public List<Time> listarTimes() {
        return new ArrayList<>(times);
    }

    public Time adicionarTime(Time time) {

        if (time == null) {
            throw new IllegalArgumentException("Time não pode ser nulo.");
        }

        if (time.getNome() == null || time.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do time é obrigatório.");
        }

        if (time.getGrupo() == null || time.getGrupo().trim().isEmpty()) {
            throw new IllegalArgumentException("Grupo é obrigatório.");
        }

        String grupo = time.getGrupo().toUpperCase();

        if (!grupo.equals("A") &&
                !grupo.equals("B") &&
                !grupo.equals("C")) {

            throw new IllegalArgumentException(
                    "Grupo inválido. Utilize A, B ou C."
            );
        }

        boolean existe = times.stream()
                .anyMatch(t ->
                        t.getNome().equalsIgnoreCase(time.getNome()));

        if (existe) {
            throw new IllegalArgumentException(
                    "Já existe um time com este nome."
            );
        }

        time.setNome(time.getNome().trim());
        time.setGrupo(grupo);

        times.add(time);

        return time;
    }

    public void removerTime(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nome do time é obrigatório."
            );
        }

        boolean removido = times.removeIf(
                t -> t.getNome().equalsIgnoreCase(nome)
        );

        if (!removido) {
            throw new IllegalArgumentException(
                    "Time não encontrado."
            );
        }
    }

    public List<Time> listarPorGrupo(String grupo) {

        if (grupo == null) {
            throw new IllegalArgumentException("Grupo inválido.");
        }

        grupo = grupo.toUpperCase();

        List<Time> resultado = new ArrayList<>();

        for (Time time : times) {
            if (time.getGrupo().equalsIgnoreCase(grupo)) {
                resultado.add(time);
            }
        }

        return resultado;
    }
}
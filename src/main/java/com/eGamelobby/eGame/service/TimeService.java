package com.eGamelobby.eGame.service;

import com.eGamelobby.eGame.model.Time;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TimeService {

    private final List<Time> times = new ArrayList<>();

    // =========================
    // LISTAR TODOS (ranking)
    // =========================
    public List<Time> listarTimes() {
        return times.stream()
                .sorted(Comparator.comparingInt(Time::getPontos).reversed())
                .toList();
    }

    // =========================
    // ADICIONAR TIME
    // =========================
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

        if (!grupo.equals("A") && !grupo.equals("B") && !grupo.equals("C")) {
            throw new IllegalArgumentException("Grupo inválido. Use A, B ou C.");
        }

        boolean existe = times.stream()
                .anyMatch(t -> t.getNome().equalsIgnoreCase(time.getNome()));

        if (existe) {
            throw new IllegalArgumentException("Já existe um time com este nome.");
        }

        time.setNome(time.getNome().trim());
        time.setGrupo(grupo);

        // 🔥 garante pontos iniciais
        if (time.getPontos() == 0) {
            time.setPontos(0);
        }

        times.add(time);

        return time;
    }

    // =========================
    // REMOVER TIME
    // =========================
    public void removerTime(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do time é obrigatório.");
        }

        boolean removido = times.removeIf(
                t -> t.getNome().equalsIgnoreCase(nome)
        );

        if (!removido) {
            throw new IllegalArgumentException("Time não encontrado.");
        }
    }

    // =========================
    // LISTAR POR GRUPO (RANKING)
    // =========================
    public List<Time> listarPorGrupo(String grupo) {

        if (grupo == null || grupo.trim().isEmpty()) {
            throw new IllegalArgumentException("Grupo inválido.");
        }

        String g = grupo.toUpperCase();

        return times.stream()
                .filter(t -> t.getGrupo().equalsIgnoreCase(g))
                .sorted(Comparator.comparingInt(Time::getPontos).reversed())
                .toList();
    }

    // =========================
    // 🔥 NOVO: ADICIONAR PONTOS
    // =========================
    public void adicionarPontos(String nome, int pontos) {

        Time time = times.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Time não encontrado.")
                );

        time.setPontos(time.getPontos() + pontos);
    }

    // =========================
    // 🔥 NOVO: RANKING GERAL
    // =========================
    public List<Time> ranking() {
        return listarTimes();
    }
}

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
    // LISTAR TODOS (RANKING)
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

        String grupo = time.getGrupo().trim().toUpperCase();

        if (!grupo.equals("A")
                && !grupo.equals("B")
                && !grupo.equals("C")
                && !grupo.equals("D")) {

            throw new IllegalArgumentException(
                    "Grupo inválido. Utilize A, B, C ou D."
            );
        }

        boolean existe = times.stream()
                .anyMatch(t ->
                        t.getNome().trim()
                                .equalsIgnoreCase(time.getNome().trim())
                );

        if (existe) {
            throw new IllegalArgumentException(
                    "Já existe um time com este nome."
            );
        }

        time.setNome(time.getNome().trim());
        time.setGrupo(grupo);

        if (time.getPontos() < 0) {
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
            throw new IllegalArgumentException(
                    "Nome do time é obrigatório."
            );
        }

        boolean removido = times.removeIf(
                t -> t.getNome().equalsIgnoreCase(nome.trim())
        );

        if (!removido) {
            throw new IllegalArgumentException("Time não encontrado.");
        }
    }

    // =========================
    // LISTAR POR GRUPO
    // =========================
    public List<Time> listarPorGrupo(String grupo) {

        if (grupo == null || grupo.trim().isEmpty()) {
            throw new IllegalArgumentException("Grupo inválido.");
        }

        String g = grupo.trim().toUpperCase();

        return times.stream()
                .filter(t -> t.getGrupo().equalsIgnoreCase(g))
                .sorted(Comparator.comparingInt(Time::getPontos).reversed())
                .toList();
    }

    // =========================
    // ADICIONAR PONTOS
    // =========================
    public void adicionarPontos(String nome, int pontos) {

        if (pontos <= 0) {
            throw new IllegalArgumentException(
                    "Os pontos devem ser maiores que zero."
            );
        }

        Time time = times.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Time não encontrado."
                        )
                );

        time.setPontos(time.getPontos() + pontos);
    }

    // =========================
    // REMOVER PONTOS
    // =========================
    public void removerPontos(String nome, int pontos) {

        if (pontos <= 0) {
            throw new IllegalArgumentException(
                    "Os pontos devem ser maiores que zero."
            );
        }

        Time time = times.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Time não encontrado."
                        )
                );

        int novosPontos = Math.max(0, time.getPontos() - pontos);
        time.setPontos(novosPontos);
    }

    // =========================
    // BUSCAR TIME
    // =========================
    public Time buscarTime(String nome) {

        return times.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Time não encontrado."
                        )
                );
    }

    // =========================
    // RANKING GERAL
    // =========================
    public List<Time> ranking() {
        return listarTimes();
    }
}
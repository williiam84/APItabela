package com.eGamelobby.eGame.controller;

import com.eGamelobby.eGame.model.Time;
import com.eGamelobby.eGame.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
@CrossOrigin(origins = "*")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    // =========================
    // LISTAR TIMES (já ordenado por pontos)
    // =========================
    @GetMapping
    public ResponseEntity<List<Time>> listarTimes() {
        return ResponseEntity.ok(timeService.listarTimes());
    }

    // =========================
    // RANKING GERAL (extra)
    // =========================
    @GetMapping("/ranking")
    public ResponseEntity<List<Time>> ranking() {
        return ResponseEntity.ok(timeService.ranking());
    }

    // =========================
    // LISTAR POR GRUPO
    // =========================
    @GetMapping("/grupo/{grupo}")
    public ResponseEntity<List<Time>> listarPorGrupo(
            @PathVariable String grupo) {

        return ResponseEntity.ok(
                timeService.listarPorGrupo(grupo)
        );
    }

    // =========================
    // ADICIONAR TIME
    // =========================
    @PostMapping
    public ResponseEntity<?> adicionarTime(@RequestBody Time time) {

        try {
            Time novoTime = timeService.adicionarTime(time);
            return ResponseEntity.ok(novoTime);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // =========================
    // REMOVER TIME
    // =========================
    @DeleteMapping("/{nome}")
    public ResponseEntity<?> removerTime(@PathVariable String nome) {

        try {
            timeService.removerTime(nome);
            return ResponseEntity.ok("Time removido com sucesso.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // =========================
    // 🔥 ADICIONAR PONTOS (NOVO)
    // =========================
    @PutMapping("/{nome}/pontos")
    public ResponseEntity<?> adicionarPontos(
            @PathVariable String nome,
            @RequestParam int pontos) {

        try {
            timeService.adicionarPontos(nome, pontos);
            return ResponseEntity.ok("Pontos atualizados com sucesso.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

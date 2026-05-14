package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.*;
import Model.Enums.*;
import Services.CalculaScoreService;

public class CalculaScoreServiceTest {
    private Usuario usuario;
    private CalculaScoreService service;

    @BeforeEach
    void setup() throws Exception {
        Map<Genero, Double> pesos = new HashMap<>();
        for (Genero g : Genero.values()) {
            pesos.put(g, 0.5);
        }

        PerfilCine perfil = new PerfilCine(90, 150, ClassIndicativa.MAIORDE16, pesos, Set.of(Idioma.PT, Idioma.EN),
                new HashMap<>(), new HashSet<>());

        usuario = new Usuario("Vitor", 20, 90, 150, ClassIndicativa.MAIORDE16, pesos, Set.of(Idioma.PT, Idioma.EN),
                new HashMap<>(), new HashSet<>());

        java.lang.reflect.Field field = Usuario.class.getDeclaredField("perfil");

        field.setAccessible(true);
        field.set(usuario, perfil);

        service = new CalculaScoreService();
    }

    @Test
    void deveCalcularScoreParaFilme() {

        Filme filme = new Filme("Aventura", 2020, 120, 80, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.AVENTURA));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);
        assertEquals(1, scores.size());
    }

    @Test
    void deveCalcularPopularidadeCorretamente() {

        Filme filme = new Filme("Popular", 2020, 120, 90, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);

        ScoreFilme score = scores.get(filme);

        assertEquals(0.9, score.getScorePopularidade(), 0.01);
    }

    @Test
    void deveRetornarScoreDuracaoMaximoQuandoDentroDoLimite() {

        Filme filme = new Filme("Duracao Ideal", 2020, 120, 50, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.DRAMA));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);

        ScoreFilme score = scores.get(filme);

        assertEquals(1.0, score.getScoreDuracao(), 0.01);
    }

    @Test
    void devePunirDuracaoMuitoForaDoLimite() {

        Filme filme = new Filme("Muito Longo", 2020, 300, 70, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.TERROR));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);

        ScoreFilme score = scores.get(filme);

        assertTrue(score.getScoreDuracao() < 1.0);
    }

    @Test
    void deveCalcularScoreGenero() {

        Filme filme = new Filme("Genero", 2020, 120, 70, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);

        ScoreFilme score = scores.get(filme);

        assertEquals(0.5, score.getScoreGenero(), 0.01);
    }

    @Test
    void deveGerarScoreTotalMaiorQueZero() {

        Filme filme = new Filme("Total", 2020, 120, 80, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.COMEDIA));

        Map<Filme, ScoreFilme> scores = new HashMap<>();

        service.definirScores(usuario, List.of(filme), scores);

        ScoreFilme score = scores.get(filme);

        assertTrue(score.getScoreTotal() > 0);
    }
}
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.*;
import Model.Enums.*;
import Services.GeradorJustificativaService;

public class GeradorJustificativaServiceTest {
    private Usuario usuario;
    private GeradorJustificativaService service;

    @BeforeEach
    void setup() throws Exception {
        Map<Genero, Double> pesos = new HashMap<>();

        for (Genero g : Genero.values()) {
            pesos.put(g, 0.5);
        }
        PerfilCine perfil = new PerfilCine(90, 150, ClassIndicativa.MAIORDE16, pesos, Set.of(Idioma.PT),
                new HashMap<>(), new HashSet<>());

        usuario = new Usuario("Vitor", 20, 90, 150, ClassIndicativa.MAIORDE16, pesos, Set.of(Idioma.PT),
                new HashMap<>(), new HashSet<>());

        java.lang.reflect.Field field = Usuario.class.getDeclaredField("perfil");

        field.setAccessible(true);
        field.set(usuario, perfil);

        service = new GeradorJustificativaService();
    }

    @Test
    void deveGerarJustificativaComGenero() {

        Filme filme = new Filme("Acao Total", 2020, 120, 80, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        ScoreFilme score = new ScoreFilme(80, 50, 50, 0, 70);

        String justificativa = service.gerar(usuario, filme, score);

        assertTrue(justificativa.contains("gosto"));
    }

    @Test
    void deveGerarJustificativaPorDuracao() {

        Filme filme = new Filme("Filme Ideal", 2020, 120, 70, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.DRAMA));

        ScoreFilme score = new ScoreFilme(50, 100, 40, 0, 60);

        String justificativa = service.gerar(usuario, filme, score);

        assertTrue(justificativa.contains("duração"));
    }

    @Test
    void deveGerarJustificativaPorAfinidade() {

        Filme filme = new Filme("Comedia", 2020, 120, 60, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.COMEDIA));

        ScoreFilme score = new ScoreFilme(50, 50, 50, 1, 70);

        String justificativa = service.gerar(usuario, filme, score);

        assertTrue(justificativa.contains("avaliou positivamente"));
    }

    @Test
    void deveGerarJustificativaPorPopularidade() {

        Filme filme = new Filme("Blockbuster", 2020, 120, 95, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.AVENTURA));

        ScoreFilme score = new ScoreFilme(50, 50, 50, 0, 70);

        String justificativa = service.gerar(usuario, filme, score);

        assertTrue(justificativa.contains("sucesso absoluto"));
    }

    @Test
    void deveGerarMensagemZonaDeConforto() {

        Filme filme = new Filme("Diferente", 2020, 300, 10, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.DOCUMENTARIO));

        ScoreFilme score = new ScoreFilme(10, 10, 10, 0, 10);

        String justificativa = service.gerar(usuario, filme, score);

        assertTrue(justificativa.contains("zona de conforto"));
    }
}

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.*;
import Model.Enums.*;
import Services.FiltroService;

public class FiltroServiceTest {

    private FiltroService filtroService;
    private Usuario usuario;

    private Usuario criarUsuario() {
        Map<Genero, Double> pesos = new HashMap<>();
        for (Genero genero : Genero.values()) {
            pesos.put(genero, 0.5);
        }
        return new Usuario("Vitor", 20, 90, 180, ClassIndicativa.MAIORDE18, pesos, Set.of(Idioma.PT, Idioma.EN),
                new HashMap<>(), new HashSet<>());
    }

    @BeforeEach
    void setup() {
        filtroService = new FiltroService();
        usuario = criarUsuario();
    }

    @Test
    void deveRetornarFilmeCompativel() {
        Filme filme = new Filme("Matrix", 1999, 120, 90, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        List<Filme> resultado = filtroService.filtrar(usuario, List.of(filme));
        assertEquals(1, resultado.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremFilmes() {
        List<Filme> resultado = filtroService.filtrar(usuario, new ArrayList<>());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRemoverFilmeJaAssistido() {
        Filme filme = new Filme("Vingadores", 2019, 140, 95, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        usuario.getPerfil().getAssistidos().add(filme);

        List<Filme> resultado = filtroService.filtrar(usuario, List.of(filme));
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRemoverFilmePorIdioma() {
        Filme filme = new Filme("Anime Japones", 2022, 110, 88, ClassIndicativa.MAIORDE14, Idioma.JA,
                Set.of(Genero.AVENTURA));

        List<Filme> resultado = filtroService.filtrar(usuario, List.of(filme));
        assertEquals(0, resultado.size());
    }

    @Test
    void deveRetornarFilmeQuandoIdiomaEGeneroForemCompativeis() {
        Filme filme = new Filme("Aventura Épica", 2020, 120, 85, ClassIndicativa.MAIORDE14, Idioma.PT,
                Set.of(Genero.AVENTURA));

        List<Filme> resultado = filtroService.filtrar(usuario, List.of(filme));
        assertFalse(resultado.isEmpty());
    }
}
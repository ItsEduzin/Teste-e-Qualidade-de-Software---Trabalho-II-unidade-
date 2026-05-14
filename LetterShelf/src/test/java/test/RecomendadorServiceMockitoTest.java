package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import Model.*;
import Model.Enums.*;
import Services.*;

public class RecomendadorServiceMockitoTest {

    private RecomendadorService service;

    private CatalogoFilmesAPI catalogoMock;
    private HistoricoUsuarioRepository historicoMock;
    private NotificadorPush notificadorMock;
    private GeradorAleatorio geradorMock;

    private Usuario usuario;

    @BeforeEach
    void setup() throws Exception {

        service = new RecomendadorService();

        catalogoMock = mock(CatalogoFilmesAPI.class);
        historicoMock = mock(HistoricoUsuarioRepository.class);
        notificadorMock = mock(NotificadorPush.class);
        geradorMock = mock(GeradorAleatorio.class);

        injetarMock("catalogo", catalogoMock);
        injetarMock("historico", historicoMock);
        injetarMock("notificador", notificadorMock);
        injetarMock("gerador", geradorMock);

        Map<Genero, Double> pesos = new HashMap<>();

        for (Genero g : Genero.values()) {
            pesos.put(g, 0.8);
        }

        usuario = new Usuario("Vitor", 20, 90, 180, ClassIndicativa.MAIORDE18, pesos, Set.of(Idioma.PT),
                new HashMap<>(), new HashSet<>());
    }

    // método para substituir atributos privados
    private void injetarMock(String nomeCampo, Object mock) throws Exception {

        Field field = RecomendadorService.class.getDeclaredField(nomeCampo);

        field.setAccessible(true);

        field.set(service, mock);
    }

    @Test
    void deveRetornarFilmesDaApiMockada() {

        Filme filme = new Filme("Matrix", 1999, 120, 95, ClassIndicativa.MAIORDE14, Idioma.PT, Set.of(Genero.ACAO));

        when(catalogoMock.buscarTodos()).thenReturn(List.of(filme));

        ArrayList<Filme> resultado = service.Recomendar(usuario, 1, new ArrayList<>());

        assertFalse(resultado.isEmpty());
    }

    @Test
    void deveSalvarHistorico() {

        Filme filme = new Filme("Interestelar", 2014, 160, 90, ClassIndicativa.MAIORDE12, Idioma.PT,
                Set.of(Genero.FICCAO_CIENTIFICA));

        when(catalogoMock.buscarTodos()).thenReturn(List.of(filme));

        service.Recomendar(usuario, 1, new ArrayList<>());

        // verifica se o repository foi chamado
        verify(historicoMock).salvarHistorico(eq(usuario), anyList());
    }

    @Test
    void deveEnviarNotificacaoUmaVez() {

        Filme filme = new Filme("Avatar", 2009, 150, 88, ClassIndicativa.MAIORDE12, Idioma.PT, Set.of(Genero.AVENTURA));

        when(catalogoMock.buscarTodos()).thenReturn(List.of(filme));

        service.Recomendar(usuario, 1, new ArrayList<>());

        verify(notificadorMock, times(1)).enviarNotificacao(any(), anyString());
    }

    @Test
    void deveLancarErroQuandoApiFalhar() {

        when(catalogoMock.buscarTodos()).thenThrow(new RuntimeException("API offline"));

        assertThrows(RuntimeException.class, () -> {
            service.Recomendar(usuario, 1, new ArrayList<>());
        });
    }

    @Test
    void deveCapturarMensagemDaNotificacao() {

        Filme filme = new Filme("Titanic", 1997, 180, 90, ClassIndicativa.MAIORDE12, Idioma.PT, Set.of(Genero.ROMANCE));

        when(catalogoMock.buscarTodos()).thenReturn(List.of(filme));

        service.Recomendar(usuario, 1, new ArrayList<>());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(notificadorMock).enviarNotificacao(eq(usuario), captor.capture());

        String mensagem = captor.getValue();

        assertEquals("Filmes recomendados!", mensagem);
    }

    @Test
    void deveRetornarValoresSequenciais() {

        when(geradorMock.gerarNumero(10)).thenReturn(1, 5, 8);

        assertEquals(1, geradorMock.gerarNumero(10));

        assertEquals(5, geradorMock.gerarNumero(10));

        assertEquals(8, geradorMock.gerarNumero(10));
    }
}
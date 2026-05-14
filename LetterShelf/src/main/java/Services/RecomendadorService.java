package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.Filme;
import Model.ScoreFilme;
import Model.Usuario;

public class RecomendadorService {

    UserScoreData data;
    Ranqueamento ranqueamento;
    FiltroService filtro;
    CalculaScoreService calculaScore;
    GeradorJustificativaService geradorJustificativa;

    CatalogoFilmesAPI catalogo;
    HistoricoUsuarioRepository historico;
    NotificadorPush notificador;
    GeradorAleatorio gerador;

    public RecomendadorService() {

        data = new UserScoreData();
        ranqueamento = new Ranqueamento();
        filtro = new FiltroService();
        calculaScore = new CalculaScoreService();
        geradorJustificativa = new GeradorJustificativaService();

        catalogo = new CatalogoFilmesAPI();
        historico = new HistoricoUsuarioRepository();
        notificador = new NotificadorPush();
        gerador = new GeradorAleatorio();
    }

    public ArrayList<Filme> Recomendar(
            Usuario user,
            int qntd,
            List<Filme> FilmesDisponiveis) {

        // BUSCA DA API EXTERNA
        List<Filme> filmesAPI = catalogo.buscarTodos();

        if (data.CheckUser(user)) {

            Map<Filme, ScoreFilme> scores = data.getUserScores(user);

            ArrayList<Filme> recomendados = ranqueamento.ranquear(
                    user,
                    scores,
                    qntd);

            historico.salvarHistorico(
                    user,
                    recomendados);

            notificador.enviarNotificacao(
                    user,
                    "Filmes recomendados!");

            return recomendados;

        } else {

            Map<Filme, ScoreFilme> scores = data.CriarUser(user);

            List<Filme> filmesApropiados = filtro.filtrar(
                    user,
                    filmesAPI);

            calculaScore.definirScores(
                    user,
                    filmesApropiados,
                    scores);

            ArrayList<Filme> recomendados = ranqueamento.ranquear(
                    user,
                    scores,
                    qntd);

            historico.salvarHistorico(
                    user,
                    recomendados);

            notificador.enviarNotificacao(
                    user,
                    "Filmes recomendados!");

            return recomendados;
        }
    }

    public ArrayList<String> RecomendadosToString(
            Usuario user,
            int qntd,
            List<Filme> FilmesDisponiveis) {

        ArrayList<Filme> recomendados = Recomendar(
                user,
                qntd,
                FilmesDisponiveis);

        Map<Filme, ScoreFilme> score = data.getUserScores(user);

        ArrayList<String> recomendadosString = new ArrayList<>();

        for (Filme filme : recomendados) {

            recomendadosString.add(
                    geradorJustificativa.gerar(
                            user,
                            filme,
                            score.get(filme)));
        }

        return recomendadosString;
    }
}
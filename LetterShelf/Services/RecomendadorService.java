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

    public RecomendadorService(){
        data= new UserScoreData();
        ranqueamento= new Ranqueamento();
        filtro = new FiltroService();
        calculaScore = new CalculaScoreService();
    }

    public ArrayList<Filme> Recomendar(Usuario user, int qntd, List<Filme> FilmesDisponiveis){
        if (data.CheckUser(user)) {
            Map<Filme,ScoreFilme> scores= data.getUserScores(user);
            return ranqueamento.ranquear(user, scores, qntd);
        } else {
            Map<Filme,ScoreFilme> scores = data.CriarUser(user);
            List<Filme> filmesApropiados= filtro.filtrar(user, FilmesDisponiveis);
            calculaScore.definirScores(user, filmesApropiados, scores);
            return ranqueamento.ranquear(user, scores, qntd);
        }
    }

    
}
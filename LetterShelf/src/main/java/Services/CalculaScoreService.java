package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import Model.Filme;
import Model.PerfilCine;
import Model.ScoreFilme;
import Model.Usuario;
import Model.Enums.Genero;

public class CalculaScoreService {

    private static final double PESO_GENERO = 0.50;
    private static final double PESO_DURACAO = 0.20;
    private static final double PESO_POPULARIDADE = 0.15;
    private static final double PESO_AFINIDADE = 0.15;

    public Map<Filme, ScoreFilme> definirScores(Usuario user, List<Filme> filmes,
            Map<Filme, ScoreFilme> relacaoFilmeScore) {
        Map<Genero, Double> bonusGenero = bonusPorGenero(user);

        for (Filme filme : filmes) {
            double sGen = definirScoreGenero(user, filme);
            double sDur = definirScoreDuracao(user, filme);
            double sPop = definirScorePopularidade(filme);
            double sAfi = definirScoreAfinidade(user, filme, bonusGenero);
            double total = sGen * PESO_GENERO + sDur * PESO_DURACAO + sPop * PESO_POPULARIDADE + sAfi * PESO_AFINIDADE;

            relacaoFilmeScore.put(filme, new ScoreFilme(sGen, sDur, sPop, sAfi, total));

        }

        return relacaoFilmeScore;
    }

    public void atualizarScores(Usuario user, ArrayList<Filme> filmes) {
        Map<Filme, ScoreFilme> relacaoFilmeScore = user.getPerfil().getRelacaoFilmeScore();
        Map<Genero, Double> bonusGenero = bonusPorGenero(user);

        for (Filme filme : filmes) {
            ScoreFilme scoreFilmeAtual = relacaoFilmeScore.get(filme);
            double total = scoreFilmeAtual.getScoreTotal() - scoreFilmeAtual.getScoreAfinidade();
            scoreFilmeAtual.setScoreAfinidade(definirScoreAfinidade(user, filme, bonusGenero));
            total += scoreFilmeAtual.getScoreAfinidade();
            scoreFilmeAtual.setScoreTotal(total);
        }
    }

    private double definirScoreGenero(Usuario user, Filme filme) {
        PerfilCine perfil = user.getPerfil();

        double soma = 0;
        int generos = 0;
        for (Genero gen : Genero.values()) {
            if (filme.getGeneros(gen)) {
                generos++;
                soma += perfil.getPesos().getOrDefault(gen, 0.0);
            }
        }
        double ans = soma / ((double) generos);

        return ans;

    }

    private double definirScoreDuracao(Usuario user, Filme filme) {
        PerfilCine perfil = user.getPerfil();
        int tolerancia = 4;
        double punicao = 0.5, total = 1;

        if (filme.getDuracao() >= perfil.getDuracaoMin() && filme.getDuracao() <= perfil.getDuracaoMax()) {
            return total;
        }

        int abs = Math.min(Math.abs(perfil.getDuracaoMin() - filme.getDuracao()),
                Math.abs(perfil.getDuracaoMax() - filme.getDuracao()));
        int valor = (abs + tolerancia - 1) / tolerancia;
        return Math.max(total - (punicao * valor), 0);

    }

    private double definirScorePopularidade(Filme filme) {
        return (double) filme.getPopularidade() / 100;
    }

    private double definirScoreAfinidade(Usuario user, Filme filme, Map<Genero, Double> bonusGenero) {
        double bonus = 0;
        for (Genero gen : filme.getGeneros()) {
            bonus += bonusGenero.getOrDefault(gen, 0.0);
        }
        if (bonus > 1) {
            bonus = 1;
        }

        return bonus;
    }

    private Map<Genero, Double> bonusPorGenero(Usuario user) {
        PerfilCine perfil = user.getPerfil();
        double bonus = 0.05;
        int notaMin = 4;
        Map<Genero, Double> bonusGenero = new HashMap<>();
        for (Map.Entry<Filme, Integer> entry : perfil.getNotas().entrySet()) {
            if (entry.getValue() < notaMin) {
                continue;
            }

            Filme filme = entry.getKey();
            for (Genero gen : filme.getGeneros()) {
                bonusGenero.put(gen, bonusGenero.getOrDefault(gen, 0.0) + bonus);
            }
        }
        return bonusGenero;
    }
}

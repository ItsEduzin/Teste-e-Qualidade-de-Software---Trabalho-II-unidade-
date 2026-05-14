package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Filme;
import Model.ScoreFilme;
import Model.Usuario;
import Model.Enums.*;;

public class GeradorJustificativaService {
    public String gerar(Usuario user, Filme filme, ScoreFilme score) {
        List<String> motivos = new ArrayList<>();

        if (score.getScoreGenero() > 70.0) {
            String generosAmados = filme.getGeneros().stream()
                .filter(g -> user.getPerfil().getPesos().getOrDefault(g, 0.0) >= 0.5)
                .map(Genero::getString)
                .collect(Collectors.joining(" e "));
            
            motivos.add("combina muito com seu gosto por " + generosAmados);
        }

        if (score.getScoreDuracao() == 100.0) {
            motivos.add("tem a duração exata que você procura");
        }

        if (score.getScoreAfinidade() > 0) {
            motivos.add("você avaliou positivamente filmes com esse mesmo estilo");
        }
        
        if (filme.getPopularidade() > 90) {
            motivos.add("é um sucesso absoluto de crítica");
        }

        if (motivos.isEmpty()) {
            return "Recomendamos " + filme.getNome() + " para você sair da sua zona de conforto!";
        }

        return "Recomendamos " + filme.getNome() + " porque " + String.join(", além de que ", motivos) + ".";
    }
}


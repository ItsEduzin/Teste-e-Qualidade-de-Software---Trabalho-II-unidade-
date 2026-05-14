package Services;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

import Model.Filme;
import Model.ScoreFilme;
import Model.Usuario;

public class Ranqueamento {
    public ArrayList<Filme> ranquear(Usuario user, Map<Filme, ScoreFilme> scores, int qntd) {
        PriorityQueue<Filme> listaAuxiliar = new PriorityQueue<>((filme1, filme2) -> Double
                .compare(scores.get(filme1).getScoreTotal(), scores.get(filme2).getScoreTotal()));
        for (Map.Entry<Filme, ScoreFilme> entry : scores.entrySet()) {
            listaAuxiliar.add(entry.getKey());
        }

        ArrayList<Filme> listaFinal = new ArrayList<>();
        for (int i = 0; i < qntd && !listaAuxiliar.isEmpty(); i++) {
            listaFinal.add(listaAuxiliar.peek());
            listaAuxiliar.remove();
        }
        return listaFinal;
    }
}

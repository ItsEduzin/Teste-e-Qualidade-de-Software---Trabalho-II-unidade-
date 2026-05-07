import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

public class RecomendadorService {
    private static final double[] peso = {0.5,0.2,0.15,0.15};
    Vector<HashMap<Usuario,HashMap<Filme,Double>>> scores = new Vector<HashMap<Usuario,HashMap<Filme,Double>>>(5);

    public void definirScores(Usuario user, Filme[] filmes){
        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).put(user, new HashMap<Filme,Double>());
        }
        Vector<Filme> filmesApropiados= FiltrarPossibilidades(user, filmes);
        definirScoreGenero(user, filmesApropiados);
        definirScoreDuracao(user, filmesApropiados);
        definirScorePopularidade(user, filmesApropiados);
        atualizarScoreAfinidade(user);
    }

    private void definirScoreGenero(Usuario user, Vector<Filme> filmes){
        PerfilCine perfil=user.getPerfil();
        for (Filme filme : filmes) {
            double soma=0;
            int generos=0;
            for (int i = 0; i < perfil.getPesos().size(); i++) {
                if(filme.getGeneros().get(i)){
                    generos++;
                    soma+=perfil.getPesos().get(i);
                }
            }
            double ans= soma/((double)generos);
            scores.get(0).get(user).put(filme, ans);
        }
    }

    private void definirScoreDuracao(Usuario user, Vector<Filme> filmes){
        PerfilCine perfil=user.getPerfil();
        int tolerancia = 4;
        double punicao = 0.5, total=1;
        for (Filme filme : filmes) {
            if (filme.getDuracao()>=perfil.getDuracaoMin() && filme.getDuracao()<=perfil.getDuracaoMax()) {
                scores.get(1).get(user).put(filme, total);
                continue;
            } 

            if (filme.getDuracao()>perfil.getDuracaoMax()) {
                int abs=filme.getDuracao()-perfil.getDuracaoMax();
                int valor=(abs+tolerancia-1)/tolerancia;
                scores.get(1).get(user).put(filme, total-(valor*punicao));
                continue;
            }

            int abs=perfil.getDuracaoMin()-filme.getDuracao();
            int valor=(abs+tolerancia-1)/tolerancia;
            scores.get(1).get(user).put(filme, total-(valor*punicao));
        }
    }

    private void definirScorePopularidade(Usuario user, Vector<Filme> filmes){
        for (Filme filme : filmes) {
            scores.get(2).get(user).put(filme, (double)filme.getPopularidade());
        }
    }

    private void atualizarScoreAfinidade(Usuario user){
        Vector<Double> bonusGenero= bonusPorGenero(user);

        for (Map.Entry<Filme,Double> filmeListado : scores.get(3).get(user).entrySet()) {
            Filme filme=filmeListado.getKey();
            double bonus=0;
            for (int i=0; i<filme.getGeneros().size(); i++) {
                if (filme.getGeneros().get(i)) {
                    bonus+=bonusGenero.get(i);
                }
            }
            if (bonus>1) {
                bonus=1;
            }

            scores.get(3).get(user).put(filme, bonus);
            for (int i = 0; i < peso.length; i++) {
                scores.get(4).get(user).put(filme, scores.get(i).get(user).get(filme)*peso[i]);
            }
        }
    }

    private Vector<Double> bonusPorGenero(Usuario user){
        PerfilCine perfil=user.getPerfil();
        double bonus=0.05;
        int notaMin=4;
        Vector<Double> bonusGenero= new Vector<>(8);
        for (Map.Entry<Filme,Integer> entry : perfil.getNotas().entrySet()) {
            if (entry.getValue()<notaMin) {
                continue;
            }
            
            for (int i = 0; i < 8; i++) {
                if (entry.getKey().generos.get(i)) {
                    bonusGenero.set(i, bonusGenero.get(i)+bonus);
                }
            }
        }

        return bonusGenero;
    }

    private Vector<Filme> FiltrarPossibilidades(Usuario user, Filme[] filmes){
        Vector<Filme> filmesApropiados= new Vector<>();
        PerfilCine perfil=user.getPerfil();
        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i].getClassInd()>perfil.getClassIndMax() || perfil.assistiuFilme(filmes[i])) {
                continue;
            }
            if (perfil.falaIdioma(filmes[i].getIdioma())) {
                filmesApropiados.add(filmes[i]);
            }
        }

        return filmesApropiados;
    }

    public void Recomendar(Usuario user){
        Vector<Filme> ListaOrdenada = Ranqueamento(user);

        for (int i = 0; i < ListaOrdenada.size(); i++) {
            System.out.println(i+1 + "");
        }
    }

    private Vector<Filme> Ranqueamento(Usuario user){
        PriorityQueue<Filme> listaAuxiliar = new PriorityQueue<>((filme1, filme2) -> Double.compare(scores.get(4).get(user).get(filme1), scores.get(4).get(user).get(filme2)));
        for (Map.Entry<Filme,Integer> entry : user.getPerfil().getNotas().entrySet()) {
            listaAuxiliar.add(entry.getKey());
        }
        
        Vector<Filme> listaFinal = new Vector<>();
        while (!listaAuxiliar.isEmpty()) {
            listaFinal.add(listaAuxiliar.peek());
            listaAuxiliar.remove();
        }

        return listaFinal;
    }
}

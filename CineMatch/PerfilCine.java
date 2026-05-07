import java.util.HashSet;
import java.util.HashMap;
import java.util.Vector;

public class PerfilCine {
    private Vector<Double> Pesos=new Vector<Double>(8);
    private int duracaoMin, duracaoMax;
    private int classIndMax;
    private HashSet<String> idiomas = new HashSet<String>();
    private HashMap<Filme,Integer> notas = new HashMap<Filme, Integer>();
    private HashSet<Filme> assistidos = new HashSet<Filme>();

    
    public Boolean falaIdioma(String idioma){
        return idiomas.contains(idioma);
    }
    
    public Boolean assistiuFilme(Filme filme){
        return assistidos.contains(filme);
    }

    public HashSet<Filme> getAssistidos() {
        return assistidos;
    }

    public int getDuracaoMax() {
        return duracaoMax;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public int getClassIndMax() {
        return classIndMax;
    }

    public Vector<Double> getPesos() {
        return Pesos;
    }

    public HashMap<Filme, Integer> getNotas() {
        return notas;
    }
}

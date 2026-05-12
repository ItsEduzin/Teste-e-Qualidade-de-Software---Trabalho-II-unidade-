package Model;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Model.Enums.*;

public class PerfilCine {
    private int duracaoMin, duracaoMax;
    private ClassIndicativa classIndMax;
    private Map<Genero, Double> pesos=new HashMap<Genero,Double>();
    private Set<Idioma> idiomasConhecidos = new HashSet<Idioma>();
    private Map<Filme,Integer> notas = new HashMap<Filme, Integer>();
    private Set<Filme> assistidos = new HashSet<Filme>();

    private Map<Filme, ScoreFilme> relacaoFilmeScore;

    int GenBit=0;

    public PerfilCine(int duracaoMin, int duracaoMax, ClassIndicativa classIndMax, Map<Genero,Double> pesos, Set<Idioma> idiomasConhecidos, Map<Filme, Integer> notas, Set<Filme> assistidos){
        this.assistidos=assistidos;
        this.duracaoMin=duracaoMin;
        this.duracaoMax = duracaoMax;
        this.pesos=pesos;
        this.idiomasConhecidos=idiomasConhecidos;
        this.notas=notas;
        this.classIndMax=classIndMax;

        int bit=0;
        for (Genero genero : Genero.values()) {
            if (pesos.get(genero)!=0) {
                GenBit+=(1<<bit);
            }
            bit++;
        }
    }
    
    public int getGenBit() {
        return GenBit;
    }

    public Boolean falaIdioma(Idioma idioma){
        return idiomasConhecidos.contains(idioma);
    }
    
    public Boolean assistiuFilme(Filme filme){
        return assistidos.contains(filme);
    }

    public Set<Filme> getAssistidos() {
        return assistidos;
    }

    public int getDuracaoMax() {
        return duracaoMax;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public ClassIndicativa getClassIndMax() {
        return classIndMax;
    }

    public Map<Genero,Double> getPesos() {
        return pesos;
    }

    public Double getPesos(Genero gen) {
        return pesos.get(gen);
    }

    public Map<Filme, Integer> getNotas() {
        return notas;
    }

    public Map<Filme, ScoreFilme> getRelacaoFilmeScore() {
        return relacaoFilmeScore;
    }
}

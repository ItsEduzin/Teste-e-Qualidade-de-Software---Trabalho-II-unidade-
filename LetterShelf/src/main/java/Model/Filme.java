package Model;

import java.util.HashSet;
import java.util.Set;

import Model.Enums.ClassIndicativa;
import Model.Enums.Genero;
import Model.Enums.Idioma;

public class Filme {
    String nome;
    int anoDeLancamento, duracao, popularidade;
    ClassIndicativa classInd;
    Idioma idioma;
    Set<Genero> generos = new HashSet<Genero>();
    int GenBit = 0;

    public Filme(String nome, int ano, int duracao, int pop, ClassIndicativa classInd, Idioma idioma,
            Set<Genero> generos) {
        this.nome = nome;
        anoDeLancamento = ano;
        this.duracao = duracao;
        this.popularidade = pop;
        this.classInd = classInd;
        this.idioma = idioma;
        this.generos = generos;

        int bit = 0;
        for (Genero genero : Genero.values()) {
            if (generos.contains(genero)) {
                GenBit += (1 << bit);
            }
            bit++;
        }
    }

    public int getGenBit() {
        return GenBit;
    }

    public ClassIndicativa getClassInd() {
        return classInd;
    }

    public boolean getGeneros(Genero gen) {
        return generos.contains(gen);
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public int getDuracao() {
        return duracao;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public String getNome() {
        return nome;
    }
}

import java.util.Vector;

public class Filme {
    String nome, idioma;
    int anoDeLancamento, duracao, classInd, popularidade;
    Vector<Boolean> generos= new Vector<Boolean>(8);

    public int getClassInd() {
        return classInd;
    }

    public Vector<Boolean> getGeneros() {
        return generos;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getIdioma() {
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

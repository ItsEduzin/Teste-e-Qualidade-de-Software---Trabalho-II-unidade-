package Model.Enums;

public enum Genero {
    ACAO(0, "Acao"),
    COMEDIA(1, "Comedia"),
    DRAMA(2, "Drama"),
    FICCAO_CIENTIFICA(3, "Ficcao Cientifica"),
    ROMANCE(4, "Romance"), TERROR(5, "Terror"),
    DOCUMENTARIO(6, "Documentario"),
    AVENTURA(7, "Aventura");

    int id;
    String nome;

    Genero(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getString() {
        return nome;
    }
}

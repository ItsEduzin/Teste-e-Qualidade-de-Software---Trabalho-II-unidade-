public enum Genero {
    ACAO(0, "ACAO"), 
    COMEDIA(1, "COMEDIA"), 
    DRAMA(2, "DRAMA"), 
    FICCAO_CIENTIFICA(3,"FICCAO_CIENTIFICA"), 
    ROMANCE(4,"ROMANCE"), TERROR(5,"TERROR"), 
    DOCUMENTARIO(6,"DOCUMENTARIO"), 
    AVENTURA(7,"AVENTURA");

    int id;
    String nome;
    Genero(int id, String nome){
        this.id=id;
        this.nome=nome;
    }

    public int getId() {
        return id;
    }

    public String getString(){
        return nome;
    } 
}



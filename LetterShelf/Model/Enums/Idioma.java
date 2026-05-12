package Model.Enums;

public enum Idioma {
    PT(0,"PT"),
    EN(1,"EN"),
    ES(2,"ES"),
    JA(3,"JA"),
    DE(4,"DE");

    int id;
    String sigla;
    Idioma(int id, String sigla){
        this.id=id;
        this.sigla=sigla;
    }
    

    public int getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }
}

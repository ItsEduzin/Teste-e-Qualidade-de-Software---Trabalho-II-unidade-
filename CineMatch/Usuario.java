public class Usuario {

    private String nome;
    private int idade;
    private PerfilCine perfil;

    public Usuario(String nome, int idade){
        this.nome=nome;
        this.idade=idade;
        perfil= new PerfilCine();
        
    }
 
    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }
    
    public PerfilCine getPerfil() {
        return perfil;
    }

}
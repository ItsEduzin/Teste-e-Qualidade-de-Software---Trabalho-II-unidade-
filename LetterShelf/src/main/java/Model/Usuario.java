package Model;

import java.util.Map;
import java.util.Set;

import Exceptions.DuracaoInvalidaException;
import Exceptions.PesoInvalidoException;
import Model.Enums.ClassIndicativa;
import Model.Enums.Genero;
import Model.Enums.Idioma;

public class Usuario {

    private String nome;
    private int idade;
    private PerfilCine perfil;

    public Usuario(String nome, int idade, int duracaoMin, int duracaoMax, ClassIndicativa classIndMax,
            Map<Genero, Double> pesos, Set<Idioma> idiomasConhecidos, Map<Filme, Integer> notas,
            Set<Filme> assistidos) {
        this.nome = nome;
        this.idade = idade;
        try {
            this.perfil = new PerfilCine(duracaoMin, duracaoMax, classIndMax, pesos, idiomasConhecidos, notas,
                    assistidos);
        } catch (DuracaoInvalidaException | PesoInvalidoException e) {
            e.printStackTrace();
        }
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
package Services;

import java.util.ArrayList;
import java.util.List;
import Model.*;

public class FiltroService {
    
    public List<Filme> filtrar(Usuario user, List<Filme> filmesDisponiveis){
        List<Filme> filmesApropiados = new ArrayList<Filme>();
        PerfilCine perfil=user.getPerfil();
        for (int i = 0; i < filmesDisponiveis.size(); i++) {
            if (filmesDisponiveis.get(i).getClassInd().getId()>perfil.getClassIndMax().getId() || perfil.assistiuFilme(filmesDisponiveis.get(i)) || (perfil.getGenBit()&filmesDisponiveis.get(i).getGenBit())==0) {
                continue;
            }
            if (perfil.falaIdioma(filmesDisponiveis.get(i).getIdioma())) {
                filmesApropiados.add(filmesDisponiveis.get(i));
            }
        }

        return filmesApropiados;
    }
}

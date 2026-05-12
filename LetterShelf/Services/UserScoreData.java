package Services;

import java.util.HashMap;
import java.util.Map;
import Model.*;


public class UserScoreData {
    Map<Usuario,HashMap<Filme,ScoreFilme>> scoresDosUsuarios = new HashMap<Usuario,HashMap<Filme,ScoreFilme>>();

    public HashMap<Filme,ScoreFilme> CriarUser(Usuario usuario){
        scoresDosUsuarios.put(usuario,new HashMap<Filme,ScoreFilme>());
        return getUserScores(usuario);
    }

    public HashMap<Filme,ScoreFilme> getUserScores(Usuario user){
        return scoresDosUsuarios.get(user);
    }

    public Boolean CheckUser(Usuario usuario){
        return scoresDosUsuarios.containsKey(usuario);
    }
}

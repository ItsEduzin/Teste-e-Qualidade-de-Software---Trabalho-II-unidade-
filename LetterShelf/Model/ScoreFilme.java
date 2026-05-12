package Model;

public class ScoreFilme {
    private double scoreTotal, scoreGenero, scoreDuracao, scoreAfinidade, scorePopularidade;
    private String justificativa;

    public ScoreFilme(double scoreGenero, double scoreDuracao, double scorePopularidade, double scoreAfinidade, double scoreTotal) {
        this.scoreGenero = scoreGenero;
        this.scoreDuracao = scoreDuracao;
        this.scorePopularidade = scorePopularidade;
        this.scoreAfinidade = scoreAfinidade;
        this.scoreTotal = scoreTotal;
    }
    
    public double getScoreAfinidade() {
        return scoreAfinidade;
    }
    public double getScoreDuracao() {
        return scoreDuracao;
    }
    public double getScoreTotal() {
        return scoreTotal;
    }
    public double getScoreGenero() {
        return scoreGenero;
    }
    public double getScorePopularidade() {
        return scorePopularidade;
    }
    
    public void setScoreAfinidade(double scoreAfinidade) { 
        this.scoreAfinidade = scoreAfinidade; 
    }
    public void setScoreTotal(double scoreTotal) { 
        this.scoreTotal = scoreTotal; 
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getJustificativa() {
        return justificativa;
    }
    
}

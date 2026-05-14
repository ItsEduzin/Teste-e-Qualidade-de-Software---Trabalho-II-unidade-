package Model.Enums;

public enum ClassIndicativa {
    LIVRE(0, "LIVRE"),
    MAIORDE10(1, "10"),
    MAIORDE12(2, "12"),
    MAIORDE14(3, "14"),
    MAIORDE16(4, "16"),
    MAIORDE18(5, "18");

    int id;
    String text;

    ClassIndicativa(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}

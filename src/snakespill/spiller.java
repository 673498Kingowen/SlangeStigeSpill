package snakespill;

public class spiller {
    private String name;
    private int position;
    private boolean kanStarte;

    public spiller(String name) {
        this.name = name;
        this.position = 1;
        this.kanStarte = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean kanStarte() {
        return kanStarte;
    }

    public void setKanStarte(boolean kanStarte) {
        this.kanStarte = kanStarte;
    }
}


package snakespill;
import java.util.Random;

public class terning {
    private final Random random;

    public terning() {
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}

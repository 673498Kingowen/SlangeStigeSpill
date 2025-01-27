package snakespill;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Integer, Integer> spill;

    public Board(int size) {
        spill = new HashMap<>();
        run();
    }

    private void run() {
        // slanger
        spill.put(16, 6);
        spill.put(47, 26);
        spill.put(49, 11);
        spill.put(56, 53);
        spill.put(62, 19);
        spill.put(87, 24);
        spill.put(93, 73);
        spill.put(95, 75);
        spill.put(98, 78);

        // stige
        spill.put(2, 38);
        spill.put(7, 14);
        spill.put(8, 31);
        spill.put(15, 26);
        spill.put(28, 84);
        spill.put(36, 44);
        spill.put(51, 67);
        spill.put(71, 91);
        spill.put(78, 98);
    }

    public int sjekkForSlangeEllerStige(int position) {
        return spill.getOrDefault(position, position);
    }
}

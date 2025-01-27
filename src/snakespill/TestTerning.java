package snakespill;

import java.util.Queue;
import java.util.LinkedList;

//test klasse for terning
public class TestTerning extends terning{
    private final Queue<Integer> testRolls = new LinkedList<>();

    public void addRoll(int roll) {
        testRolls.add(roll);
    }

    @Override
    public int roll() {
        return testRolls.isEmpty() ? super.roll() : testRolls.poll();
    }
}

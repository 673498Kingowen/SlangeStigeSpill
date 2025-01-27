package test;
import org.junit.jupiter.api.Test;

import snakespill.*;


import static org.junit.jupiter.api.Assertions.*;

public class spillTest {
    @Test
    void testDiceRoll() {
        terning dice = new terning();
        for (int i = 0; i < 1000; i++) {
            int roll = dice.roll();
            assertTrue(roll >= 1 && roll <= 6, "Terning er out of bounds: " + roll);
        }
    }

    @Test
    void testSjekkForSlangeEllerStige() {
        Board board = new Board(100);
        assertEquals(6, board.sjekkForSlangeEllerStige(16), "Slange burde flytte spiller til 6");
        assertEquals(38, board.sjekkForSlangeEllerStige(2), "Stige burde flytte spiller til 38");
        assertEquals(19, board.sjekkForSlangeEllerStige(62), "Slange burde flytte spiller til 19");
        assertEquals(84, board.sjekkForSlangeEllerStige(28), "Stige burde flytte spiller til 84");
        assertEquals(73, board.sjekkForSlangeEllerStige(93), "Slange burde flytte spiller til 73");
    }

    @Test
    void testSpillerPosition() {
        spiller player = new spiller("TestSpiller");
        assertEquals(1, player.getPosition());
        player.setPosition(10);
        assertEquals(10, player.getPosition());
        player.setKanStarte(true);
        assertTrue(player.kanStarte());
    }

    @Test
    void testGameplay() {
        spillImpl game = new spillImpl();
        spiller player = new spiller("Player1");
        game.flyttSpiller(player, 6);
        assertEquals(7, player.getPosition());
    }

    @Test
    void testGameplayWin() {
        spillImpl game = new spillImpl();
        spiller player = new spiller("Player1");
        game.flyttSpiller(player, 95);
        assertEquals(96, player.getPosition());
        game.flyttSpiller(player, 6);
        assertEquals(96, player.getPosition());
        game.flyttSpiller(player, 4);
        assertEquals(100, player.getPosition());
    }

    @Test
    void testTrilleSeksTreGanger() {
        TestTerning testDice = new TestTerning();
        testDice.addRoll(6);
        testDice.addRoll(6);
        testDice.addRoll(6);

        spillImpl game = new spillImpl(testDice);
        spiller player = new spiller("Player1");
        game.spillere.add(player);
        player.setKanStarte(true);
        game.play(true);
        assertEquals(0, player.getPosition());
        assertFalse(player.kanStarte());
    }




}

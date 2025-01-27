package snakespill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class spillImpl {
    private static final int BOARD_SIZE = 100;
    public final List<spiller> spillere;
    private final Board board;
    public terning dice;

    public spillImpl() {
        this.spillere = new ArrayList<>();
        this.board = new Board(BOARD_SIZE);
        this.dice = new terning();
    }

    // Testing av testTrilleSeksTreGanger()
    public spillImpl(terning customDice) {
        this.spillere = new ArrayList<>();
        this.board = new Board(BOARD_SIZE);
        this.dice = customDice;
    }

    public void start() {
        System.out.println("Slange og Stigespill!");
        setupPlayers();
        play(true);
    }

    private void setupPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hvor mange spillere? Velg mellom 2 - 4 spillere: ");
        int antallSpillere = scanner.nextInt();
        while (antallSpillere < 2 || antallSpillere > 4) {
            System.out.print("Ugyldig antall spillere. Velg mellom 2 - 4: ");
            antallSpillere = scanner.nextInt();
        }
        for (int i = 1; i <= antallSpillere; i++) {
            System.out.print("Skriv inn navn for spiller " + i + ": ");
            String navn = scanner.next();
            spillere.add(new spiller(navn));
        }
    }

    // testTrilleSeksTreGanger() med (boolean debug)
    public void play(boolean debug) {
        boolean vunnet = false;
        while (!vunnet) {
            for (spiller spiller : spillere) {
                System.out.println("\n" + spiller.getName() + " sin tur.");
                int trill;
                int sekserTeller = 0;
                do {
                    trill = dice.roll();
                    System.out.println(spiller.getName() + " kastet en " + trill);
                    if (trill == 6) {
                        if (spiller.kanStarte()) {
                            sekserTeller++;
                        }
                        if (sekserTeller == 3) {
                            System.out.println(spiller.getName() + " kastet 6 tre ganger på rad! Går tilbake til start.");
                            spiller.setPosition(0);
                            spiller.setKanStarte(false);
                            if (debug) return;
                            break;
                        }
                        if (!spiller.kanStarte()) {
                            spiller.setKanStarte(true);
                            System.out.println(spiller.getName() + " kan nå starte! Kast på nytt for å bevege deg.");
                            continue;
                        }
                        flyttSpiller(spiller, trill);
                    } else {
                        sekserTeller = 0;
                        if (spiller.kanStarte()) {
                            flyttSpiller(spiller, trill);
                        } else {
                            System.out.println(spiller.getName() + " må kaste en 6 for å starte.");
                        }
                    }
                    if (spiller.getPosition() == BOARD_SIZE) {
                        System.out.println(spiller.getName() + " har vunnet spillet!");
                        vunnet = true;
                        if (debug) return;
                        break;
                    }
                } while (trill == 6);
            }
        }
    }

    public void flyttSpiller(spiller spiller, int roll) {
        int nyPosition = spiller.getPosition() + roll;
        if (nyPosition > BOARD_SIZE) {
            System.out.println(spiller.getName() + " går forbi brettet og blir stående på rute " + spiller.getPosition());
            return;
        }
        spiller.setPosition(nyPosition);
        System.out.println(spiller.getName() + " flyttet til rute " + nyPosition);
        int nextPosition = board.sjekkForSlangeEllerStige(nyPosition);
        if (nextPosition != nyPosition) {
            spiller.setPosition(nextPosition);
            System.out.println(spiller.getName() + " flyttet til rute " + nextPosition + " pga. en slange eller stige.");
        }
    }
}

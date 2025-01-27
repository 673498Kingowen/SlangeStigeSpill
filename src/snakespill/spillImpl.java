package snakespill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class spillImpl {
    private static final int BOARD_SIZE = 100;
    private final List<spiller> spillere;
    private final Board board;
    private final terning dice;

    public spillImpl() {
        this.spillere = new ArrayList<>();
        this.board = new Board(BOARD_SIZE);
        this.dice = new terning();
    }

    public void start() {
        System.out.println("Slange og Stigespill!");
        setupPlayers();
        play();
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

    public void play() {
        boolean Vunnet = false;
        while (!Vunnet) {
            for (spiller spiller : spillere) {
                System.out.println("\n" + spiller.getName() + " sin tur.");
                int trill = dice.roll();
                System.out.println(spiller.getName() + " kastet en " + trill);
                if (spiller.kanStarte() || trill == 6) {
                    if (!spiller.kanStarte()) {
                        spiller.setKanStarte(true);
                        System.out.println(spiller.getName() + " kan nå starte!");
                    }
                    flyttSpiller(spiller, trill);
                    if (trill == 6) {
                        System.out.println("Du kastet 6! Kast på nytt.");
                        trill = dice.roll();
                        System.out.println(spiller.getName() + " kastet en " + trill);
                        flyttSpiller(spiller, trill);
                    }
                    if (spiller.getPosition() == BOARD_SIZE) {
                        System.out.println(spiller.getName() + " har vunnet spillet!");
                        Vunnet = true;
                        break;
                    }
                } else {
                    System.out.println(spiller.getName() + " må kaste en 6 for å starte.");
                }
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

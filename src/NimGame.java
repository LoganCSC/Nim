import java.util.Scanner;

public class NimGame {

    /** the maximum number of sticks that a player can pick up at once */
    private static final int MAX_STICKS_PICKED = 3;

    /** Scanner used to retrieve player text input */
    private Scanner scanner;


    /** alternates between players asking them how many sticks they want to pick up */
    public void startPlaying() {
        System.out.println("JAVA NIM");
        boolean playing;

        do {
            playing = playGame();
        } while (playing);
    }

    /**
     * Play a complete nim game
     * @return true if the player has requested to play again.
     */
    private boolean playGame() {
        boolean continuePlaying;
        scanner = new Scanner(System.in);
        NimGameState state = new NimGameState();

        do {
            continuePlaying = doPlayerTurn(state);
        } while (continuePlaying && !state.isGameOver());

        return continuePlaying;
    }

    /**
     * @param state nim game state
     * @return true if play it to continue
     */
    private boolean doPlayerTurn(NimGameState state) {
        boolean continuePlaying = true;
        showTurnInfo(state);
        int numSticksToPick = getNumSticksToPick();
        boolean won = state.pickUpSticks(numSticksToPick);

        if (won) {
            System.out.println("Player " + state.getCurrentPlayer() + " Won!");
            continuePlaying = askToPlayAgain();
        }
        else {
            state.advanceToNextPlayer();
        }

        return continuePlaying;
    }

    private void showTurnInfo(NimGameState state) {
        System.out.print("Turn " + state.getTurnCount() + "; Player ");
        System.out.println(state.getCurrentPlayer());
        printSticks(state.getNumSticksRemaining());
    }

    private int getNumSticksToPick() {
        System.out.print("How many sticks would you like to pick? ");
        int num = scanner.nextInt();
        while (num <= 0 || num > MAX_STICKS_PICKED) {
            System.out.println("Please pick a value between 1 and " + MAX_STICKS_PICKED + "!");
            num = scanner.nextInt();
        }
        return num;
    }

    private boolean askToPlayAgain() {
        System.out.print("Do you want to play again? (y/n) ");
        Character pInput = scanner.next().charAt(0);
        return (pInput == 'y' || pInput == 'Y');
    }

    public void printSticks(int numSticks) {
        for (int i=0; i < numSticks; i++) {
            System.out.print("|");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
         new NimGame().startPlaying();
    }
}
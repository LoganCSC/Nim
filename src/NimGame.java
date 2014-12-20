import java.util.Scanner;

public class NimGame {

    /** the maximum number of sticks that a player can pick up at once */
    private static final int MAX_STICKS_PICKED = 3;

    /** alternates between players asking them how many sticks they want to pick up */
    public void startPlaying() {
        System.out.println("JAVA NIM");
        boolean playing;

        do {
            playing = playGame();

        } while (playing);
    }

    private boolean playGame() {
        boolean playing;
        Scanner scanna = new Scanner(System.in);
        NimGameState state = new NimGameState();

        do {
            playing = doPlayerTurn(scanna, state);

        } while (playing && !state.isGameOver());

        return playing;
    }

    private boolean doPlayerTurn(Scanner scanna, NimGameState state) {
        boolean won;
        boolean playing = true;
        System.out.print("Turn " + state.getTurnCount() + "; Player ");
        String player = state.isPlayer1()?  "1" : "2";
        System.out.println(player);

        printSticks(state.getNumSticksRemaining());
        System.out.print("How many sticks would you like to pick? ");
        Integer input = scanna.nextInt();
        if (input > 0 && input <= MAX_STICKS_PICKED) {
            won = state.pickUpSticks(input);

            if (won) {
                System.out.println("Player " + player + " Won!");
                System.out.print("Do you want to play again? (y/n) ");
                Character pInput = scanna.next().charAt(0);
                if (pInput == 'n' || pInput == 'N') {
                    playing = false;
                }
            }
            else {
                state.switchToNextPlayer();
            }
        }
        else {
            System.out.println("Please pick a value between 1 and 3!");
        }
        return playing;
    }

    public void printSticks(int input) {
        for (int i=0; i < input; i++) {
            System.out.print("|");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
         new NimGame().startPlaying();
    }
}
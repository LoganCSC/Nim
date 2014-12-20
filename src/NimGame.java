import java.util.Scanner;

public class NimGame {

    /** alternates between players asking them how many sticks they want to pick up */
    public void startPlaying() {
        System.out.println("JAVA NIM");
        boolean playing = true;
        Scanner scanna = new Scanner(System.in);

        do {

            NimGameState state = new NimGameState();
            boolean won = false;
            do {
                System.out.print("Turn " + state.getTurnCount() + "; Player ");
                String player = state.isPlayer1()?  "1" : "2";
                System.out.println(player);

                printSticks(state.getNumSticksRemaining());
                System.out.print("How many sticks would you like to pick? ");
                Integer input = scanna.nextInt();
                if (input > 0 && input < 4) {
                    won = state.pickUpSticks(input);

                    if (!won) {
                        state.switchToNextPlayer();
                    }
                    else {
                        System.out.println("Player " + player + " Won!");
                        System.out.print("Do you want to play again? (y/n) ");
                        Character pInput = scanna.next().charAt(0);
                        if (pInput == 'n' || pInput == 'N') {
                            playing = false;
                        }
                    }
                }
                else {
                    System.out.println("Please pick a value between 1 and 3!");
                }
            } while (playing && !won);
        } while (playing);
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
import java.util.Scanner;

public class NimGame {
	public static void printSticks(Integer input) {
        for (int i=0; i < input; i++) {
            System.out.print("|");
        }
        System.out.println("");
    }

	public static void main(String[] args) {
		System.out.println("JAVA NIM v1");
		boolean playing = true;
		do {
			Scanner scanna = new Scanner(System.in);
			boolean won = false;
			boolean player1Playing = true;
			int turnCount = 1;
			int stickCount = 26;
			do {
				System.out.print("Turn " + turnCount + "; Player ");
				turnCount++;
				if (player1Playing) {
					System.out.print("1\n");
				}
				else {
					System.out.print("2\n");
				}
		        printSticks(stickCount);
		        System.out.print("How many sticks would you like to pick? ");
		        Integer input = scanna.nextInt();
		        if (input > 0 && input < 4) {
		            stickCount -= input;
		            if (stickCount == 0) {
		                won = true;
		            }
		            if (!won) {
                        player1Playing = !player1Playing;
		            }
		            else {
		            	if (player1Playing) {
		                    System.out.println("Player 1 Won!");
		                }
		                else {
		                    System.out.println("Player 2 Won!");
		                }
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
			} while(playing && !won);
		} while(playing);
	}
}
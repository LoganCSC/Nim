/**
 * Encapsulate the Nim game state so that it is easier to understand and test.
 */
class NimGameState {

    private boolean player1Playing = true;
    private int turnCount = 1;
    private int stickCount = 26;

    boolean isPlayer1() {
        return player1Playing;
    }

    void switchToNextPlayer()  {
        player1Playing = !player1Playing;
    }

    int getTurnCount() {
        return turnCount;
    }

    int getNumSticksRemaining() {
        return stickCount;
    }

    /**
     * @param numSticksToPickUp  the number of stick to pick up
     * @return true if the player whose current turn it is just won.
     */
    boolean pickUpSticks(int numSticksToPickUp) {
        stickCount -= numSticksToPickUp;
        stickCount = Math.max(0, stickCount);
        turnCount++;
        return (stickCount == 0);
    }

    boolean isGameOver() {
        return stickCount == 0;
    }

    String getCurrentPlayer() {
        return isPlayer1() ?  "1" : "2";
    }

}
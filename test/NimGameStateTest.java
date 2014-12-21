
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Barry Becker
 */
public class NimGameStateTest {

    /** this is the thing begin tested */
    private NimGameState state = new NimGameState();


    @Test
    public void checkInitialNumberOfSticks() {
        assertEquals("Unexpected number of sticks", 26, state.getNumSticksRemaining());
    }

    @Test
    public void checkNumberOfSticksAfterPickup() {
        state.pickUpSticks(2);
        assertEquals("Unexpected number of sticks after picking up 2",
                24, state.getNumSticksRemaining());
        state.pickUpSticks(4);
        assertEquals("Unexpected number of sticks after picking up 4",
                20, state.getNumSticksRemaining());
        boolean won = state.pickUpSticks(7);
        assertEquals("Unexpected number of sticks after picking up 7",
                13, state.getNumSticksRemaining());
        assertFalse(won);
    }

    /** verify that we cannot ever have less than 0 sticks even if we pick up more than there are */
    @Test
    public void checkNumberOfSticksAfterPickupMoreThanExist() {
        boolean won = state.pickUpSticks(30);
        assertEquals("Unexpected number of sticks",
                0, state.getNumSticksRemaining());
        assertTrue(won);
        assertTrue(state.isGameOver());
    }

    @Test
    public void testSwitchToNextPlayer()  {
        assertTrue(state.isPlayer1());
        state.advanceToNextPlayer();
        assertFalse(state.isPlayer1());
    }

    @Test
    public void testTurnCount()  {
        assertEquals("Unexpected inital turnCount",
                1, state.getTurnCount());
        state.pickUpSticks(3);
        state.pickUpSticks(2);
        state.pickUpSticks(2);
        assertEquals("Unexpected turnCount after 3 more moves",
                4, state.getTurnCount());
    }

    @Test
    public void testIsGameOver()  {
        assertFalse(state.isGameOver());
        state.pickUpSticks(20);
        assertFalse(state.isGameOver());
        state.pickUpSticks(10);
        assertTrue(state.isGameOver());
    }

    @Test
    public void testGetCurrentPlayer()  {
        assertEquals("1", state.getCurrentPlayer());
        state.advanceToNextPlayer();
        assertEquals("2", state.getCurrentPlayer());
    }

}

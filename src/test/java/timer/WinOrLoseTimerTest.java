package timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WinOrLoseTimerTest {
    @Test
    public void testTimerStartStop() {
        WinOrLoseTimer timer = WinOrLoseTimer.getInstance();

        timer.stopTimer();
        assertFalse(timer.isRunning());

        timer.startTimer();
        assertTrue(timer.isRunning());

        timer.stopTimer();
        assertFalse(timer.isRunning());
    }
}
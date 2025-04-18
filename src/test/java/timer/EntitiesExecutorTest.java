package timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesExecutorTest {

    @BeforeEach
    public void setup() {
        EntitiesExecutor.Instance.reset();
    }

    @AfterEach
    public void tearDown() {
        EntitiesExecutor.Instance.stop();
    }

    @Test
    public void testExecutorsStartCorrectly() {
        assertTrue(EntitiesExecutor.Instance.isRunning());
        assertTrue(EntitiesExecutor.Instance.isRunning2());
    }

    @Test
    public void testExecutorsStopCorrectly() {
        EntitiesExecutor.Instance.stop();
        assertFalse(EntitiesExecutor.Instance.isRunning());
        assertFalse(EntitiesExecutor.Instance.isRunning2());
    }

    @Test
    public void testAddScheduleWhenRunning() throws Exception {
        Callable<String> task = () -> "Hello";
        ScheduledFuture<String> future = EntitiesExecutor.Instance.addSchedule(task);

        assertNotNull(future);
        assertEquals("Hello", future.get());
    }

    @Test
    public void testAddScheduleReturnsNullWhenNotRunning() {
        EntitiesExecutor.Instance.stop();
        ScheduledFuture<String> future = EntitiesExecutor.Instance.addSchedule(() -> "Test");
        assertNull(future);
    }

    @Test
    public void testAddScheduleAtFixedRateWhenRunning() {
        Runnable task = () -> System.out.print("");  // No-op
        ScheduledFuture<?> future = EntitiesExecutor.Instance.addScheduleAtFixedRate(task);
        assertNotNull(future);
    }

    @Test
    public void testAddScheduleAtFixedRateReturnsNullWhenNotRunning() {
        EntitiesExecutor.Instance.stop();
        ScheduledFuture<?> future = EntitiesExecutor.Instance.addScheduleAtFixedRate(() -> {});
        assertNull(future);
    }
}
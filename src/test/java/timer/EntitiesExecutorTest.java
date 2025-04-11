package timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesExecutorTest {

    private EntitiesExecutor executor;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        executor = EntitiesExecutor.Instance;
        executor.stop();
        Field executorField = EntitiesExecutor.class.getDeclaredField("executor");
        executorField.setAccessible(true);
        executorField.set(executor, new ScheduledThreadPoolExecutor(4));
        Field pathSearchExecutorField = EntitiesExecutor.class.getDeclaredField("pathSearchExecutor");
        pathSearchExecutorField.setAccessible(true);
        pathSearchExecutorField.set(executor, new ScheduledThreadPoolExecutor(2));
    }

    @AfterEach
    void tearDown() {
        executor.stop();
    }

    @Test
    void reset_initializesExecutors() throws NoSuchFieldException, IllegalAccessException {
        executor.stop();
        Field executorField = EntitiesExecutor.class.getDeclaredField("executor");
        executorField.setAccessible(true);
        executorField.set(executor, new ScheduledThreadPoolExecutor(4));
        Field pathSearchExecutorField = EntitiesExecutor.class.getDeclaredField("pathSearchExecutor");
        pathSearchExecutorField.setAccessible(true);
        pathSearchExecutorField.set(executor, new ScheduledThreadPoolExecutor(2));
        assertTrue(executor.isRunning(), "Executor should be running after reset");
    }

    @Test
    void isRunning_returnsTrueWhenRunning() {
        assertTrue(executor.isRunning(), "Executor should be running after setup");
    }

    @Test
    void isRunning_returnsFalseAfterStop() {
        executor.stop();
        assertFalse(executor.isRunning(), "Executor should not be running after stop");
    }

    @Test
    void addSchedule_executesRunnable() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Runnable task = latch::countDown;
        ScheduledFuture<?> future = executor.addSchedule(task);
        assertTrue(latch.await(100, TimeUnit.MILLISECONDS), "Task should be executed");
        assertNotNull(future, "Future should not be null");
    }

    @Test
    void addSchedule_throwsExceptionWhenNotRunning() {
        executor.stop();
        Runnable task = () -> {
        };

        assertThrows(IllegalStateException.class, () -> executor.addSchedule(task),
                "Should throw IllegalStateException when executor is not running");
    }

    @Test
    void testAddSchedule_executesCallable() throws InterruptedException, ExecutionException {
        // Arrange
        Callable<String> task = () -> "Result";
        CountDownLatch latch = new CountDownLatch(1);
        executor.addSchedule(() -> {
            latch.countDown();
            return null;
        });
        ScheduledFuture<String> future = executor.addSchedule(task);
        assertTrue(latch.await(100, TimeUnit.MILLISECONDS), "Task should be scheduled");
        assertEquals("Result", future.get(), "Callable should return expected result");
    }

    @Test
    void testAddSchedule_throwsExceptionWhenNotRunning() {
        executor.stop();
        Callable<String> task = () -> "Result";
        assertThrows(IllegalStateException.class, () -> executor.addSchedule(task),
                "Should throw IllegalStateException when executor is not running");
    }

    @Test
    void addScheduleAtFixedRate_executesRepeatedly() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Runnable task = latch::countDown;
        ScheduledFuture<?> future = executor.addScheduleAtFixedRate(task);
        assertTrue(latch.await(200, TimeUnit.MILLISECONDS), "Task should execute multiple times");
        assertNotNull(future, "Future should not be null");
        future.cancel(false); // Clean up to prevent infinite execution
    }

    @Test
    void addScheduleAtFixedRate_throwsExceptionWhenNotRunning() {
        executor.stop();
        Runnable task = () -> {
        };
        assertThrows(IllegalStateException.class, () -> executor.addScheduleAtFixedRate(task),
                "Should throw IllegalStateException when executor is not running");
    }

    @Test
    void stop_shutsDownExecutors() {
        executor.stop();
        assertFalse(executor.isRunning(), "Executor should be stopped");
        assertThrows(IllegalStateException.class, () -> executor.addSchedule(() -> {
                }),
                "Should throw IllegalStateException after stop");
    }
}
package timer;

import panels.CardPanel;

import java.util.concurrent.*;

/**
 * Manages the execution of scheduled tasks using multiple executors.
 */
public class EntitiesExecutor {

    public static final EntitiesExecutor Instance = new EntitiesExecutor();

    private ScheduledExecutorService executor;
    private ScheduledExecutorService pathSearchExecutor;



    /**
     * Stops both the main executor and the path search executor if they are running.
     */
    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
        if (pathSearchExecutor != null) {
            pathSearchExecutor.shutdownNow();
            pathSearchExecutor = null;
        }
    }

    /**
     * Resets the executors by initializing them with new thread pools and scheduling tasks.
     */
    public synchronized void reset() {
        stop();

        executor = new ScheduledThreadPoolExecutor(4);
        pathSearchExecutor = new ScheduledThreadPoolExecutor(2);
    }

    /**
     * Checks if the main executor is currently running and not shut down or terminated.
     *
     * @return true if the executor is running, false otherwise.
     */
    public boolean isRunning() {

        return executor != null && !executor.isShutdown() && !executor.isTerminated();
    }

    /**
     * Checks if the main executor is currently running and not shut down or terminated.
     *
     * @return true if the executor is running, false otherwise.
     */
    public boolean isRunning2() {
        return pathSearchExecutor != null && !pathSearchExecutor.isShutdown() && !pathSearchExecutor.isTerminated();
    }


    /**
     * Schedules a callable task for one-time execution with a delay of 1 millisecond.
     *
     * @param task the task to execute.
     * @param <T> the result type of the callable.
     * @return a ScheduledFuture representing the pending result of the task.
     * @throws IllegalStateException if the executor is not running.
     */
    public <T> ScheduledFuture<T> addSchedule(Callable<T> task) {
        if (isRunning2()) {
            return pathSearchExecutor.schedule(task, 1, TimeUnit.MILLISECONDS);
        }
        return null;
    }

    /**
     * Schedules a task to be executed at a fixed rate starting after 1 millisecond delay,
     * and repeating every 20 milliseconds.
     *
     * @param task the task to execute.
     * @return a ScheduledFuture representing the pending result of the task.
     * @throws IllegalStateException if the executor is not running.
     */
    public ScheduledFuture<?> addScheduleAtFixedRate(Runnable task) {
        if (isRunning()) {
            return executor.scheduleAtFixedRate(task, 1, 20, TimeUnit.MILLISECONDS);
        }
        return null;
    }
}

package timer;

import panels.CardPanel;

import java.util.concurrent.*;

public class EntitiesExecutor {

    public static EntitiesExecutor Instance = new EntitiesExecutor();

    private ScheduledExecutorService executor;
    private ScheduledExecutorService pathSearchExecutor;

    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
        }
        if (pathSearchExecutor != null) {
            pathSearchExecutor.shutdownNow();
        }
    }

    public void reset() {
        executor = new ScheduledThreadPoolExecutor(4);
        addScheduleAtFixedRate(CardPanel.Instance::repaint);
        pathSearchExecutor = new ScheduledThreadPoolExecutor(2);
    }

    public boolean isRunning() {
        return executor != null && !executor.isShutdown() && !executor.isTerminated();
    }

    public ScheduledFuture<?> addSchedule(Runnable task) {
        if (isRunning()) {
            return executor.schedule(task, 1, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

    public <T> ScheduledFuture<T> addSchedule(Callable<T> task) {
        if (isRunning()) {
            return pathSearchExecutor.schedule(task, 1, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

    public ScheduledFuture<?> addScheduleAtFixedRate(Runnable task) {
        if (isRunning()) {
            return executor.scheduleAtFixedRate(task, 1, 20, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

}

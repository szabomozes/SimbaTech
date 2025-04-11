package timer;

import panels.CardPanel;
import panels.game.EventPanel;

import javax.swing.*;
import java.util.concurrent.*;

public class EntitiesExecutor {

    public static EntitiesExecutor Instance = new EntitiesExecutor();

    private ScheduledExecutorService executor;
    private ScheduledExecutorService pathSearchExecutor;

    public void stop() {
        if (executor != null) {
            executor.shutdownNow(); // azonnal leállítja a futó és várakozó feladatokat is
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

    // Egyszeri futtatás (opcionálisan késleltetve)
    public ScheduledFuture<?> addSchedule(Runnable task) {
        if (isRunning()) {
            return executor.schedule(task, 1, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

    // Egyszeri futtatás (opcionálisan késleltetve) Callable-lel
    public <T> ScheduledFuture<T> addSchedule(Callable<T> task) {
        if (isRunning()) {
            return pathSearchExecutor.schedule(task, 1, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

    // Ismétlődő futtatás
    public ScheduledFuture<?> addScheduleAtFixedRate(Runnable task) {
        if (isRunning()) {
            return executor.scheduleAtFixedRate(task, 1, 20, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

    // Egyszerű azonnali végrehajtás
    public void execute(Runnable task) {
        if (isRunning()) {
            executor.execute(task);
        } else {
            throw new IllegalStateException("Executor is not running.");
        }
    }

}

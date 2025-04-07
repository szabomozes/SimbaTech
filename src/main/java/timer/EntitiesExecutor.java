package timer;

import panels.CardPanel;
import panels.game.EventPanel;

import javax.swing.*;
import java.util.concurrent.*;

public class EntitiesExecutor {

    public static EntitiesExecutor Instance = new EntitiesExecutor();

    private ScheduledExecutorService executor;

    public void stop() {
        if (executor != null) {
            executor.shutdownNow(); // azonnal leállítja a futó és várakozó feladatokat is
        }
    }

    public void reset() {
        if (executor == null || executor.isShutdown() || executor.isTerminated()) {
            executor = new ScheduledThreadPoolExecutor(4);
            addScheduleAtFixedRate(CardPanel.Instance::repaint);
        }
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

    // Ismétlődő futtatás
    public ScheduledFuture<?> addScheduleAtFixedRate(Runnable task) {
        if (isRunning()) {
            return executor.scheduleAtFixedRate(task, 1, 10, TimeUnit.MILLISECONDS);
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

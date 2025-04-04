package timer;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class BasicTimer {

    protected Instant lastUpdate;
    protected final ScheduledExecutorService executor;
    protected final Random rnd = new Random();

    public BasicTimer() {
        executor = Executors.newScheduledThreadPool(1);
        lastUpdate = Instant.now();
        executor.scheduleAtFixedRate(this::update, 1_000_000_000, 5_000_000, TimeUnit.NANOSECONDS);
    }

    protected void update() {

    }

    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public boolean isRunning() {
        return executor != null && !executor.isShutdown() && !executor.isTerminated();
    }

}

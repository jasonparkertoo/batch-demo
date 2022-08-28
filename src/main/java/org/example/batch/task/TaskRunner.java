package org.example.batch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskRunner {
    private static final int NUM_COMPUTE_THREADS = 16;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUM_COMPUTE_THREADS);

    public void run(Task task) {
        this.executor.submit(task);
    }

    public ExecutorService getExecutorService() {
        return this.executor;
    }

    public boolean isProcessingThreads() {
        return ((ThreadPoolExecutor) this.executor).getActiveCount() > 0;
    }
}

package com.timbuchalka;

import java.util.concurrent.*;

public class TaskMonitor {
    private final ExecutorService executor;
    private final ProgressBar progressBar = new ProgressBar();

    public TaskMonitor(ExecutorService executor) {
        this.executor = executor;

        ScheduledExecutorService progressBarScheduler = Executors.newSingleThreadScheduledExecutor();
        progressBarScheduler.scheduleAtFixedRate(progressBar, 1000, 500, TimeUnit.MILLISECONDS);
    }

    public void addTask() {
        Task task = new Task();
        executor.submit(task);

        progressBar.addTask(task);
    }
}

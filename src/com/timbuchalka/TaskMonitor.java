package com.timbuchalka;

import java.util.Queue;

class TaskMonitor {
    private final Queue<Task> tasksQueue;
    private final ProgressBar progressBar;

    public TaskMonitor(Queue<Task> tasksQueue, ProgressBar progressBar) {
        this.tasksQueue = tasksQueue;
        this.progressBar = progressBar;
    }

    public void addTask() {
//        long startTime = System.currentTimeMillis();
        synchronized (tasksQueue) {
            try {
                Task task = new Task();
                tasksQueue.add(task);

                synchronized (ProgressBar.class) {
                    ProgressBar.addTask();
                    ProgressBar.addTime(task.getTimeToRun());
                }

                notify();
            } catch (IllegalStateException ignored) {}
        }

//        long endTime = System.currentTimeMillis();
//
//        long duration = (endTime - startTime);
//        System.out.println("Duration (ms): " + duration);
    }
}

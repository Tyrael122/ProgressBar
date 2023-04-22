package com.timbuchalka;

import java.util.Queue;

class TaskMonitor {
    private final Queue<Task> tasksQueue;

    public TaskMonitor(Queue<Task> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }

    public void addTask() {
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
    }
}

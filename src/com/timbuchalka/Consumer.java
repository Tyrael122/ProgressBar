package com.timbuchalka;

import java.util.Queue;

class Consumer extends Thread {
    private final Queue<Task> tasksQueue;
    private final int efficiency;
    private final TaskMonitor taskMonitor;

    public Consumer(Queue<Task> tasksQueue, TaskMonitor taskMonitor) {
        this.tasksQueue = tasksQueue;
        this.taskMonitor = taskMonitor;
        this.efficiency = 0;
    }

    public Consumer(Queue<Task> tasksQueue, TaskMonitor taskMonitor, int efficiency) {
        this.tasksQueue = tasksQueue;
        this.taskMonitor = taskMonitor;
        this.efficiency = efficiency;
    }

    @Override
    public void run() {
        while (true) {
            Task task;
            task = pollTask();
            try {
                if (task == null) {
                    waitForTaskToBeAdded();
                    continue;
                }

                calculateTimeToRunTask(task);
                task.run();

                removeTaskFromProgressBar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitForTaskToBeAdded() throws InterruptedException {
        synchronized (taskMonitor) {
            taskMonitor.wait();
        }
    }

    private Task pollTask() {
        Task task;
        synchronized (tasksQueue) {
            task = tasksQueue.poll();
        }
        return task;
    }

    private void calculateTimeToRunTask(Task task) {
        int defaultTimeToRun = task.getTimeToRun();
        double newTimeToRun = defaultTimeToRun - (defaultTimeToRun * (efficiency / 100.0));
        task.setTimeToRun((int) newTimeToRun);
    }

    private void removeTaskFromProgressBar() {
        ProgressBar.removeTask();
    }

    public int getEfficiency() {
        return efficiency;
    }
}

package com.timbuchalka;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProgressBar implements Runnable {
    private final Queue<Task> tasksQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        int totalTaskTime = getTotalTaskTime();
        int tasksAmount = tasksQueue.size();

        deleteProgressBar();
        printProgressBar(tasksAmount, totalTaskTime);
    }

    private int getTotalTaskTime() {
        int totalTaskTime = 0;
        for (Task task : tasksQueue) {
            synchronized (task) {
                int timeToRunTask = task.getTimeToRun();
                totalTaskTime += timeToRunTask / 1000;

                removeTaskIfRuntimeIsEmpty(task, timeToRunTask);
            }
        }
        return totalTaskTime;
    }

    private void removeTaskIfRuntimeIsEmpty(Task task, int timeToRunTask) {
        if (timeToRunTask < 1000) {
            tasksQueue.remove(task);
        }
    }

    public void addTask(Task task) {
        tasksQueue.add(task);
    }

    public void printProgressBar(int tasksAmount, int totalTaskTime) {
        System.out.print("O ".repeat(tasksAmount));
        System.out.print("// Total time: " + totalTaskTime + "s");
    }

    private void deleteProgressBar() {
        System.out.print("\b\b".repeat(100));
    }
}

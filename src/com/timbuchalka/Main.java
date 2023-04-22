package com.timbuchalka;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static int size = 0;
    public static void main(String[] args) {
        // TODO: Change to use a thread pool for practice.

        int capacity = 20;

        Queue<Task> taskQueue = new ArrayBlockingQueue<>(capacity);

        // TODO: Make the consumer not run and wait for the creation of a new task.
        ProgressBar progressBar = new ProgressBar();
        TaskMonitor taskMonitor = new TaskMonitor(taskQueue, progressBar);

        List<Consumer> consumers = new ArrayList<>(
                Arrays.asList(
                    new Consumer(taskQueue, taskMonitor, progressBar, 0),
                    new Consumer(taskQueue, taskMonitor, progressBar, 0)
                )
        );

        startConsumers(consumers);
        displayKeyListenerWindow(taskMonitor);

//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.schedule(new Producer(taskQueue), 1000, TimeUnit.SECONDS);
//
//        executor.schedule(new Consumer(taskQueue), 1000, TimeUnit.SECONDS);
//        executor.schedule(new Consumer(taskQueue), 1000, TimeUnit.SECONDS);

        printConsumersInfo(consumers);
        printCapacity(capacity);

//        startProgressBarThread(taskQueue, taskMonitor);
    }

//    private static void startProgressBarThread(Queue<Task> taskQueue) {
//        ProgressBar progressBar = new ProgressBar(taskQueue);
//        progressBar.start();
//    }


    private static void printConsumersInfo(List<Consumer> consumers) {
        System.out.println("Number of:");
        System.out.println("    Consumers: " + consumers.size());
        for (int i = 0; i < consumers.size(); i++) {
            int efficiency = consumers.get(i).getEfficiency();
            System.out.println("        " + (i + 1) + " - Efficiency: " + efficiency + "%");
        }
        System.out.println("----------------------------------------------");
    }

    private static void displayKeyListenerWindow(TaskMonitor taskMonitor) {
        KeyListenerFrame frame = new KeyListenerFrame("My Frame");
        frame.setSize(400, 400);
        frame.addKeyListener(new TaskKeyListener(taskMonitor)); // Add key listener to frame
        frame.setVisible(true);
    }

    private static void startConsumers(List<? extends Thread> consumers) {
        for (Thread consumer : consumers) {
            consumer.start();
        }
    }

    private static void printCapacity(int capacity) {
        System.out.println("Progress bar: ");
        for (int i = 0; i < capacity; i++) {
            System.out.print("  ");
        }
        System.out.print("// Capacity");
        System.out.println();
    }
}
















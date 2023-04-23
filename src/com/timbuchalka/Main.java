package com.timbuchalka;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        TaskMonitor taskMonitor = new TaskMonitor(executor);

        displayKeyListenerWindow(taskMonitor);
    }


    private static void displayKeyListenerWindow(TaskMonitor taskMonitor) {
        KeyListenerFrame frame = new KeyListenerFrame("My Frame");
        frame.setSize(400, 400);
        frame.addKeyListener(new TaskKeyListener(taskMonitor)); // Add key listener to frame
        frame.setVisible(true);
    }
}
















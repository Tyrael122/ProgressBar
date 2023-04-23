package com.timbuchalka;

import java.util.Random;

public class Task implements Runnable {
    private int timeToRun =  new Random().nextInt(10000, 20000);
    public void run() {
        while (timeToRun - 1000 > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            timeToRun -= 1000;
        }
    }

    public int getTimeToRun() {
        return timeToRun;
    }
}

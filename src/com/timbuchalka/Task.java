package com.timbuchalka;

import java.util.Random;

public class Task {
    private int timeToRun =  new Random().nextInt(10000, 20000);
    public void run() throws InterruptedException {
        while (timeToRun - 1000 > 0) {
            Thread.sleep(1000);
            timeToRun -= 1000;
            synchronized (ProgressBar.class) {
                ProgressBar.removeTime(1000);
                ProgressBar.updateTime();
            }
        }
    }

    public int getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(int timeToRun) {
        this.timeToRun = timeToRun;
    }
}

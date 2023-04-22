package com.timbuchalka;

import java.util.concurrent.ArrayBlockingQueue;

public class TaskQueue<Task> extends ArrayBlockingQueue<Task> implements Runnable {
    public TaskQueue(int capacity) {
        super(capacity);
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

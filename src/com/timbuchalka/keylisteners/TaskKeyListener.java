package com.timbuchalka.keylisteners;

import com.timbuchalka.TaskMonitor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TaskKeyListener implements KeyListener {
    private final TaskMonitor taskMonitor;

    public TaskKeyListener(TaskMonitor taskMonitor) {
        this.taskMonitor = taskMonitor;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                synchronized (taskMonitor) {
                    taskMonitor.addTask();
                }
            } catch (IllegalStateException ignored) {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }
}

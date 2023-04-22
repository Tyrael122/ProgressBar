package com.timbuchalka;

public class ProgressBar {
    private static int tasksAmount = 0;
    private static int totalTaskTime = 0;

    public static void addTask() {
        tasksAmount++;
        update();
    }

    public static void removeTask() {
        if (tasksAmount == 0) {
            return;
        }
        tasksAmount--;
        update();
    }

    public static void updateTime() {
        deleteProgressBar();
        printProgressBar();
    }

    private static void update() {
        if (tasksAmount == 0) {
            deleteProgressBar();
            return;
        }

        deleteProgressBar();
        printProgressBar();
    }

    private static void printProgressBar() {
        System.out.print("O ".repeat(tasksAmount));
        System.out.print("// Total time: " + totalTaskTime() + "s");
    }

    private static String totalTaskTime() {
        return String.valueOf(totalTaskTime);
    }

    private static void deleteProgressBar() {
        System.out.print("\b\b".repeat(100));
    }

    public static void addTime(long timeToAdd) {
        totalTaskTime += timeToAdd / 1000;
    }

    public static void removeTime(int timeToRemove) {
        totalTaskTime -= timeToRemove / 1000;
    }
}

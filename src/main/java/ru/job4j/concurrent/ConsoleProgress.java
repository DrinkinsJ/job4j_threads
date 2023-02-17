package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        int count = 0;
        var process = new char[]{'-', '\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[count % process.length]);
            count++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


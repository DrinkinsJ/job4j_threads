package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getState())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getState())
        );
        System.out.println(first.getName() + "\t" + first.getState());
        System.out.println(second.getName() + "\t" + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.print("");
        }
        System.out.println(Thread.currentThread().getName() + "\t\t" + "TERMINATED");
    }
}
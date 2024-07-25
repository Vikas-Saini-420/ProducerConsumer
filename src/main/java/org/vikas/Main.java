package org.vikas;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SharedBufferInterface sharedBuffer = new SharedBufferWIthLocks(10);
        Thread producer = new Thread(new Producer(sharedBuffer));
        Thread consumer = new Thread(new Consumer(sharedBuffer));
        producer.start();
        consumer.start();
    }
}
package org.vikas;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class SharedBufferWithSemaphore implements SharedBufferInterface {
    //implementation of Producer Consumer Problem using Semaphore
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final Semaphore full = new Semaphore(0);
    private final Semaphore empty = new Semaphore(1);
    private final Semaphore mutex = new Semaphore(1);
    private final int capacity;

    public SharedBufferWithSemaphore(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int data) {
        try {
            empty.acquire();
            mutex.acquire();
            buffer.add(data);
            System.out.println("Producer produces " + data);
            full.release();
            mutex.release();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public int consume() {
        try {
            full.acquire();
            mutex.acquire();
            int data = buffer.remove();
            System.out.println("Consumer consumes " + data);
            empty.release();
            mutex.release();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

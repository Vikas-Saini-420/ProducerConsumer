package org.vikas;

import java.util.LinkedList;

public class SharedBufferSynchronised implements SharedBufferInterface{
    private LinkedList<Integer> buffer = new LinkedList<>();
    int capacity;
    public SharedBufferSynchronised(int capacity) {
        this.capacity = capacity;
    }
    public boolean isFull() {
        return buffer.size() == capacity;
    }
    public boolean isEmpty() {
        return buffer.isEmpty();
    }
    public synchronized void produce(int item) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.add(item);
        System.out.println("Item added to buffer: " + item);
        notifyAll();
    }
    public synchronized int consume() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int value = buffer.poll();
        System.out.println("Item removed from buffer: " + value);
        notifyAll();
        return value;
    }
}

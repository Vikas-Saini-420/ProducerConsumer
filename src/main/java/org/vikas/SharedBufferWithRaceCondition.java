package org.vikas;

import java.util.LinkedList;

public class SharedBufferWithRaceCondition implements SharedBufferInterface{
    private LinkedList<Integer> buffer = new LinkedList<>();
    int capacity;
    public SharedBufferWithRaceCondition(int capacity) {
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
        }
        buffer.add(item);
        System.out.println("Item added to buffer: " + item);
    }
    public int consume() {
        while (isEmpty()) {
        }
        int value = buffer.poll();
        System.out.println("Item removed from buffer: " + value);
        return value;
    }
}

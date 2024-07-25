package org.vikas;

import java.util.concurrent.BlockingQueue;

public class SharedBufferWithBlockingQueue implements SharedBufferInterface {

    private BlockingQueue<Integer> blockingQueue;

    public SharedBufferWithBlockingQueue(int capacity) {
        blockingQueue = new java.util.concurrent.ArrayBlockingQueue<>(capacity);
    }

    @Override
    public boolean isEmpty() {
        return blockingQueue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return blockingQueue.size() == blockingQueue.remainingCapacity();
    }

    @Override
    public void produce(int item) {
        try {
            blockingQueue.put(item);
            System.out.println("Item added to buffer: " + item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int consume() {
        try {
            int item = blockingQueue.take();
            System.out.println("Item removed from buffer: " + item);
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

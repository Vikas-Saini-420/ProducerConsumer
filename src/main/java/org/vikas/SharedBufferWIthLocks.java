package org.vikas;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBufferWIthLocks implements SharedBufferInterface {
    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private static final LinkedList<Integer> buffer = new LinkedList<>();
    private int capacity;
    public SharedBufferWIthLocks(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return buffer.size() == 0;
    }

    @Override
    public boolean isFull() {
        return buffer.size() == capacity;
    }

    @Override
    public void produce(int item) {
        try {
            lock.lock();
            while (isFull()) {
                notFull.await();
            }
            buffer.add(item);
            notEmpty.signalAll();
            System.out.println("Item added to buffer: " + item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int consume() {
        try {
            lock.lock();
            while(isEmpty()) {
                notEmpty.await();
            }
            int item = buffer.poll();
            System.out.println("Item removed from buffer: " + item);
            notFull.signalAll();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            lock.unlock();
        }
    }
}

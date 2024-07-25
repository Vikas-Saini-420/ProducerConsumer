package org.vikas;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private final SharedBufferInterface sharedBuffer;

    AtomicInteger count = new AtomicInteger(0);

    public Producer(SharedBufferInterface sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }
    @Override
    public void run() {
        try {
            while (true) {
            int item = count.getAndIncrement();
            sharedBuffer.produce(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

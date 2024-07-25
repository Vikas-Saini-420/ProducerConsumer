package org.vikas;

public class Consumer implements Runnable {

    private final SharedBufferInterface sharedBuffer;

    public Consumer(SharedBufferInterface sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }
    @Override
    public void run() {
        try{
            while (true) {
            sharedBuffer.consume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

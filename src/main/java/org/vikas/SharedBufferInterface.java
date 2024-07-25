package org.vikas;

public interface SharedBufferInterface {

    default public boolean isFull() {
        return false;
    }

    default public boolean isEmpty() {
        return false;
    }

    public void produce(int item) ;

    public int consume();
}

package hello.no4;

import hello.no3.MyLinkedList;

public class MyQueue<T> implements QueueInterface<T>{
    private final MyLinkedList<T> linkedList = new MyLinkedList<>();

    @Override
    public void push(T item) {
        linkedList.add(item);
    }

    @Override
    public T pop() {
        if (linkedList.getSize() == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        T ret = peek();
        linkedList.delete(0);
        return ret;
    }

    @Override
    public T peek() {
        if (linkedList.getSize() == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return linkedList.get(0);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}

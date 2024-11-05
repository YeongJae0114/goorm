package hello.no5;

import hello.no3.MyLinkedList;

public class MyStack<T> implements StackInterface<T>{
    private final MyLinkedList<T> linkedList = new MyLinkedList<>();

    @Override
    public void push(T item) {
        linkedList.add(item);
    }

    @Override
    public T pop() {
        if (linkedList.getSize() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        T ret = top();
        linkedList.delete(linkedList.getSize()-1);
        return ret;
    }

    @Override
    public T top() {
        if (linkedList.getSize() == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return linkedList.get(linkedList.getSize()-1);
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

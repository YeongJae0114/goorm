package hello.no2;

import hello.common.LinkedListInterface;
import hello.common.Node;
import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> implements LinkedListInterface<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void add(T data) {
        if (head==null){
            addIfEmpty(data);
            return;
        }
        addIfNotEmpty(data);
    }

    @Override
    public T get(int targetIndex) {
        if (checkValidBound(targetIndex)){
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = this.head;
        int currentIndex = 0;
        while (currentIndex < targetIndex){
            currentNode = currentNode.getNext();
            currentIndex++;
        }
        return currentNode.getData();
    }

    @Override
    public void delete(int targetIndex) {
        if (checkValidBound(targetIndex)){
            throw new IndexOutOfBoundsException();
        }
        if (targetIndex==0){
            deleteTargetIndexIsZero();
            return;
        }
        deleteTargetIndexIsNotZero(targetIndex);

    }

    @Override
    public String toString(){
        List<String>result = new ArrayList<>();
        Node<T>current = head;
        while(current != null){
            result.add(current.getData().toString());
            current = current.getNext();
        }
        return String.join(" -> ", result);
    }

    @Override
    public int getSize() {
        return size;
    }

    private boolean checkValidBound(int targetIndex){
        return targetIndex < 0 || targetIndex >= size;
    }

    private void addIfEmpty(T data){
        head = new Node<>(data);
        tail = head;
    }

    private void addIfNotEmpty(T data){
        tail.setNext(new Node<>(data));
        tail = tail.getNext();
    }

    private void deleteTargetIndexIsZero(){
        head = head.getNext();
        size--;
    }

    private void deleteTargetIndexIsNotZero(int targetIndex){
        Node<T> current = head;
        int currentIndex = 0;
        while(currentIndex < targetIndex - 1){
            current = current.getNext();
            currentIndex++;
        }
        if (current.getNext().getNext()==null){
            tail = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
    }

}

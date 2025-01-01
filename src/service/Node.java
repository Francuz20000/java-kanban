package service;

public class Node <T> {
    public T data;
    public service.Node<T> next;
    public service.Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

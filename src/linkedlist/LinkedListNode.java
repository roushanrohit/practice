package linkedlist;

public class LinkedListNode<T> {

    T data;
    LinkedListNode<T> nextNode;

    LinkedListNode(T data){
        this.data = data;
        this.nextNode = null;
    }
}

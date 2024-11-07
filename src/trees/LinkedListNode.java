package trees;

public class LinkedListNode<T> {

    T data;
    LinkedListNode<T> nextNode;

    LinkedListNode(T data){
        this.data = data;
        nextNode = null;
    }

    LinkedListNode(T data, LinkedListNode<T> nextNode){
        this.data = data;
        this.nextNode = nextNode;
    }
}
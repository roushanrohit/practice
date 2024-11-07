package stacks_and_queues;

public class LinkedListNode <T> {

    T data;
    LinkedListNode<T> nextNode;

    LinkedListNode(T data){
        this.data = data;
        nextNode = null;
    }
}

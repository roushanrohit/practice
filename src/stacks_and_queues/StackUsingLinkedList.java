package stacks_and_queues;

public class StackUsingLinkedList <T> {

    private LinkedListNode<T> head;
    private int size;

    StackUsingLinkedList(){
        head = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    T top() throws StackEmptyException {
        if(size == 0){
            throw new StackEmptyException();
        }
        return head.data;
    }

    T pop() throws StackEmptyException {
        if(size == 0){
            throw new StackEmptyException();
        }
        T data = head.data;
        head = head.nextNode;
        size--;
        return data;
    }

    void push(T data){
        LinkedListNode<T> newElement = new LinkedListNode<>(data);
        newElement.nextNode = head;
        head = newElement;
        size++;
    }
}

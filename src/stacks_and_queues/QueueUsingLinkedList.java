package stacks_and_queues;

public class QueueUsingLinkedList <T> {

    LinkedListNode<T> front = null;
    LinkedListNode<T> rear = null;
    int size = 0;

    int size() {
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    T front() throws QueueEmptyException {
        if(size == 0) throw new QueueEmptyException();
        return front.data;
    }

    void enqueue(T data){
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if(size == 0){
            front = newNode;
        } else {
            rear.nextNode = newNode;
        }
        rear = newNode;
        size++;
    }

    T dequeue() throws QueueEmptyException {
        if(size == 0) throw new QueueEmptyException();
        T temp = front.data;
        front = front.nextNode;
        size--;

        // if the queue has become empty
        if(size == 0){
            rear = null;
        }
        return temp;
    }
}

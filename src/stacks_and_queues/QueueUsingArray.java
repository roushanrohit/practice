package stacks_and_queues;

public class QueueUsingArray {

    private int[] data;
    private int front;
    private int rear;
    private int size;

    QueueUsingArray(){
        data = new int[10];
        front = -1;
        rear = -1;
        size = 0;
    }

    QueueUsingArray(int capacity){
        data = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    int front() throws QueueEmptyException {
        if(size == 0) throw new QueueEmptyException();
        return data[front];
    }

    void enqueue(int element){
        if(size == 0) front = 0;
        if(size == data.length){
            doubleCapacity();
        } else {
            rear = (rear + 1) % data.length;
        }
        data[rear] = element;
        size++;
    }

    int dequeue() throws QueueEmptyException {
        if(size == 0) throw new QueueEmptyException();
        int temp = data[front];
        size--;
        if(size == 0){
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % data.length;
        }
        return temp;
    }

    private void doubleCapacity() {

        int[] temp = data;
        data = new int[data.length * 2];
        int index = 0;
        for(int i = front; i < temp.length; i++){
            data[index++] = temp[i];
        }
        for(int i = 0; i < front; i++){
            data[index++] = temp[i];
        }
        front = 0;
        rear = temp.length;
    }
}

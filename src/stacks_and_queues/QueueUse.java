package stacks_and_queues;

public class QueueUse {

    public static void main(String[] args) throws QueueEmptyException {

        QueueUsingLinkedList<Integer> queue = new QueueUsingLinkedList<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        System.out.println("Size of the queue: " + queue.size());
//        while (!queue.isEmpty()){
//            System.out.println(queue.dequeue());
//        }
        reverseQueue(queue);
        while (!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }
    }

    private static void reverseQueue(QueueUsingLinkedList<Integer> queue) throws QueueEmptyException {

        // base case
        if(queue.size() <= 1) return;

        int front = queue.dequeue();
        reverseQueue(queue);
        queue.enqueue(front);
    }
}

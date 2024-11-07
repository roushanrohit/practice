package hashmaps;

public class LinkedListNode<K, V> {

    K key;
    V value;
    LinkedListNode<K, V> nextNode;

    LinkedListNode(K key, V value){
        this.key = key;
        this.value = value;
        this.nextNode = null;
    }
}

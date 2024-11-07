package hashmaps;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap<K,V> {
    private static final double STD_LOAD_FACTOR = 0.7;
    private int size = 0;
    private int numBuckets = 5;
    private List<LinkedListNode<K, V>> buckets;
    private double loadFactor = 0;

    public CustomHashMap() {
        buckets = new ArrayList<>(numBuckets);
        for(int i = 0; i < numBuckets; i++){

            /*
                adding null so that we can set a value at a
                particular bucket index
             */
            buckets.add(null);
        }
    }

    private int getBucketIndex(K key) {

        /*
            using hashcode of object class if the key has not
            overridden the hashcode method
         */
        int hashCode = key.hashCode();
        return hashCode % numBuckets; // compression function
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return loadFactor;
    }

    public void insert(K key, V value) {

        if(loadFactor > STD_LOAD_FACTOR) rehash();
        int bucketIndex = getBucketIndex(key);
        LinkedListNode<K, V> head = buckets.get(bucketIndex);

        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                break;
            }
            head = head.nextNode;
        }

        if(head == null){
            // key is not already present in the hashmap
            LinkedListNode<K, V> newNode = new LinkedListNode<>(key, value);
            head = buckets.get(bucketIndex);
            newNode.nextNode = head;
            buckets.set(bucketIndex, newNode);
            size++;
            loadFactor = (double) size/numBuckets;
        }
    }

    public V getValue(K key) {

        int bucketIndex = getBucketIndex(key);
        LinkedListNode<K, V> head = buckets.get(bucketIndex);

        while (head != null){
            if(head.key.equals(key)) {
                return head.value;
            }
            head = head.nextNode;
        }

        // key is not present in the hashmap
        return null;
    }

    public V removeKey(K key) {

        int bucketIndex = getBucketIndex(key);
        LinkedListNode<K, V> head = buckets.get(bucketIndex);
        LinkedListNode<K, V> prev = null;

        while (head != null){
            if(head.key.equals(key)){
                size--;
                loadFactor = (double) size/numBuckets;
                if(prev == null) buckets.set(bucketIndex, head.nextNode);
                else prev.nextNode = head.nextNode;
                return head.value;
            }
            prev = head;
            head = head.nextNode;
        }

        // key is not present in the hashmap
        return null;
    }

    private void rehash() {

        List<LinkedListNode<K, V>> temp = buckets;
        numBuckets *= 2;
        size = 0;
        buckets = new ArrayList<>(numBuckets);

        for(int i = 0; i < numBuckets; i++){
            buckets.add(null);
        }

        for(int i = 0; i < temp.size(); i++){
            LinkedListNode<K, V> head = temp.get(i);
            while (head != null){
                insert(head.key, head.value);
                head = head.nextNode;
            }
        }
    }
}

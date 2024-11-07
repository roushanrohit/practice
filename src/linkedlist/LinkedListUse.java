package linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinkedListUse {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // default size is 10, it is incremented by 50% everytime the list is full and we add another element
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        // adds 0 at index 1 so elements 2, 3 are shifted to the right
        // new arraylist is {1, 0, 2, 3}
        list1.add(1, 0);
        for(int ele: list1) System.out.print(ele + " ");
        System.out.println();

        LinkedListNode<Integer> head = createLinkedList(s);
        printLinkedList(head);

        System.out.println("Length of the Linked List: " + findLength(head));

        int k = 2;
        printKthNode(head, k);

        int m = 50;
        System.out.println("Is " + m + " present in the list: " + findNode(head, m));

        System.out.println("Linked List after removing the duplicates: ");
        removeDuplicates(head);
        printLinkedList(head);

        // without reversing the linked list
        System.out.println("Printing the linked list in reverse order: ");
        printReversedLinkedList(head);

        List<LinkedListNode<Integer>> middleElements = findMiddleElement(head);
        if(middleElements.size() > 1){
            System.out.println("Linked List has even number of nodes, middle elements: " + middleElements.get(0).data
                    + " " + middleElements.get(1).data);
        } else {
            System.out.println("Linked List has odd number of elements, middle element: " + middleElements.get(0).data);
        }

        System.out.println("First middle element of the linked list: " + findFirstMiddleElement(head).data);

        System.out.println("Is the linked list a palindrome: " + checkPalindromeLinkedList(head));

        // reverse the linked list
//        head = reverseLinkedList(head);
//        System.out.println("Printing the list after reversing it: ");
//        printLinkedList(head);

//        int n = 4;
//        head = appendLastNNodesToFrontImproved(head, n);
//        System.out.println("Printing the linked list after appending the last " + n +" nodes to the front: ");
//        printLinkedList(head);

//        int data = 100;
//        head = insertDataAtKthPosition(head, k, data);
//        printLinkedList(head);
//
//        int l = 0;
//        head = deleteNodeAtKthPosition(head, l);
//        printLinkedList(head);
    }

    private static LinkedListNode<Integer> findFirstMiddleElement(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return head;

        LinkedListNode<Integer> slow = head;
        LinkedListNode<Integer> fast = head;

        while(fast.nextNode != null && fast.nextNode.nextNode != null){
            fast = fast.nextNode.nextNode;
            slow = slow.nextNode;
        }

        return slow;
    }

    private static List<LinkedListNode<Integer>> findMiddleElement(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return List.of(head);

        LinkedListNode<Integer> slow = head;
        LinkedListNode<Integer> fast = head;
        LinkedListNode<Integer> prev = null;

        while(fast != null && fast.nextNode != null){
            fast = fast.nextNode.nextNode;
            prev = slow;
            slow = slow.nextNode;
        }

        List<LinkedListNode<Integer>> result = new ArrayList<>();
        if(fast == null){
            result.add(prev);
        }
        result.add(slow);

        return result;
    }

    private static boolean checkPalindromeLinkedList(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return true;

        LinkedListNode<Integer> firstMiddleElement = findFirstMiddleElement(head);
        LinkedListNode<Integer> revHead = reverseLinkedList(firstMiddleElement.nextNode);
        firstMiddleElement.nextNode = null;

        while (head != null && revHead != null){
            if(head.data != revHead.data) return false;
            else {
                head = head.nextNode;
                revHead = revHead.nextNode;
            }
        }

        return true;
    }

    // O(N)
    private static LinkedListNode<Integer> reverseLinkedList(LinkedListNode<Integer> head) {

        // edge case
        if(head == null) return null;

        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> curr = head;
        LinkedListNode<Integer> next = null;

        while (curr != null){
            next = curr.nextNode;
            curr.nextNode = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // O(N^2)
    private static void printReversedLinkedList(LinkedListNode<Integer> head) {

        // edge case
        if(head == null) return;
        LinkedListNode<Integer> first = head;

        int lastIndex = 0;
        while (head.nextNode != null){
            head = head.nextNode;
            lastIndex++;
        }

        for(int i = 0; i <= lastIndex; i++){

            // reset head
            head = first;
            for(int j = 0; j < lastIndex - i; j++){
                head = head.nextNode;
            }
            System.out.print(head.data + " ");
        }
        System.out.println();
    }

    private static void removeDuplicates(LinkedListNode<Integer> head) {

        if(head == null) return;

        LinkedListNode<Integer> curr = head;
        LinkedListNode<Integer> next = head.nextNode;

        while(next != null){
            if(curr.data == next.data){
                curr.nextNode = next.nextNode;
                LinkedListNode<Integer> temp = next;
                next = next.nextNode;
                temp.nextNode = null;
            } else {
                curr = next;
                next = next.nextNode;
            }
        }
    }

    private static LinkedListNode<Integer> appendLastNNodesToFrontImproved(LinkedListNode<Integer> head, int n) {

        // edge case 1
        if(head == null || n <= 0) return head;

        LinkedListNode<Integer> fast = head;
        LinkedListNode<Integer> slow = head;

        int pos = 0;
        while(pos < n && fast != null) {
            fast = fast.nextNode;
            pos++;
        }

        // edge case 2
        if(fast == null) return head;

        while(fast.nextNode != null){
            fast = fast.nextNode;
            slow = slow.nextNode;
        }

        LinkedListNode<Integer> newHead = slow.nextNode;
        slow.nextNode = null;
        fast.nextNode = head;

        return newHead;
    }

    private static LinkedListNode<Integer> appendLastNNodesToFront(LinkedListNode<Integer> head, int n) {

        // edge case 1
        if(head == null || n <= 0) return head;

        int lengthOfLL = 1;
        LinkedListNode<Integer> tail = head;
        while (tail.nextNode != null){
            tail = tail.nextNode;
            lengthOfLL++;
        }

        // edge case 2
        if(n >= lengthOfLL) return head;

        LinkedListNode<Integer> temp = head;
        int pos = 0;
        do {
            temp = temp.nextNode;
            pos++;
        } while (pos < lengthOfLL - n - 1);

        LinkedListNode<Integer> newHead = temp.nextNode;
        temp.nextNode = null;
        tail.nextNode = head;

        return newHead;
    }

    private static boolean findNode(LinkedListNode<Integer> head, int m) {

        while(head != null){
            if(head.data == m) return true;
            head = head.nextNode;
        }
        return false;
    }

    private static LinkedListNode<Integer> deleteNodeAtKthPosition(LinkedListNode<Integer> head, int k) {

        if(k == 0) {
            head = head.nextNode;
            return head;
        }

        LinkedListNode<Integer> temp = head;
        int pos = 0;
        // we have to delete temp.next so it should be not null
        while(temp.nextNode != null && pos < (k - 1)){
            temp = temp.nextNode;
            pos++;
        }

        if(temp.nextNode != null){
            temp.nextNode = temp.nextNode.nextNode;
        }

        return head;
    }

    private static LinkedListNode<Integer> insertDataAtKthPosition(LinkedListNode<Integer> head, int k, int data) {

        if(k == 0){
            LinkedListNode<Integer> nodeToBeInserted = new LinkedListNode<>(data);
            nodeToBeInserted.nextNode = head;
            return nodeToBeInserted;
        }

        int pos = 0;
        LinkedListNode<Integer> temp = head;
        while (temp != null && pos < (k - 1)){
            temp = temp.nextNode;
            pos++;
        }

        if(temp != null){
            LinkedListNode<Integer> nodeToBeInserted = new LinkedListNode<>(data);
            nodeToBeInserted.nextNode = temp.nextNode; // make new link first
            temp.nextNode = nodeToBeInserted; // break existing link later
        }

        return head;
    }

    private static void printKthNode(LinkedListNode<Integer> head, int k) {

        int pos = 0;
        while(head != null && pos < k) {
            head = head.nextNode;
            pos++;
        }

        if(head != null) System.out.println("kth Node of the Linked List: " + head.data);
    }

    private static int findLength(LinkedListNode<Integer> head) {

        int length = 0;
        while (head != null){
            length++;
            head = head.nextNode;
        }
        return length;
    }

    // Time Complexity = O(N)
    private static LinkedListNode<Integer> createLinkedList(Scanner s) {

        LinkedListNode<Integer> head = null;
        LinkedListNode<Integer> tail = null;

        System.out.println("Enter the data for the head node: ");
        int data = s.nextInt();
        while (data != -1){
            LinkedListNode<Integer> newNode = new LinkedListNode<>(data);
            if(head == null) {
                head = newNode;
            } else {
                tail.nextNode = newNode;
            }
            tail = newNode;
            System.out.println("Enter the data for the next node: ");
            data = s.nextInt();
        }

        return head;
    }

    private static void printLinkedList(LinkedListNode<Integer> head) {
        /*
            head is local to this method, so when head becomes null, node1 is still
            pointing to the first node of the Linked List
         */
        while (head != null){
            System.out.print(head.data + " ");
            head = head.nextNode;
        }
        System.out.println();
    }
}

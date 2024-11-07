package linkedlist;

import java.util.LinkedList;
import java.util.Scanner;

public class LinkedListUse2 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
//        System.out.println("Taking input for the first sorted linked list: ");
//        LinkedListNode<Integer> head1 = createLinkedList(s);
//        System.out.println("Printing the first sorted linked list: ");
//        printLinkedList(head1);
//
//        System.out.println("Taking input for the second sorted linked list: ");
//        LinkedListNode<Integer> head2 = createLinkedList(s);
//        System.out.println("Printing the second sorted linked list: ");
//        printLinkedList(head2);
//
//        LinkedListNode<Integer> mergedLinkedListHead = mergeSortedLinkedList(head1, head2);
//        System.out.println("Printing the merged sorted linked list: ");
//        printLinkedList(mergedLinkedListHead);

        System.out.println("Taking input for the unsorted linked list: ");
        LinkedListNode<Integer> head = createLinkedList(s);
        LinkedListNode<Integer> newHead = mergeSortLinkedList(head);
        System.out.println("Printing the sorted linked list: ");
        printLinkedList(newHead);

//        LinkedListNode<Integer> revHead = reverseLinkedListRecursivelyImproved(newHead);
//        System.out.println("Printing the linked list after reversing it: ");
//        printLinkedList(revHead);

        int data = 80;
        int k = 4;
        newHead = insertNodeAtKthPosition(newHead, k, data);
        System.out.println("Printing the list after inserting " + data + " at position " + k);
        printLinkedList(newHead);

        newHead = deleteNodeAtKthPosition(newHead, k);
        System.out.println("Printing the linked list after deleting node at position " + k);
        printLinkedList(newHead);

//        newHead = evenAfterOddLinkedList(newHead);
//        System.out.println("Printing the linked list after arranging the elements in even after odd fashion: ");
//        printLinkedList(newHead);

        System.out.println("Enter the index of first node to be swapped: ");
        int i = s.nextInt();
        System.out.println("Enter the index of second node to be swapped: ");
        int j = s.nextInt();
        newHead = swapNodesOfLinkedList(newHead, i, j);
        System.out.println("Printing the linked list after swapping nodes at indices " + i + " and " + j + " : ");
        printLinkedList(newHead);

//        int m = 2;
//        int n = 3;
//        newHead = deleteNNodesAfterEveryMNodes(head, m, n);
//        System.out.println("Printing the linked list after deleting " + n + " nodes after every " + m + " nodes");
//        printLinkedList(newHead);
    }

    private static LinkedListNode<Integer> swapNodesOfLinkedList(LinkedListNode<Integer> head, int i, int j) {

        // edge case 1
        if(head == null || i == j) return head;

        LinkedListNode<Integer> pi = null;
        LinkedListNode<Integer> ci = head;
        LinkedListNode<Integer> pj = null;
        LinkedListNode<Integer> cj = head;

        int temp = i;
        i = Math.min(i, j);
        j = Math.max(temp, j);

        int pos = 0;
        while(pos < i && ci != null){
            pos++;
            pi = ci;
            ci = ci.nextNode;
        }
        pos = 0;
        while(pos < j && cj != null){
            pos++;
            pj = cj;
            cj = cj.nextNode;
        }

        // edge case 2
        if(ci == null || cj == null) return head;

        LinkedListNode<Integer> tempNode = ci.nextNode;
        ci.nextNode = cj.nextNode;

        if(i == 0) head = cj;
        else {
            pi.nextNode = cj;
        }

        if(j - i == 1){
            cj.nextNode = ci;
        } else {
            pj.nextNode = ci;
            cj.nextNode = tempNode;
        }

        return head;
    }

    private static LinkedListNode<Integer> deleteNNodesAfterEveryMNodes(LinkedListNode<Integer> head, int m, int n) {

        // edge cases
        if(n == 0 || head == null) return head; // no deletion
        if(m == 0) return null; // all nodes are deleted

        LinkedListNode<Integer> temp = head;
        while (temp != null){

            int pos = 0;
            while(pos < m - 1 && temp != null){
                temp = temp.nextNode;
                pos++;
            }

            if(temp == null){
                // we have reached to the end of the linked list
                return head;
            }
            LinkedListNode<Integer> mthNode = temp;

            pos = 0;
            while (pos < n && temp != null){
                temp = temp.nextNode;
                pos++;
            }

            if(temp == null){
                // we have reached to the end of the linked list
                mthNode.nextNode = null;
                return head;
            }

            mthNode.nextNode = temp.nextNode;
            temp = temp.nextNode;
        }

        return head;
    }

    private static LinkedListNode<Integer> evenAfterOddLinkedList(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return head;

        LinkedListNode<Integer> oddHead = null;
        LinkedListNode<Integer> oddTail = null;
        LinkedListNode<Integer> evenHead = null;
        LinkedListNode<Integer> evenTail = null;

        while (head != null){

            if(head.data % 2 == 0){
                if(evenHead == null){
                    evenHead = head;
                } else {
                    evenTail.nextNode = head;
                }
                evenTail = head;
            } else {
                if(oddHead == null){
                    oddHead = head;
                } else {
                    oddTail.nextNode = head;
                }
                oddTail = head;
            }
            head = head.nextNode;
        }

        if(oddHead != null) {
            if(evenHead != null){
                oddTail.nextNode = evenHead;
                evenTail.nextNode = null;
            }
            return oddHead;
        } else {
            return evenHead;
        }
    }

    private static LinkedListNode<Integer> deleteNodeAtKthPosition(LinkedListNode<Integer> head, int pos) {

        // edge case
        if(head == null || pos < 0) return head;

        // base case
        if(pos == 0){
            LinkedListNode<Integer> newHead = head.nextNode;
            head.nextNode = null;
            return newHead;
        }

        head.nextNode = deleteNodeAtKthPosition(head.nextNode, pos - 1);
        return head;
    }

    private static LinkedListNode<Integer> insertNodeAtKthPosition(LinkedListNode<Integer> head, int pos, int data) {

        // edge case
        if(head == null || pos < 0) return head;

        // base case
        if(pos == 0){
            LinkedListNode<Integer> newNode = new LinkedListNode<>(data);
            newNode.nextNode = head;
            return newNode;
        }

        head.nextNode = insertNodeAtKthPosition(head.nextNode, pos - 1, data);
        return head;
    }

    private static LinkedListNode<Integer> reverseLinkedListRecursivelyImproved(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return head;

        LinkedListNode<Integer> revTail = head.nextNode;
        LinkedListNode<Integer> revHead = reverseLinkedListRecursivelyImproved(head.nextNode);
        revTail.nextNode = head;
        head.nextNode = null;

        return revHead;
    }

    private static LinkedListNodePair reverseLinkedListRecursively(LinkedListNode<Integer> head) {

        // edge case
        if(head == null) return null;

        // base case
        if(head.nextNode == null) return new LinkedListNodePair(head, head);

        LinkedListNodePair linkedListNodePair = reverseLinkedListRecursively(head.nextNode);
        linkedListNodePair.tail.nextNode = head;
        head.nextNode = null;
        linkedListNodePair.tail = head;

        return linkedListNodePair;
    }

    private static LinkedListNode<Integer> reverseLinkedListIteratively(LinkedListNode<Integer> head) {

        // edge case
        if(head == null || head.nextNode == null) return head;

        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> curr = head;
        LinkedListNode<Integer> next = null;

        while(curr != null){
            next = curr.nextNode;
            curr.nextNode = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static LinkedListNode<Integer> mergeSortLinkedList(LinkedListNode<Integer> head) {

        // base case
        if(head == null || head.nextNode == null) return head;

        LinkedListNode<Integer> midpoint = findFirstMiddleElement(head);
        LinkedListNode<Integer> rightLinkedListHead = midpoint.nextNode;
        midpoint.nextNode = null;
        LinkedListNode<Integer> leftSortedLinkedListHead = mergeSortLinkedList(head);
        LinkedListNode<Integer> rightSortedLinkedListHead = mergeSortLinkedList(rightLinkedListHead);

        return mergeSortedLinkedList(leftSortedLinkedListHead, rightSortedLinkedListHead);
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

    private static LinkedListNode<Integer> mergeSortedLinkedList(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2) {

        // edge cases
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        LinkedListNode<Integer> mergedHead = null;
        LinkedListNode<Integer> mergedTail = null;

        while (head1 != null && head2 != null){
            if(head1.data <= head2.data){
                if(mergedHead == null){
                    mergedHead = head1;
                } else {
                    mergedTail.nextNode = head1;
                }
                mergedTail = head1;
                head1 = head1.nextNode;
            } else {
                if(mergedHead == null){
                    mergedHead = head2;
                } else {
                    mergedTail.nextNode = head2;
                }
                mergedTail = head2;
                head2 = head2.nextNode;
            }
        }

        while (head1 != null){
            mergedTail.nextNode = head1;
            mergedTail = head1;
            head1 = head1.nextNode;
        }

        while (head2 != null){
            mergedTail.nextNode = head2;
            mergedTail = head2;
            head2 = head2.nextNode;
        }

        return mergedHead;
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
}

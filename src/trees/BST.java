package trees;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    BinaryTreeNode<Integer> root;

    public boolean hasData(int data){
        return hasData(root, data);
    }

    // O(height) -- For balanced BST O(logN)
    private boolean hasData(BinaryTreeNode<Integer> root, int m){

        // base case + edge case
        if(root == null) return false;

        if(root.data == m) return true;
        else if (root.data > m) {
            return hasData(root.leftNode, m);
        } else {
            return hasData(root.rightNode, m);
        }
    }

    public void insertData(int data){
        root = insertData(root, data);
    }

    // O(height) -- For balanced BST O(logN)
    private BinaryTreeNode<Integer> insertData(BinaryTreeNode<Integer> root, int m){

        // base case
        if(root == null){
            BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(m);
            return newNode;
        }

        if(root.data > m){
            root.leftNode = insertData(root.leftNode, m);
        } else {
            root.rightNode = insertData(root.rightNode, m);
        }
        return root;
    }

    public void deleteData(int data){
        root = deleteData(root, data);
    }

    // O(height) -- For balanced BST O(logN)
    private BinaryTreeNode<Integer> deleteData(BinaryTreeNode<Integer> root, int m){

        // base case
        if(root == null) return null;

        if(root.data > m){
            root.leftNode = deleteData(root.leftNode, m);
            return root;
        } else if (root.data < m) {
            root.rightNode = deleteData(root.rightNode, m);
            return root;
        } else {

            if(root.leftNode == null) return root.rightNode;
            if(root.rightNode == null) return root.leftNode;

            /*  We need to find a replacement for root/node to be deleted
                There are two options:
                1. Largest of the left subtree.
                2. Smallest of the right subtree.
             */

            // 2nd option -- smallest of right subtree
            BinaryTreeNode<Integer> smallestRightSubTree = root.rightNode;
            while(smallestRightSubTree.leftNode != null){
                smallestRightSubTree = smallestRightSubTree.leftNode;
            }
            root.data = smallestRightSubTree.data;
            // This time, the node to be deleted is a leaf node
            root.rightNode = deleteData(root.rightNode, smallestRightSubTree.data);
            return root;

            // 1st option -- largest of left subtree
//            BinaryTreeNode<Integer> largestLeftSubTree = root.leftNode;
//            while(largestLeftSubTree.rightNode != null){
//                largestLeftSubTree = largestLeftSubTree.leftNode;
//            }
//            root.data = largestLeftSubTree.data;
//            root.leftNode = deleteData(root.leftNode, largestLeftSubTree.data);
//            return root;
        }
    }

    public void printBST(){

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            StringBuilder sb = new StringBuilder();
            BinaryTreeNode<Integer> node = queue.poll();
            sb.append(node.data);

            if(node.leftNode != null){
                sb.append(" L: ").append(node.leftNode.data);
                queue.add(node.leftNode);
            }
            if(node.rightNode != null){
                sb.append(" R: ").append(node.rightNode.data);
                queue.add(node.rightNode);
            }
            System.out.println(sb);
        }
    }
}

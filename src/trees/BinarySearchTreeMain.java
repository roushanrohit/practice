package trees;

import java.util.*;

public class BinarySearchTreeMain {

    public static void main(String[] args) {

        // create a scanner
        Scanner s = new Scanner(System.in);

        BinaryTreeNode<Integer> binaryTreeRootLvlWise = createBinaryTreeLvlWise(s);
        printTreeLvlWise(binaryTreeRootLvlWise);

        int x = 5;
        System.out.println("Element " + x + " present in the BST: " + searchElement(binaryTreeRootLvlWise, x));

        int k1 = 5, k2 = 7;
        System.out.print("Nodes between " + k1 + " and " + k2 + " : ");
        printNodesBetweenK1K2(binaryTreeRootLvlWise, k1, k2);
        System.out.println();

        System.out.println("Is the binary tree a BST(bottom-up approach): " + checkBST(binaryTreeRootLvlWise).isBST);
        System.out.println("Is the binary tree a BST(top-down approach): " + checkBST2(binaryTreeRootLvlWise, Integer.MIN_VALUE, Integer.MAX_VALUE));

        List<Integer> sortedArrayList = List.of(1,2,3,4,5,6,7,8,9);
        BinaryTreeNode<Integer> bstFromSortedArray = createBSTFromSortedArray(sortedArrayList);
        System.out.println("Printing BST created from sorted array: ");
        printTreeLvlWise(bstFromSortedArray);

        LinkedListNode<Integer> headOfLinkedList = createLinkedListFromBST(bstFromSortedArray).head;
        System.out.println("Printing sorted linked list created from BST: ");
        while(headOfLinkedList != null){
            System.out.print(headOfLinkedList.data + " -> ");
            headOfLinkedList = headOfLinkedList.nextNode;
        }
        System.out.println();

        int m = 9;
        List<Integer> rootToNodePath = findRootToNodePathForBST(bstFromSortedArray, m);
        System.out.println("Printing root to node path for element: " + m);
        for(int i : rootToNodePath) System.out.print(i + " ");
        System.out.println();

        System.out.println("Printing path sum from root to element: " + m + " : " + rootToNodePathSum(bstFromSortedArray, m));
    }

    private static int rootToNodePathSum(BinaryTreeNode<Integer> bstFromSortedArray, int m) {

        if(bstFromSortedArray == null) return -1;

        if(bstFromSortedArray.data == m) return bstFromSortedArray.data;

        if(bstFromSortedArray.data > m) {
            int leftPathSum = rootToNodePathSum(bstFromSortedArray.leftNode, m);
            if (leftPathSum != -1) return leftPathSum + bstFromSortedArray.data;
        } else {
            int rightPathSum = rootToNodePathSum(bstFromSortedArray.rightNode, m);
            if (rightPathSum != -1) return rightPathSum + bstFromSortedArray.data;
        }

        return -1;
    }

    private static List<Integer> findRootToNodePathForBST(BinaryTreeNode<Integer> bstFromSortedArray, int m) {

        // base case
        if(bstFromSortedArray == null) return null;

        if(bstFromSortedArray.data == m) return new ArrayList<>(Arrays.asList(bstFromSortedArray.data));

        if(bstFromSortedArray.data > m) {
            List<Integer> leftPath = findRootToNodePathForBST(bstFromSortedArray.leftNode, m);
            if (leftPath != null) {
                leftPath.add(bstFromSortedArray.data);
                return leftPath;
            }
        } else {
            List<Integer> rightPath = findRootToNodePathForBST(bstFromSortedArray.rightNode, m);
            if (rightPath != null) {
                rightPath.add(bstFromSortedArray.data);
                return rightPath;
            }
        }

        return null;
    }

    private static HeadTailPair createLinkedListFromBST(BinaryTreeNode<Integer> bstRoot) {

        // base case
        if(bstRoot == null) return new HeadTailPair(null, null);

        LinkedListNode<Integer> rootNode = new LinkedListNode<>(bstRoot.data);
        HeadTailPair overallHeadTailPair = new HeadTailPair(rootNode, rootNode);
        HeadTailPair leftPair = createLinkedListFromBST(bstRoot.leftNode);
        HeadTailPair rightPair = createLinkedListFromBST(bstRoot.rightNode);

        if(leftPair.tail != null) {
            leftPair.tail.nextNode = rootNode;
            overallHeadTailPair.head = leftPair.head;
        }
        if(rightPair.head != null){
            rootNode.nextNode = rightPair.head;
            overallHeadTailPair.tail = rightPair.tail;
        }

        return overallHeadTailPair;
    }

    private static BinaryTreeNode<Integer> createBSTFromSortedArray(List<Integer> sortedArrayList) {
        return createBSTFromSortedArrayHelper(sortedArrayList, 0, sortedArrayList.size() - 1);
    }

    private static BinaryTreeNode<Integer> createBSTFromSortedArrayHelper(List<Integer> sortedArrayList, int startIndex, int endIndex) {

        // base case
        if(startIndex > endIndex) return null;

        int midIndex = (startIndex + endIndex)/2;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(sortedArrayList.get(midIndex));
        root.leftNode = createBSTFromSortedArrayHelper(sortedArrayList, startIndex, midIndex - 1);
        root.rightNode = createBSTFromSortedArrayHelper(sortedArrayList, midIndex + 1, endIndex);
        return root;
    }

    private static boolean checkBST2(BinaryTreeNode<Integer> binaryTreeRootLvlWise, Integer minimum, Integer maximum) {

        // base case
        if(binaryTreeRootLvlWise == null) return true;

        // minimum and maximum inclusive
        if(binaryTreeRootLvlWise.data < minimum || binaryTreeRootLvlWise.data > maximum) return false;

        return checkBST2(binaryTreeRootLvlWise.leftNode, minimum, binaryTreeRootLvlWise.data)
                && checkBST2(binaryTreeRootLvlWise.rightNode, binaryTreeRootLvlWise.data + 1, maximum);
    }

    private static checkBSTTriplet checkBST(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        if(binaryTreeRootLvlWise == null) return new checkBSTTriplet(Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        checkBSTTriplet leftBSTTriplet = checkBST(binaryTreeRootLvlWise.leftNode);
        checkBSTTriplet rightBSTTriplet = checkBST(binaryTreeRootLvlWise.rightNode);

        int overallMin = Math.min(binaryTreeRootLvlWise.data, Math.min(leftBSTTriplet.minimum, rightBSTTriplet.minimum));
        int overallMax = Math.max(binaryTreeRootLvlWise.data, Math.max(leftBSTTriplet.maximum, rightBSTTriplet.maximum));
        boolean isOverallTreeBST = leftBSTTriplet.isBST && rightBSTTriplet.isBST
                && leftBSTTriplet.maximum <= binaryTreeRootLvlWise.data && rightBSTTriplet.minimum > binaryTreeRootLvlWise.data;

        return new checkBSTTriplet(overallMax, overallMin, isOverallTreeBST);
    }

    private static void printNodesBetweenK1K2(BinaryTreeNode<Integer> binaryTreeRootLvlWise, int k1, int k2) {

        if(binaryTreeRootLvlWise == null) return;
        if(binaryTreeRootLvlWise.data > k2) printNodesBetweenK1K2(binaryTreeRootLvlWise.leftNode, k1, k2);
        else if (binaryTreeRootLvlWise.data < k1) printNodesBetweenK1K2(binaryTreeRootLvlWise.rightNode, k1, k2);
        else {
            printNodesBetweenK1K2(binaryTreeRootLvlWise.leftNode, k1, k2);
            System.out.print(binaryTreeRootLvlWise.data + " ");
            printNodesBetweenK1K2(binaryTreeRootLvlWise.rightNode, k1, k2);
        }
    }

    private static boolean searchElement(BinaryTreeNode<Integer> binaryTreeRootLvlWise, int x) {

        if(binaryTreeRootLvlWise == null) return false;
        if(binaryTreeRootLvlWise.data == x) return true;
        else if(binaryTreeRootLvlWise.data > x) return searchElement(binaryTreeRootLvlWise.leftNode, x);
        else return searchElement(binaryTreeRootLvlWise.rightNode, x);
    }

    private static void printTreeLvlWise(BinaryTreeNode<Integer> binaryTreeRootLvlWise){

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(binaryTreeRootLvlWise);

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

    private static BinaryTreeNode<Integer> createBinaryTreeLvlWise(Scanner s) {

        System.out.println("Enter root data: ");
        int rootData = s.nextInt();
        if(rootData == -1) return null;
        BinaryTreeNode<Integer> rootNode = new BinaryTreeNode<>(rootData);
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(rootNode);

        while(!queue.isEmpty()){
            BinaryTreeNode<Integer> node = queue.poll();

            System.out.println("Enter left child of node: " + node.data);
            int leftChildData = s.nextInt();
            if(leftChildData != -1){
                BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<>(leftChildData);
                node.leftNode = leftChild;
                queue.add(leftChild);
            }

            System.out.println("Enter right child of node: " + node.data);
            int rightChildData = s.nextInt();
            if(rightChildData != -1){
                BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<>(rightChildData);
                node.rightNode = rightChild;
                queue.add(rightChild);
            }
        }
        return rootNode;
    }

}

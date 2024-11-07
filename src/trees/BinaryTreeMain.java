package trees;

import java.util.*;

public class BinaryTreeMain {

    public static void main(String[] args) {

        // create a scanner
        Scanner s = new Scanner(System.in);

        // BinaryTreeNode<Integer> binaryTreeRoot = createBinaryTree(s);
        // printTree(binaryTreeRoot);

        BinaryTreeNode<Integer> binaryTreeRootLvlWise = createBinaryTreeLvlWise(s);
        printTreeLvlWise(binaryTreeRootLvlWise);

        System.out.println("Total number of nodes: " + findNoOfNodes(binaryTreeRootLvlWise));

        System.out.println("Height of the binary tree: " + findHeightOfBinaryTree(binaryTreeRootLvlWise));

        int x = 15;
        System.out.println("Node with value " + x + " exists in the tree: " + checkIfExists(binaryTreeRootLvlWise, x));

        System.out.println("Diameter of the binary tree: " + findDiameterOfBinaryTree(binaryTreeRootLvlWise));

        System.out.println("Printing tree in-order:");
        printInorder(binaryTreeRootLvlWise);
        System.out.println();

        System.out.println("Printing tree pre-order:");
        printPreOrder(binaryTreeRootLvlWise);
        System.out.println();

        System.out.println("Printing tree post-order:");
        printPostOrder(binaryTreeRootLvlWise);
        System.out.println();

        System.out.println("Given binary tree is balanced: " + checkBalancedBinaryTree(binaryTreeRootLvlWise).isBalanced);

        System.out.println("Printing nodes without siblings: ");
        printNodesWithoutSiblings(binaryTreeRootLvlWise);
        System.out.println();

        int z = 5;
        List<Integer> rootToNodePath = findRootToNodePath(binaryTreeRootLvlWise, z);
        System.out.println("Printing root to node path for element: " + z);
        for(int i : rootToNodePath) System.out.print(i + " ");
        System.out.println();

        System.out.println("Printing path sum from root to element: " + z + " : " + rootToNodePathSum(binaryTreeRootLvlWise, z));

        ArrayList<LinkedListNode<Integer>> headsOfAllLevels = convertEachLevelToALinkedList(binaryTreeRootLvlWise);
        System.out.println("Traversing through the list to print all nodes:");
        for(LinkedListNode<Integer> headOfEachLevel : headsOfAllLevels){

            LinkedListNode<Integer> currentNode = headOfEachLevel;
            while (currentNode != null){
                System.out.print(currentNode.data + " -> ");
                currentNode = currentNode.nextNode;
            }
            System.out.println();
        }

        System.out.println("Printing binary tree in zigzag order: ");
        printBinaryTreeInZigZagOrder(binaryTreeRootLvlWise);

        removeLeafNodes(binaryTreeRootLvlWise);
        System.out.println("Printing binary tree after removing the leaf nodes: ");
        printTreeLvlWise(binaryTreeRootLvlWise);

        List<Integer> preOrderList = List.of(1,2,4,9,5,3,6,8);
        List<Integer> inOrderList = List.of(9,4,2,5,1,6,3,8);
        BinaryTreeNode<Integer> binaryTreeRootPreInOrder = buildTreeFromPreOrderAndInOrderArrayLists(preOrderList, inOrderList);
        System.out.println("Printing binary tree created from the preorder and inorder lists: ");
        printTreeLvlWise(binaryTreeRootPreInOrder);

        List<Integer> postOrderList = List.of(9,4,5,2,6,8,3,1);
        BinaryTreeNode<Integer> binaryTreeRootPostInOrder = buildTreeFromPostOrderAndInOrderArrayLists(postOrderList, inOrderList);
        System.out.println("Printing binary tree created from the postorder and inorder lists: ");
        printTreeLvlWise(binaryTreeRootPostInOrder);

        mirrorBinaryTree(binaryTreeRootLvlWise);
        System.out.println("Printing the mirror image of binary tree: ");
        printTreeLvlWise(binaryTreeRootLvlWise);
        s.close();
    }

    private static int rootToNodePathSum(BinaryTreeNode<Integer> binaryTreeRootLvlWise, int m) {

        if(binaryTreeRootLvlWise == null) return -1;

        if(binaryTreeRootLvlWise.data == m) return binaryTreeRootLvlWise.data;

        int leftPathSum = rootToNodePathSum(binaryTreeRootLvlWise.leftNode, m);
        if(leftPathSum != -1) return leftPathSum + binaryTreeRootLvlWise.data;

        int rightPathSum = rootToNodePathSum(binaryTreeRootLvlWise.rightNode, m);
        if(rightPathSum != -1) return rightPathSum + binaryTreeRootLvlWise.data;

        return -1;
    }

    private static List<Integer> findRootToNodePath(BinaryTreeNode<Integer> binaryTreeRootLvlWise, int m) {

        // base case
        if(binaryTreeRootLvlWise == null) return null;

        if(binaryTreeRootLvlWise.data == m) return new ArrayList<>(Arrays.asList(binaryTreeRootLvlWise.data));

        List<Integer> leftPath = findRootToNodePath(binaryTreeRootLvlWise.leftNode, m);
        if(leftPath != null){
            leftPath.add(binaryTreeRootLvlWise.data);
            return leftPath;
        }

        List<Integer> rightPath = findRootToNodePath(binaryTreeRootLvlWise.rightNode, m);
        if(rightPath != null){
            rightPath.add(binaryTreeRootLvlWise.data);
            return rightPath;
        }

        return null;
    }

    private static void printBinaryTreeInZigZagOrder(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        if(binaryTreeRootLvlWise == null) return;

        Stack<BinaryTreeNode<Integer>> oddStack = new Stack<>();
        Stack<BinaryTreeNode<Integer>> evenStack = new Stack<>();
        oddStack.push(binaryTreeRootLvlWise);

        while(!oddStack.isEmpty() || !evenStack.isEmpty()){
            while (!oddStack.isEmpty()){
                BinaryTreeNode<Integer> node = oddStack.pop();
                System.out.print(node.data + " ");
                if(node.leftNode != null) evenStack.push(node.leftNode);
                if(node.rightNode != null) evenStack.push(node.rightNode);
            }
            System.out.println();
            while (!evenStack.isEmpty()){
                BinaryTreeNode<Integer> node = evenStack.pop();
                System.out.print(node.data + " ");
                if(node.rightNode != null) oddStack.push(node.rightNode);
                if(node.leftNode != null) oddStack.push(node.leftNode);
            }
            System.out.println();
        }
    }

    private static ArrayList<LinkedListNode<Integer>> convertEachLevelToALinkedList(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        ArrayList<LinkedListNode<Integer>> arrayList = new ArrayList<>();
        if(binaryTreeRootLvlWise == null) return arrayList;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(binaryTreeRootLvlWise);
        queue.add(null); // used as a delimiter
        LinkedListNode<Integer> headForEachLevel;
        LinkedListNode<Integer> currentNodeForEachLevel = null;

        while (!queue.isEmpty()){

            headForEachLevel = null;
            // till the time we don't get a null, all nodes are on the same level
            while (queue.peek() != null){

                BinaryTreeNode<Integer> binaryTreeNode = queue.poll();
                if(headForEachLevel == null){
                    headForEachLevel = new LinkedListNode<>(binaryTreeNode.data);
                    currentNodeForEachLevel = headForEachLevel;
                } else {
                    currentNodeForEachLevel.nextNode = new LinkedListNode<>(binaryTreeNode.data);
                    currentNodeForEachLevel = currentNodeForEachLevel.nextNode;
                }

                if(binaryTreeNode.leftNode != null) {
                    queue.add(binaryTreeNode.leftNode);
                }
                if(binaryTreeNode.rightNode != null) {
                    queue.add(binaryTreeNode.rightNode);
                }
            }

            arrayList.add(headForEachLevel);
            queue.poll(); // remove the null
            if(queue.size() > 0) queue.add(null);
        }
        return arrayList;
    }

    private static void printNodesWithoutSiblings(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        if(binaryTreeRootLvlWise == null) return;
        if(binaryTreeRootLvlWise.leftNode == null && binaryTreeRootLvlWise.rightNode == null) return;

        if(binaryTreeRootLvlWise.leftNode == null) System.out.print(binaryTreeRootLvlWise.rightNode.data + " ");
        if(binaryTreeRootLvlWise.rightNode == null) System.out.print(binaryTreeRootLvlWise.leftNode.data + " ");

        printNodesWithoutSiblings(binaryTreeRootLvlWise.leftNode);
        printNodesWithoutSiblings(binaryTreeRootLvlWise.rightNode);
    }

    private static BinaryTreeNode<Integer> removeLeafNodes(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        if(binaryTreeRootLvlWise == null) return null;

        // if root is a leaf node
        if(binaryTreeRootLvlWise.leftNode == null && binaryTreeRootLvlWise.rightNode == null) return null;

        binaryTreeRootLvlWise.leftNode = removeLeafNodes(binaryTreeRootLvlWise.leftNode);
        binaryTreeRootLvlWise.rightNode = removeLeafNodes(binaryTreeRootLvlWise.rightNode);

        return binaryTreeRootLvlWise;
    }

    private static HeightIsBalancedPair checkBalancedBinaryTree(BinaryTreeNode<Integer> binaryTreeRootLvlWise) {

        // base case
        if(binaryTreeRootLvlWise == null) return new HeightIsBalancedPair(0, true);

        HeightIsBalancedPair leftHeightIsBalancedPair = checkBalancedBinaryTree(binaryTreeRootLvlWise.leftNode);
        HeightIsBalancedPair rightHeightBalancedPair = checkBalancedBinaryTree(binaryTreeRootLvlWise.rightNode);

        int height = 1 + Math.max(leftHeightIsBalancedPair.height, rightHeightBalancedPair.height);
        boolean isBalanced = (leftHeightIsBalancedPair.isBalanced && rightHeightBalancedPair.isBalanced)
                && (Math.abs(leftHeightIsBalancedPair.height - rightHeightBalancedPair.height) <= 1);

        return new HeightIsBalancedPair(height, isBalanced);
    }

    private static BinaryTreeNode<Integer> buildTreeFromPostOrderAndInOrderArrayLists(List<Integer> postOrderList, List<Integer> inOrderList) {
        return buildTreeFromPostOrderAndInOrderArrayListsHelper(postOrderList, inOrderList, 0, postOrderList.size() - 1, 0, inOrderList.size() - 1);
    }

    private static BinaryTreeNode<Integer> buildTreeFromPostOrderAndInOrderArrayListsHelper(List<Integer> postOrderList,
                           List<Integer> inOrderList, int postOrderStart, int postOrderEnd, int inOrderStart, int inOrderEnd) {

        // base cases
        if(postOrderStart > postOrderEnd || inOrderStart > inOrderEnd) return null;

        // root will be present at the end of the postorder list
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(postOrderList.get(postOrderEnd));

        if(postOrderStart == postOrderEnd || inOrderStart == inOrderEnd) return root;

        // find the root index in the inorder list
        int rootIndexInorderList = inOrderStart;
        while(rootIndexInorderList <= inOrderEnd){
            if(inOrderList.get(rootIndexInorderList) == root.data) break;
            else rootIndexInorderList++;
        }

        int inOrderStartLeftSubtree = inOrderStart;
        int inOrderEndLeftSubtree = rootIndexInorderList - 1;
        int inOrderStartRightSubtree = rootIndexInorderList + 1;
        int inOrderEndRightSubtree = inOrderEnd;

        int noOfElementsLeftSubtree = inOrderEndLeftSubtree - inOrderStartLeftSubtree + 1;

        int postOrderStartLeftSubtree = postOrderStart;
        int postOrderEndLeftSubtree = postOrderStart + noOfElementsLeftSubtree - 1;
        int postOrderStartRightSubtree = postOrderEndLeftSubtree + 1;
        int postOrderEndRightSubtree = postOrderEnd - 1;

        root.leftNode = buildTreeFromPostOrderAndInOrderArrayListsHelper(postOrderList, inOrderList, postOrderStartLeftSubtree, postOrderEndLeftSubtree,
                inOrderStartLeftSubtree, inOrderEndLeftSubtree);
        root.rightNode = buildTreeFromPostOrderAndInOrderArrayListsHelper(postOrderList, inOrderList, postOrderStartRightSubtree, postOrderEndRightSubtree,
                inOrderStartRightSubtree, inOrderEndRightSubtree);

        return root;
    }


    private static BinaryTreeNode<Integer> buildTreeFromPreOrderAndInOrderArrayLists(List<Integer> preOrderList, List<Integer> inOrderList) {
        return buildTreeFromPreOrderAndInOrderArrayListsHelper(preOrderList, inOrderList, 0, preOrderList.size() - 1, 0, inOrderList.size() - 1);
    }

    private static BinaryTreeNode<Integer> buildTreeFromPreOrderAndInOrderArrayListsHelper(List<Integer> preOrderList,
                       List<Integer> inOrderList, int preOrderStart, int preOrderEnd, int inOrderStart, int inOrderEnd) {

        // base cases
        if(preOrderStart > preOrderEnd || inOrderStart > inOrderEnd) return null;

        // root will be present at the start of the preorder list
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preOrderList.get(preOrderStart));

        if(preOrderStart == preOrderEnd || inOrderStart == inOrderEnd) return root;

        // find the root index in the inorder list
        int rootIndexInorderList = inOrderStart;
        while(rootIndexInorderList <= inOrderEnd){
            if(inOrderList.get(rootIndexInorderList) == root.data) break;
            else rootIndexInorderList++;
        }

        int inOrderStartLeftSubtree = inOrderStart;
        int inOrderEndLeftSubtree = rootIndexInorderList - 1;
        int inOrderStartRightSubtree = rootIndexInorderList + 1;
        int inOrderEndRightSubtree = inOrderEnd;

        int noOfElementsLeftSubtree = inOrderEndLeftSubtree - inOrderStartLeftSubtree + 1;

        int preOrderStartLeftSubtree = preOrderStart + 1;
        int preOrderEndLeftSubtree = preOrderStart + noOfElementsLeftSubtree;
        int preOrderStartRightSubtree = preOrderEndLeftSubtree + 1;
        int preOrderEndRightSubtree = preOrderEnd;

        root.leftNode = buildTreeFromPreOrderAndInOrderArrayListsHelper(preOrderList, inOrderList, preOrderStartLeftSubtree, preOrderEndLeftSubtree,
                inOrderStartLeftSubtree, inOrderEndLeftSubtree);
        root.rightNode = buildTreeFromPreOrderAndInOrderArrayListsHelper(preOrderList, inOrderList, preOrderStartRightSubtree, preOrderEndRightSubtree,
                inOrderStartRightSubtree, inOrderEndRightSubtree);

        return root;
    }

    private static void printPostOrder(BinaryTreeNode<Integer> binaryTreeRoot) {

        if(binaryTreeRoot == null) return; // base case
        printPostOrder(binaryTreeRoot.leftNode);
        printPostOrder(binaryTreeRoot.rightNode);
        System.out.print(binaryTreeRoot.data + " ");
    }

    private static void printPreOrder(BinaryTreeNode<Integer> binaryTreeRoot) {

        if(binaryTreeRoot == null) return; // base case
        System.out.print(binaryTreeRoot.data + " ");
        printPreOrder(binaryTreeRoot.leftNode);
        printPreOrder(binaryTreeRoot.rightNode);
    }

    private static void printInorder(BinaryTreeNode<Integer> binaryTreeRoot) {

        if(binaryTreeRoot == null) return; // base case
        printInorder(binaryTreeRoot.leftNode);
        System.out.print(binaryTreeRoot.data + " ");
        printInorder(binaryTreeRoot.rightNode);
    }

    private static int findDiameterOfBinaryTree(BinaryTreeNode<Integer> binaryTreeRoot) { // O(N2)

        if(binaryTreeRoot == null) return 0; // base case

        int option1 = findHeightOfBinaryTree(binaryTreeRoot.leftNode) + findHeightOfBinaryTree(binaryTreeRoot.rightNode);
        int option2 = findDiameterOfBinaryTree(binaryTreeRoot.leftNode);
        int option3 = findDiameterOfBinaryTree(binaryTreeRoot.rightNode);

        return Math.max(option1, Math.max(option2, option3));
    }

    private static HeightDiameterPair findDiameterOfBinaryTree2(BinaryTreeNode<Integer> binaryTreeRoot) { // O(N)

        if(binaryTreeRoot == null) return new HeightDiameterPair(0, 0);

        HeightDiameterPair leftOutput = findDiameterOfBinaryTree2(binaryTreeRoot.leftNode);
        HeightDiameterPair rightOutput = findDiameterOfBinaryTree2(binaryTreeRoot.rightNode);

        int heightOfTree = 1 + Math.max(leftOutput.height, rightOutput.height);
        int option1 = leftOutput.height + rightOutput.height;
        int option2 = leftOutput.diameter;
        int option3 = rightOutput.diameter;
        int diameter = Math.max(option1, Math.max(option2, option3));

        return new HeightDiameterPair(heightOfTree, diameter);
    }

    private static void mirrorBinaryTree(BinaryTreeNode<Integer> binaryTreeRoot) { // O(N)

        if(binaryTreeRoot == null) return;
        // mirror the left subtree
        mirrorBinaryTree(binaryTreeRoot.leftNode);
        // mirror the right subtree
        mirrorBinaryTree(binaryTreeRoot.rightNode);

        BinaryTreeNode<Integer> temp = binaryTreeRoot.leftNode;
        binaryTreeRoot.leftNode = binaryTreeRoot.rightNode;
        binaryTreeRoot.rightNode = temp;
    }

    private static boolean checkIfExists(BinaryTreeNode<Integer> binaryTreeRoot, int x) { // O(N)

        if(binaryTreeRoot == null) return false; // base case
        if(binaryTreeRoot.data == x) return true;

        return checkIfExists(binaryTreeRoot.leftNode, x) || checkIfExists(binaryTreeRoot.rightNode, x);
    }

    private static int findHeightOfBinaryTree(BinaryTreeNode<Integer> binaryTreeRoot) { // O(N)

        if(binaryTreeRoot == null) return 0;
        return 1 + Math.max(findHeightOfBinaryTree(binaryTreeRoot.leftNode), findHeightOfBinaryTree(binaryTreeRoot.rightNode));
    }

    private static int findNoOfNodes(BinaryTreeNode<Integer> binaryTreeRoot){ // O(N)

        if(binaryTreeRoot == null) return 0; // base case
        return 1 + findNoOfNodes(binaryTreeRoot.leftNode) + findNoOfNodes(binaryTreeRoot.rightNode);
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

    private static void printTreeLvlWise2(BinaryTreeNode<Integer> binaryTreeRootLvlWise){

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(binaryTreeRootLvlWise);
        queue.add(null);
        System.out.println(binaryTreeRootLvlWise.data);

        while(!queue.isEmpty()){

            StringBuilder sb = new StringBuilder();
            // till the time we don't get a null, all the nodes are on the same level
            while(queue.peek() != null) {

                BinaryTreeNode<Integer> node = queue.poll();
                if (node.leftNode != null) {
                    sb.append(node.leftNode.data).append(" ");
                    queue.add(node.leftNode);
                }
                if (node.rightNode != null) {
                    sb.append(node.rightNode.data).append(" ");
                    queue.add(node.rightNode);
                }
            }
            System.out.println(sb);
            queue.poll();
            if(queue.size() > 0) queue.add(null);
        }
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

    private static void printTree(BinaryTreeNode<Integer> binaryTreeRoot){

        if(binaryTreeRoot == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append(binaryTreeRoot.data);
        if(binaryTreeRoot.leftNode != null) sb.append(" L : ").append(binaryTreeRoot.leftNode.data);
        if(binaryTreeRoot.rightNode != null) sb.append(" R : ").append(binaryTreeRoot.rightNode.data);
        System.out.println(sb);

        printTree(binaryTreeRoot.leftNode);
        printTree(binaryTreeRoot.rightNode);
    }

    private static BinaryTreeNode<Integer> createBinaryTree(Scanner s) {

        System.out.println("Enter node data: ");
        int rootData = s.nextInt();
        if(rootData == -1) return null; // base case

        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(rootData);
        node.leftNode = createBinaryTree(s);
        node.rightNode = createBinaryTree(s);

        return node;
    }
}

package trees;

import java.util.*;

public class NryTreeMain {

    public static void main(String[] args) {

        // create a scanner
        Scanner s = new Scanner(System.in);

        // create a nry tree
        //trees.NryTreeNode<Integer> nryTreeRoot  = createNryTree(s);
        // create a nry tree level wise
        NryTreeNode<Integer> nryTreeRootLvlWise  = createNryTreeLvlWise(s);

        // print tree
        //printTree(nryTreeRoot);

        // print level wise
        printTreeLvlWise(nryTreeRootLvlWise);

        // no of nodes
        System.out.println("Total Number of Nodes: " + findNoOfNodes(nryTreeRootLvlWise));

        // height of tree
        System.out.println("Height of the Tree: " + findHeight(nryTreeRootLvlWise));

        // depth of a node
        System.out.println("Depth of the Node 5: " + findDepth(nryTreeRootLvlWise, 5, 0));

        // nodes at a depth k
        System.out.println("Printing nodes at depth 2: ");
        printAllNodesAtDepthK(nryTreeRootLvlWise, 2);
        System.out.println();

        // number of leaf nodes
        System.out.println("Number of leaf nodes in the tree: " + findNoOfLeafNodes(nryTreeRootLvlWise));
        // print all leaf nodes
        System.out.println("Printing all leaf nodes:");
        printAllLeafNodes(nryTreeRootLvlWise);
        System.out.println();

        // number of nodes greater than x
        int x = 5;
        System.out.println("Total Number of Nodes greater than " + x + ": " + findNoOfNodesGreaterThanX(nryTreeRootLvlWise, x));

        // sum of all nodes
        System.out.println("Sum of all nodes of the tree: " + findSumOfAllNodes(nryTreeRootLvlWise));

        // product of all nodes
        System.out.println("Product of all nodes of the tree: " + findProductOfAllNodes(nryTreeRootLvlWise));

        // node with the largest data
        System.out.println("Node with largest data: " + findLargestDataNode(nryTreeRootLvlWise));

        // node with second-largest data
        System.out.println("Node with second largest data: " + findSecondLargestDataNode(nryTreeRootLvlWise).get(1));

        // pre, in and post order traversals
        System.out.println("Printing tree in pre-order");
        printTreePreOrder(nryTreeRootLvlWise);
        System.out.println();

        System.out.println("Printing tree in post-order");
        printTreePostOrder(nryTreeRootLvlWise);
        System.out.println();

        // node with value just greater than y
        int y = 5;
        System.out.println("Value of node just greater than " + y + " : " + findNodeJustGreaterThanY(nryTreeRootLvlWise, y));

        // node having sum of children and itself maximum
        System.out.println("Node having sum of children and itself maximum: "
                + findNodeHavingSumOfChildrenAndItselfMaximumBetterApproach(nryTreeRootLvlWise).nodeData);

        // structurally identical trees
//        trees.NryTreeNode<Integer> nryTreeRootLvlWise2  = createNryTreeLvlWise(s);
//        System.out.println("These trees are structurally identical to each other: "
//                + checkStructurallyIdenticalTrees(nryTreeRootLvlWise, nryTreeRootLvlWise2));

        // replace each node data with depth
        replaceEachNodeDataWithDepth(nryTreeRootLvlWise, 0);
        System.out.println("Tree after replacing each node data with its depth:");
        printTreeLvlWise(nryTreeRootLvlWise);

        s.close();
    }

    private static NryTreeNode<Integer> findNodeHavingSumOfChildrenAndItselfMaximum(NryTreeNode<Integer> nryTreeRootLvlWise) {

        NryTreeNode<Integer> currentNodeRef = nryTreeRootLvlWise;
        int sum = nryTreeRootLvlWise.data;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            sum += nryTreeRootLvlWise.children.get(i).data;
        }

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            NryTreeNode<Integer> childNodeWithMaxSum = findNodeHavingSumOfChildrenAndItselfMaximum(nryTreeRootLvlWise.children.get(i));
            int childSum = childNodeWithMaxSum.data;
            for(int j = 0; j < childNodeWithMaxSum.children.size(); j++){
                childSum += childNodeWithMaxSum.children.get(j).data;
            }
            if(childSum > sum) currentNodeRef = childNodeWithMaxSum;
        }

        return currentNodeRef;
    }

    private static Pair findNodeHavingSumOfChildrenAndItselfMaximumBetterApproach(NryTreeNode<Integer> nryTreeRootLvlWise) {

        // edge case
        if(nryTreeRootLvlWise == null) return null;

        int nodeData = nryTreeRootLvlWise.data;
        int sumData = nryTreeRootLvlWise.data;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            sumData += nryTreeRootLvlWise.children.get(i).data;
        }

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            Pair childNodeWithMaxSum = findNodeHavingSumOfChildrenAndItselfMaximumBetterApproach(nryTreeRootLvlWise.children.get(i));
            int childNodeData = childNodeWithMaxSum.nodeData;
            int childSumData = childNodeWithMaxSum.sumData;
            if(childSumData > sumData) {
                nodeData = childNodeData;
                sumData = childSumData;
            }
        }

        return new Pair(nodeData, sumData);
    }

    private static Integer findNodeJustGreaterThanY(NryTreeNode<Integer> nryTreeRootLvlWise, int y) {

        // edge case
        if(nryTreeRootLvlWise == null) return null;

        Integer nodeJustGreaterThanY = nryTreeRootLvlWise.data > y ? nryTreeRootLvlWise.data : null;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            Integer childNodeJustGreaterThanY = findNodeJustGreaterThanY(nryTreeRootLvlWise.children.get(i), y);
            if(childNodeJustGreaterThanY != null){
                if(nodeJustGreaterThanY != null){
                    if(childNodeJustGreaterThanY < nodeJustGreaterThanY){
                        nodeJustGreaterThanY = childNodeJustGreaterThanY;
                    }
                } else nodeJustGreaterThanY = childNodeJustGreaterThanY;
            }
        }
        return nodeJustGreaterThanY;
    }

    private static boolean checkStructurallyIdenticalTrees(NryTreeNode<Integer> nryTreeRootLvlWise,
                                                           NryTreeNode<Integer> nryTreeRootLvlWise2) {
        // edge case
        if(nryTreeRootLvlWise == null || nryTreeRootLvlWise2 == null) return false; // two nulls can't be identical

        if(nryTreeRootLvlWise.data != nryTreeRootLvlWise2.data ||
                nryTreeRootLvlWise.children.size() != nryTreeRootLvlWise2.children.size()) return false;

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            if(!checkStructurallyIdenticalTrees(nryTreeRootLvlWise.children.get(i), nryTreeRootLvlWise2.children.get(i)))
                return false;
        }
        return true;
    }

    // root will be printed last
    private static void printTreePostOrder(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            printTreePostOrder(nryTreeRootLvlWise.children.get(i));
        }
        System.out.print(nryTreeRootLvlWise.data + " ");
    }

    // root will be printed first
    private static void printTreePreOrder(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return;
        System.out.print(nryTreeRootLvlWise.data + " ");
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            printTreePreOrder(nryTreeRootLvlWise.children.get(i));
        }
    }

    private static void printAllLeafNodes(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return; // edge case

        // base case
        if(nryTreeRootLvlWise.children.size() == 0) {
            System.out.print(nryTreeRootLvlWise.data + " ");
            return;
        }

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            printAllLeafNodes(nryTreeRootLvlWise.children.get(i));
        }
    }

    private static int findNoOfLeafNodes(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return -1; // edge case

        int count = nryTreeRootLvlWise.children.size() == 0 ? 1 : 0; // base case

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            count += findNoOfLeafNodes(nryTreeRootLvlWise.children.get(i));
        }
        return count;
    }

    private static void replaceEachNodeDataWithDepth(NryTreeNode<Integer> nryTreeRootLvlWise, int depth) {

        if(nryTreeRootLvlWise == null) return;
        nryTreeRootLvlWise.data = depth;

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            replaceEachNodeDataWithDepth(nryTreeRootLvlWise.children.get(i), depth + 1);
        }
    }

    private static void printAllNodesAtDepthK(NryTreeNode<Integer> nryTreeRootLvlWise, int depth) {

        if(nryTreeRootLvlWise == null) return; // edge case
        if(depth == 0) {
            System.out.print(nryTreeRootLvlWise.data + " "); // base case
            return;
        }

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            printAllNodesAtDepthK(nryTreeRootLvlWise.children.get(i), depth - 1);
        }
    }

    private static int findDepth(NryTreeNode<Integer> nryTreeRootLvlWise, int x, int depth) {

        if(nryTreeRootLvlWise == null) return -1; // edge case

        if(nryTreeRootLvlWise.data == x) return depth; // base case

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            int childDepth = findDepth(nryTreeRootLvlWise.children.get(i), x, depth + 1);
            if(childDepth != -1) {
                return childDepth;
            }
        }
        return -1; // node not found in the subtree
    }

    private static int findHeight(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return 0;
        int maxHeightSubTree = 0;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            maxHeightSubTree = Math.max(maxHeightSubTree, findHeight(nryTreeRootLvlWise.children.get(i)));
        }
        return 1 + maxHeightSubTree;
    }

    private static List<Integer> findSecondLargestDataNode(NryTreeNode<Integer> nryTreeRootLvlWise) {

        // edge case
        if(nryTreeRootLvlWise == null) return List.of(Integer.MIN_VALUE, Integer.MIN_VALUE);

        Integer largest = nryTreeRootLvlWise.data;
        Integer secondLargest = Integer.MIN_VALUE;

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            List<Integer> childArrayList = findSecondLargestDataNode(nryTreeRootLvlWise.children.get(i));
            if(childArrayList.get(0) > largest){
                if(childArrayList.get(1) > largest){
                    secondLargest = childArrayList.get(1);
                    largest = childArrayList.get(0);
                } else {
                    secondLargest = largest;
                    largest = childArrayList.get(0);
                }
            } else if(childArrayList.get(0) == largest) {
                if(childArrayList.get(1) > secondLargest){
                    secondLargest = childArrayList.get(1);
                }
            } else {
                if(childArrayList.get(0) > secondLargest){
                    secondLargest = childArrayList.get(0);
                }
            }
        }
        return List.of(largest, secondLargest);
    }

    private static int findLargestDataNode(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return -1;
        int largestNodeData = nryTreeRootLvlWise.data;

        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            largestNodeData = Math.max(largestNodeData, findLargestDataNode(nryTreeRootLvlWise.children.get(i)));
        }
        return largestNodeData;
    }

    private static int findProductOfAllNodes(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return -1;
        int productOfAllNodes = nryTreeRootLvlWise.data;

        // no base condition. if it is a leaf node, it won't go inside the for loop and simply return node data
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            productOfAllNodes *= findProductOfAllNodes(nryTreeRootLvlWise.children.get(i));
        }
        return productOfAllNodes;
    }

    private static int findSumOfAllNodes(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return -1;
        int sumOfAllNodes = nryTreeRootLvlWise.data;

        // no base condition. if it is a leaf node, it won't go inside the for loop and simply return node data
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            sumOfAllNodes += findSumOfAllNodes(nryTreeRootLvlWise.children.get(i));
        }
        return sumOfAllNodes;
    }

    private static int findNoOfNodesGreaterThanX(NryTreeNode<Integer> nryTreeRootLvlWise, int x) {

        if(nryTreeRootLvlWise == null) return 0;
        int noOfNodesGraterThanX = nryTreeRootLvlWise.data > x ? 1 : 0;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            noOfNodesGraterThanX += findNoOfNodesGreaterThanX(nryTreeRootLvlWise.children.get(i), x);
        }
        return noOfNodesGraterThanX;
    }

    private static int findNoOfNodes(NryTreeNode<Integer> nryTreeRootLvlWise) {

        if(nryTreeRootLvlWise == null) return 0;
        int noOfNodes = 1;
        for(int i = 0; i < nryTreeRootLvlWise.children.size(); i++){
            noOfNodes += findNoOfNodes(nryTreeRootLvlWise.children.get(i));
        }
        return noOfNodes;
    }

    private static void printTreeLvlWise(NryTreeNode<Integer> nryTreeRootLvlWise) {

        Queue<NryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(nryTreeRootLvlWise);
        queue.add(null); // used as a delimiter
        System.out.println(nryTreeRootLvlWise.data);

        while(!queue.isEmpty()){

            StringBuilder sb = new StringBuilder();
            // till the time we don't get a null, all nodes are on the same level
            while (queue.peek() != null){
                NryTreeNode<Integer> node = queue.poll();
                for(int i =0; i < node.children.size(); i++){

                    NryTreeNode<Integer> child = node.children.get(i);
                    sb.append(child.data).append(" ");
                    queue.add(child);
                }
            }
            System.out.println(sb);
            queue.poll(); // remove null
            if(queue.size() > 0) queue.add(null);
        }
    }

    private static void printTree(NryTreeNode<Integer> nryTreeRoot) {

        String s = nryTreeRoot.data + ":";
        for(int i = 0; i < nryTreeRoot.children.size(); i++){
            s += nryTreeRoot.children.get(i).data + ",";
        }
        System.out.println(s);
        for(int i = 0; i < nryTreeRoot.children.size(); i++){
            printTree(nryTreeRoot.children.get(i));
        }
    }

    private static NryTreeNode<Integer> createNryTree(Scanner s) {

        System.out.println("Enter data for node: ");
        NryTreeNode<Integer> node = new NryTreeNode<>(s.nextInt());

        System.out.println("Enter number of children: ");
        int countChildren = s.nextInt();
        for(int i = 0; i < countChildren; i++){
            node.children.add(createNryTree(s));
        }

        return node;
    }

    private static NryTreeNode<Integer> createNryTreeLvlWise(Scanner s) {

        System.out.println("Enter data for root node: ");
        NryTreeNode<Integer> root = new NryTreeNode<>(s.nextInt());

        Queue<NryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            NryTreeNode<Integer> node = queue.poll();
            System.out.println("Enter number of children for : "+node.data);
            int noOfChildren = s.nextInt();

            for(int i = 1; i <= noOfChildren; i++){

                String adj = "th";
                switch (i) {
                    case 1 -> adj = "st";
                    case 2 -> adj = "nd";
                    case 3 -> adj = "rd";
                }
                System.out.println("Enter " + i + adj + " child data:");
                NryTreeNode<Integer> child = new NryTreeNode<>(s.nextInt());
                node.children.add(child);
                queue.add(child);
            }

        }
        return root;
    }
}


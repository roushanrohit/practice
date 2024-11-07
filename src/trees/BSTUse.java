package trees;

public class BSTUse {

    public static void main(String[] args) {

        BST bst = new BST();
        bst.insertData(4);
        bst.insertData(2);
        bst.insertData(6);
        bst.insertData(1);
        bst.insertData(3);
        bst.insertData(5);
        bst.insertData(7);

        bst.printBST();
        int x = 8;
        System.out.println("Is " + x + " present in the BST: " + bst.hasData(x));

        bst.deleteData(4);
        bst.printBST();
    }
}

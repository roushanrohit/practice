package trees;

public class BinaryTreeNode<T> {

    T data;
    BinaryTreeNode<T> leftNode;
    BinaryTreeNode<T> rightNode;

    BinaryTreeNode(T data){
        this.data = data;
    }

    BinaryTreeNode(T data, BinaryTreeNode<T> leftNode, BinaryTreeNode<T> rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
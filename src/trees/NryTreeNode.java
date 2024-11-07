package trees;

import java.util.ArrayList;
import java.util.List;

public class NryTreeNode<T> {

    T data;
    List<NryTreeNode<T>> children;

    NryTreeNode(T data){
        this.data = data;
        children = new ArrayList<>();
    }
}
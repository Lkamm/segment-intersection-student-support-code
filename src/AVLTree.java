import java.util.function.BiPredicate;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */

// need to have a function that finds lowest AVL and stores that information
    // may need to make it void

    public Node<K> insert(K key)
    {
        return fixAVL(super.insert(key));
    }

    public Node<K> fixAVL(Node<K> inserted)
    {
       // System.out.println(" " + inserted + "this was just inserted");
        Node<K> curr = inserted;
        while (curr != null){
            curr.updateHeight();
            int balance = getBalance(curr);

            if (balance > 1){
                if (getBalance(curr.left) < 0){
                    rotateLeft(curr.left);
                }
                rotateRight(curr);
            }else if (balance < -1){
              //  System.out.println("ZigZag right " + curr.right + " left "+ curr);
                if (getBalance(curr.right) > 0){
                    rotateRight(curr.right);
                }
                rotateLeft(curr);
            }
            curr = curr.parent;
        }
        return inserted;
    }
    private int getBalance(Node<K> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = (node.left == null) ? -1 : node.left.height;
        int rightHeight = (node.right == null) ? -1 : node.right.height;

        return leftHeight - rightHeight;

    }




    public Node<K> rotateRight(Node<K> p){
        Node<K> newSubRoot = p.left;
        p.left = newSubRoot.right;
        if(newSubRoot.right != null) {
            newSubRoot.right.parent = p;
        }
        newSubRoot.right = p;
        newSubRoot.parent = p.parent;
        if(p.parent == null) {
            this.root = newSubRoot;
        }else if(p == p.parent.left) {
            p.parent.left = newSubRoot;
        }else {
            p.parent.right = newSubRoot;
        }
        p.parent= newSubRoot;

        p.updateHeight();
        newSubRoot.updateHeight();

        return newSubRoot;
    }



    public Node<K> rotateLeft(Node<K> p){
        Node<K> newSubRoot = p.right;
        p.right = newSubRoot.left;

        if(newSubRoot.left != null) {
            newSubRoot.left.parent = p;
        }
        newSubRoot.left = p;
        newSubRoot.parent = p.parent;
        if(p.parent == null) {
            this.root = newSubRoot;
        }else if(p == p.parent.left) {
            p.parent.left = newSubRoot;
        }else {
            p.parent.right = newSubRoot;
        }
        p.parent= newSubRoot;

        p.updateHeight();
        newSubRoot.updateHeight();

        return newSubRoot;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
        Node<K> nodeToRemove = super.search(key);
        if (nodeToRemove == null) {
            return;
        }

        Node<K> rebalanceStart = nodeToRemove.parent;

        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            Node<K> successor = nodeToRemove.right.first();
            if (successor == nodeToRemove.right) {
                rebalanceStart = successor;
            } else {
                rebalanceStart = successor.parent;
            }
        }

        super.remove(key);

        if (rebalanceStart != null) {
            fixAVL(rebalanceStart);
        }
    }
}

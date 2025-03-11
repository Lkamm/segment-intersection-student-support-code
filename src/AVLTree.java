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
    public Node<K> insert(K key)
    {
        Node<K> newRoot =  super.insert(key);
          newRoot = fixAVL(newRoot);
        // System.out.println(newRoot.height);
          return newRoot;
    }

// need to have a function that finds lowest AVL and stores that information
    // may need to make it void

    public Node<K> fixAVL(Node<K> node) {
        if (node.isNodeAVL()) {
            if (node.parent == null) {
                // System.out.println(node);
                return node;
            } else {
                return fixAVL(node.parent);
            }
        }
        else {
            //System.out.println(node.data);
            if (get_height(node.left) <= get_height(node.right)) {
                if (get_height(node.right.left) <= get_height(node.right.right)) {
                    int k = node.right.right.height;
                    System.out.println("rotateLeft");
                    node = rotateLeft(node);
                } else if (get_height(node.right.left) > get_height(node.right.right)) {
                    int k = node.right.left.height;
                    node.right = rotateRight(node.right);
                    System.out.println("RotateLeftzLeftx");
                    node = rotateLeft(node);
                    // rotate right on z then rotate left on x
                }
            } else if (get_height(node.left) > get_height(node.right)) {
                if (get_height(node.left.left) < get_height(node.left.right)) {
                    int k = node.left.right.height;
                    System.out.println("RotateLeftyRightx");
                    node.left = rotateLeft(node.left);
                    node = rotateRight(node);
                    // rotate left on y then right on x
                } else if (get_height(node.left.left) >= get_height(node.left.right)) {
                    int k = node.left.left.height;
                    System.out.println("rotateRightx");
                    //rotate right on x;
                    node = rotateRight(node);
                }
            }
            node.updateHeight();
            if (node.parent != null) {
                return fixAVL(node.parent);
            }
                return node;
            }
        }

    public Node<K> rotateRight(Node<K> root)
    {
        System.out.println("rotatingRight");
        Node<K> newRoot = root.left;
        root.left = newRoot.right;
        if (newRoot.right != null) {
            newRoot.right.parent = root;
        }
        newRoot.right = root;
        newRoot.parent = root.parent;
        root.parent = newRoot;
        root.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    public Node<K> rotateLeft(Node<K> root)
    {
        System.out.println("RotatingLeft");
        Node<K> newRoot = root.right;
        root.right = newRoot.left;
        if (newRoot.left != null) {
            newRoot.left.parent = root;
        }
        newRoot.left = root;
        newRoot.parent = root.parent;
        root.parent = newRoot;
        root.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
      Node<K> tempNode = super.search(key);
      tempNode = tempNode.parent;
      super.remove(key);
      fixAVL(tempNode);
    }
}

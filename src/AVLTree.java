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
         Node<K> newNode = super.insert(key);
         System.out.println(newNode.get());
         return fixAVL(newNode);
    }

// need to have a function that finds lowest AVL and stores that information

    public Node<K> fixAVL(Node<K> root)
    {
        if (root == null)
        {
            return null;
        }
        if(root.isNodeAVL())
        {
            if (root.parent == null)
            {
                return root;
            } else
            {
               return fixAVL(root.parent);
            }
        }
        if (get_height(root.left) <= get_height(root.right))
        {
            if (get_height(root.right.left) <= get_height(root.right.right)) {
                int k = root.right.right.height;
                System.out.println("rotateLeft");
                // rotate left on x
                rotateLeft(root);


            }
            if (get_height(root.right.left) > get_height(root.right.right))
            {
                int k = root.right.left.height;
                rotateRight(root.right);
                Node<K> newRoot = rotateRight(root.right);
                System.out.println("RotateLeftzLeftx");
                rotateLeft(root);


                // rotate right on z then rotate left on x
            }
        }
        if(get_height(root.left) > get_height(root.right)) {
            if (get_height(root.left.left) < get_height(root.left.right)) {
                int k = root.left.right.height;
                System.out.println("RotateLeftyRightx");

                rotateLeft(root.left);
                 rotateRight(root);
                // rotate left on y then right on x
            }
            if (get_height(root.left.left) >= get_height(root.left.right)) {
                int k = root.left.left.height;
                System.out.println("rotateRightx");
                //rotate right on x;
                 rotateRight(root);
            }
        }
            if(root.parent != null)
            {
                return fixAVL(root.parent);
            }
            else {
                return root;
            }
        }

    public Node<K> rotateRight(Node<K> root)
    {
        Node<K> newRoot = root.left;
        newRoot.right = root;
        root.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    public Node<K> rotateLeft(Node<K> root)
    {
        Node<K> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.updateHeight();
        newRoot.updateHeight();
        return root;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
      //  Node<K> tempNode = super.search(key);
      //  super.remove(key);
      //  fixAVL(tempNode);
        // delete this line and add your code
    }
}

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
         root = insert_helper(key, root);
         System.out.println(root);
         if (root == null)
         {
             return null;
         }
         else {
             if(root.parent != null)
             {
                 if (root.parent.isNodeAVL())
                 {
                     return root;
                 }
                 else
                 {
                     System.out.println(root.parent);
                     return fixAVL(root.parent);
                 }
             }
             }
         return root;
    }
    protected Node<K> insert_helper(K key, Node<K> curr)
    {
        Node<K> newNode = curr;
        if (curr == null)
        {
            ++numNodes;
             newNode = new Node<>(key, null, null);
        }
        else if (lessThan.test(key, curr.data)) {
            curr.left = insert_helper(key, curr.left);
            curr.updateHeight();
           // return curr;
        } else if (lessThan.test(curr.data, key)) {
            curr.right = insert_helper(key, curr.right);
            curr.updateHeight();
           // return curr;
        } else {
            // duplicate; do nothing
            return curr;
        }
        return newNode;
    }

// need to have a function that finds lowest AVL and stores that information

    public Node<K> fixAVL(Node<K> root)
    {
        System.out.println(root.get());
        if (root.left.height <= root.right.height)
        {
            if(root.right.left.height <= root.right.right.height)
            {
                int k = root.right.right.height;
                // rotate left on x
                System.out.println("rotateLeft");
                 rotateLeft(root);

            }
            if (root.right.left.height > root.right.right.height)
            {
                int k = root.right.left.height;
                rotateRight(root.right);
                Node<K> newRoot = rotateRight(root.right);
                rotateLeft(root);
                // rotate right on z then rotate left on x
            }
            if (root.left.left.height < root.left.right.height)
            {
                int k = root.left.right.height;
                rotateLeft(root.left);
                rotateRight(root);
                // rotate left on y then right on x
            }
            if (root.left.left.height >= root.left.right.height)
            {
                int k = root.left.left.height;
                //rotate right on x;
                 rotateRight(root);
            }
        }
        return null;
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
        // delete this line and add your code
    }
}

/**
 * A Node<K> is a Location (defined in OrderedSet.java), which
 * means that it can be the return value of a search on the tree.
 */

class Node<K> implements Location<K> {

    protected K data;
    protected Node<K> left, right;
    protected Node<K> parent;
    protected int height;

    /**
     * Constructs a leaf Node<K> with the given key.
     */
    public Node(K key) {
        this(key, null, null);
    }

    /**
     * Constructs a new Node<K> with the given values for fields.
     */
    public Node(K data, Node<K> left, Node<K> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
        this.parent = null;
        updateHeight();
    }

    /*
     * Provide the get() method required by the Location interface.
     */
    @Override
    public K get() {
        return data;
    }

    /**
     * Return true iff this Node<K> is a leaf in the tree.
     */
    protected boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Performs a local update on the height of this Node<K>. Assumes that the
     * heights in the child Node<K>s are correct. Returns true iff the height
     * actually changed. This function *must* run in O(1) time.
     */
    protected boolean updateHeight() {
        int h = 1 + Math.max(BinarySearchTree.get_height(left), BinarySearchTree.get_height(right));
        if (height == h)
            return false;
        else {
            height = h;
            return true;
        }
    }

    /**
     * Returns the location of the Node<K> containing the inorder predecessor
     * of this Node<K>.
     */
    @Override
    public Location<K> previous() {
        if (left != null)
            return left.last();
        else {
            return prevAncestor(this);
        }
    }

    /**
     * Returns the location of the Node<K> containing the inorder successor
     * of this Node<K>.
     */
    @Override
    public Location<K> next() {
        if (right != null)
            return right.first();
        else {
            return nextAncestor(this);
        }
    }

    /**
     * This method should return the closest ancestor of Node<K> q
     * whose key is less than q's key. It is not necessary
     * to perform key comparisons to implement this method.
     */
    protected Node<K> prevAncestor(Node<K> q) {
        Node<K> p = q.parent;
        while (p != null && p.left == q) {
            q = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * <p>
     * This method should return the closest ancestor of Node<K> q
     * whose key is greater than q's key. It is not necessary
     * to perform key comparisons to implement this method.
     */
    protected Node<K> nextAncestor(Node<K> q) {
        Node<K> p = q.parent;
        while (p != null && p.right == q) {
            q = p;
            p = p.parent;
        }
        return p;
    }

    /*
     * This method should return the Node<K> in the subtree rooted at 'this'
     * that has the smallest key.
     */
    protected Node<K> first() {
        if (this.left == null)
            return this;
        return this.left.first();
    }

    /*
     * This method should return the Node<K> in the subtree rooted at 'this'
     * that has the largest key.
     */
    private Node<K> last() {
        if (this.right == null)
            return this;
        return this.right.last();
    }

    public boolean isNodeAVL() {
        int h1, h2;
        h1 = BinarySearchTree.get_height(left);
        h2 = BinarySearchTree.get_height(right);
        return Math.abs(h2 - h1) < 2;
    }

    public String toString() {
        return BinarySearchTree.toStringPreorder(this);
    }

}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements OrderedSet<K> {

    protected Node<K> root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */
    public Node<K> search(K key)
    {
        Node<K> n = find(key, root, null);
        if
            (n == null)
        {
            return null;
        }
        else if (n.data.equals(key))
    {
        return n;
    }
	else
    {
        return null;
    }
    }
    protected Node<K> find(K key, Node<K> curr, Node<K> parent)
    {
        if (curr == null) {
            return parent;
        } else if (lessThan.test(key, curr.data)) {
            return find(key, curr.left, curr);
        }
        else if (lessThan.test(curr.data, key)) {
            return find(key, curr.right, curr);
        }
        else {
            return curr;
        }
    }
    /**
     * TODO
     * <p>
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height()
    {
        return root.height;
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear()
    {
        this.numNodes = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size()
    {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new Node<K> and
     * the heights in all Node<K> along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * Node<K> containing the key), or null if the key is already present.
     */
    public Node<K> insert(K key)
    {
        return insert_helper(key, root);
    }
    protected Node<K> insert_helper(K key, Node<K> curr)
    {
        if (curr == null)
        {
            ++numNodes;
            Node<K> newNode = new Node<>(key, null, null);
            if(root == null)
            {
                root = newNode;
            }
            return newNode;
        }
        else if (lessThan.test(key, curr.data)) {
            curr.left = insert_helper(key, curr.left);
            curr.updateHeight();
            return curr;
        } else if (lessThan.test(curr.data, key)) {
            curr.right = insert_helper(key, curr.right);
            curr.updateHeight();
            return curr;
        } else {
            return curr;
        }
    }
    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return toStringPreorder(root);
    }

    /**
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node<K> p = search(key);
        return p != null;
    }

    /**
     * TODO
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key)
    {
        root = remove_helper(root, key);
    }
    private Node<K> remove_helper(Node<K> curr, K key)
    {
        if (curr == null) {
            return null;
        } else if (lessThan.test(key, curr.data)) { // remove in left subtree
            curr.left = remove_helper(curr.left, key);
            curr.updateHeight();
            --numNodes;
            return curr;
        } else if (lessThan.test(curr.data, key)) { // remove in right subtree
            curr.right = remove_helper(curr.right, key);
            curr.updateHeight();
            --numNodes;
            return curr;
        } else
        {      // remove this node
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                curr.updateHeight();
                --numNodes;
                return curr.left;
            } else
            {   // two children, replace with first of right subtree
                Node<K> min = curr.right.first();
                curr.data = min.data;
                curr.right = remove_helper(curr.right, min.data);
                curr.updateHeight();
                // may need to call the correct curr.right on updateHeight. WIll have to test this
                --numNodes;
                return curr;
            }
        }
    }
    /**
     * TODO
     * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys()
    {
     List<K> list = new ArrayList<>();
     Node<K> curr = root.first();
     while(list.size() != numNodes)
     {
         list.add(curr.get());
         curr = (Node<K>) curr.next();
     }
     return list;
    }

    static protected <K> String toStringPreorder(Node<K> p) {
        if (p == null) return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * The get_height method returns the height of the Node<K> n, which may be null.
     */
    static protected <K> int get_height(Node<K> n)
    {
        if (n == null) return -1;
        else return n.height;
    }


}

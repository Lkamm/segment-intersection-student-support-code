import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Random;


public class StudentTest {

    @Test
    public void insertSmallBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        /*
         *       4
         *     /  \
         *    /    \
         *   0      8
         *    \    / \
         *     2  6   10
         */
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
        }
        for (int i = 0; i != 11; ++i) {
            assertEquals(bst.contains(i), map.containsKey(i));
        }
        // make sure to make a test that checks for duplicates
    }

    @Test
    public void testKeys()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        list.add(10);
        for (Integer key : list)
        {
            bst.insert(key);
        }
        List<Integer> keys = bst.keys();
        assertEquals(keys, list);
    }

    @Test
    public void testClear()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        list.add(10);
        for (Integer key : list)
        {
            bst.insert(key);
        }
        bst.clear();
        List<Integer> keys = bst.keys();
    boolean theTruth = list.equals(keys);
    assertFalse(theTruth);

    }

    /**
     * TODO: Test cases
     */
    @Test
    public void test() {
        insertSmallBST();
        testKeys();
        testClear();
    }

    /*
     * Helper Method to Validate AVL Property AVL Tree.
     */

    // Check that the tree is an AVL tree.
    public static <K> void validate_AVL_property(AVLTree<K> tree) {
        validate_AVL_property(tree.root);
    }
    // Checks that the subtree rooted at location n is and AVL tree
    // and returns the height of this subtree.
    public static <K> int validate_AVL_property(Node<K> n) {
        if (n == null) {
            return -1;
        } else {
            int h1, h2;
            h1 = validate_AVL_property(n.left);
            h2 = validate_AVL_property(n.right);
            assertTrue(Math.abs(h2 - h1) < 2);
            return 1 + Math.max(h1, h2);
        }
    }
}

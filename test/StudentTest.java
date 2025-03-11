import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Random;
import java.util.function.BiPredicate;


public class StudentTest {

    @Test
    public void insertSmallBST()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10,};
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
    public void testDups()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10, 11, 12, 4, 6, 8, 10};
        int[] b = new int[]{0, 2, 4, 6, 8, 10, 11, 12};
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
        for (int i = 0; i < bst.keys().size() && i < b.length; i++)
        {
            assertEquals(b[i], bst.keys().get(i));
        }

        bst.remove(4);
        bst.remove(10);
        bst.insert(4);
        bst.insert(4);
        bst.insert(10);
        bst.insert(10);
        for (int i = 0; i < bst.keys().size() && i < b.length; i++)
        {
            assertEquals(b[i], bst.keys().get(i));
        }

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
    public void test()
    {
        insertSmallBST();
        testKeys();
        testClear();
        insertSmallLeftLeaningAVL();
        nodeTest();
    }

    @Test
    public void nodeTest()
    {
        testNodeGet();
        testNodeIsLeaf();
        testNodeUpdateHeightGetHeight();
    }

    /*
     * Helper Method to Validate AVL Property AVL Tree.
     */

    // Check that the tree is an AVL tree.
    public static <K> void validate_AVL_propertys(AVLTree<K> tree)
    {
        validate_AVL_property(tree.root);
    }
    // Checks that the subtree rooted at location n is and AVL tree
    // and returns the height of this subtree.
    public static <K> int validate_AVL_property(Node<K> n)
    {
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

    @Test
    public void insertSmallLeftLeaningAVL()
    {
        AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
        int[] a = new int[]{5, 8, 0, 2, 1,3,10,4};
        for (Integer key : a)
        {
            avl.insert(key);
            validate_AVL_propertys(avl);
        }

        List<Integer> list = avl.keys();
        List<Integer> listTruth = new ArrayList<>();
        listTruth.add(0);
        listTruth.add(1);
        listTruth.add(2);
        listTruth.add(3);
        listTruth.add(4);
        listTruth.add(5);
        listTruth.add(8);
        listTruth.add(10);
        // make sure to make a test that checks for duplicates
    }
    @Test
    public void testNodeGet()
    {
        Node<Integer> newNode = new Node<Integer>(9, null, null);
        assertEquals(newNode.get(), 9);
    }
    @Test
    public void testNodeIsLeaf()
    {
        Node<Integer> newNode = new Node<Integer>(9, null, null);
        Node<Integer> newNodeParent = new Node<Integer>(8, newNode, null);
        assertTrue(newNode.isLeaf());
        assertFalse(newNodeParent.isLeaf());
    }

    @Test
    public void testNodeUpdateHeightGetHeight()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        int[] a = new int[]{5, 8, 0, 2, 1,3,10,4};
        for (Integer key : a)
        {
            bst.insert(key);
            int h = bst.search(key).height;
            assertEquals(BinarySearchTree.get_height(bst.search(key)), h);
            assertFalse(bst.search(key).updateHeight());
            //validate_AVL_propertys(avl);
        }
    }
}

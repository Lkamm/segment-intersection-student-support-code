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
        for (Integer key : a)
        {
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
        int[] a = new int[]{4, 8, 0, 2, 6, 10, 11, 12, 4, 6, 8, 10, 4, 4};
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
    public void smallRemove()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        List<Integer> list = new ArrayList<>();
        int[] a = new int[]{4, 8, 0, 2, 6, 10, 11, 12};
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
            list.add(key);
        }
        for(Integer key: a)
        {
            bst.remove(key);
            list.remove(key); // remove all values
        }
        Collections.sort(list);
        assertEquals(list, bst.keys());

        for(Integer key: a) // testing if remove removes all values and if keys works properly
        {
            bst.insert(key);
            list.add(key);
            Collections.sort(list);
            assertEquals(list, bst.keys());
        }
        list.clear();
        bst.remove(10); // one child
        int[] b = new int[]{4, 8, 0, 2, 6, 11, 12};
        for(Integer key: b)
        {
            list.add(key);
        }
        Collections.sort(list);
        assertEquals(bst.keys(), list);
        list.clear();
        int[] c = new int[]{4, 8, 2, 6, 11, 12};
        for(Integer key: c)
        {
            list.add(key);
        }
        Collections.sort(list);
        bst.remove(0); // one child
        assertEquals(bst.keys(), list);
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
        testBigBSTInsert();
        testKeys();
        testClear();
        smallRemove();
        testDups();
        insertSmallLeftLeaningAVL();
        testBigAVL();
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
        if (n == null)
        {
            return -1;
        }
        else
        {
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
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int[] a = new int[]{5, 8, 0, 2, 1, 3, 10, 4};
        for (Integer key : a)
        {
            avl.insert(key);
            map.put(key, key);
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
       // for (int i = 0; i != 15; ++i) {
        //    assertEquals(avl.contains(i), map.containsKey(i));
       // }

        System.out.println(list.toString());
        System.out.println(listTruth.toString());
        // does not put them in the right order
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

    @Test
    public void testBigBSTInsert()
    {
        for (int i = 0; i < 100; i++)
        {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
            TreeMap<Integer, Integer> map = new TreeMap<>();
            List<Integer> list = new ArrayList<>();
            int randomSize = (int) (Math.random() * 500) + 100;
            int[] a = new int[randomSize];
            for (int j = 0; j < randomSize; j++)
            {
                a[j] = (int) (Math.random() * 500) + 10;
            }
            /*
             *       4
             *     /  \
             *    /    \
             *   0      8
             *    \    / \
             *     2  6   10
             */
            for (Integer key : a)
            {
                bst.insert(key);
                bst.insert(key); // check for duplicates
                map.put(key, key);
                if (!list.contains(key))
                {
                    list.add(key);
                }
            }
            for (int k = 0; k != 11; ++k)
            {
                assertEquals(bst.contains(i), map.containsKey(i));
            }
            Collections.sort(list);
            assertEquals(list, bst.keys());
        }
    }

    @Test
    public void testBigAVL()
    {
        for (int i = 0; i < 100; i++)
        {
            AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
            TreeMap<Integer, Integer> map = new TreeMap<>();
            List<Integer> list = new ArrayList<>();
            int randomSize = (int) (Math.random() * 5) + 5;
            int[] a = new int[randomSize];
            for (int j = 0; j < randomSize; j++)
            {
                a[j] = (int) (Math.random() * 500) + 10;
            }
            /*
             *       4
             *     /  \
             *    /    \
             *   0      8
             *    \    / \
             *     2  6   10
             */
            for (Integer key : a)
            {
                avl.insert(key);
                avl.insert(key);// check for duplicates
                map.put(key, key);
                validate_AVL_propertys(avl);
                if (!list.contains(key))
                {
                    list.add(key);
                }
            }
            for (int k = 0; k != 11; ++k)
            {
                assertEquals(avl.contains(i), map.containsKey(i));
            }
            Collections.sort(list);
            assertEquals(list, avl.keys());
            //System.out.println(list.toString());
            //System.out.println(avl.keys().toString());

            for(Integer key: a)
            {
                //System.out.println(key);
                avl.remove(key);
                list.remove(key);
                validate_AVL_propertys(avl);
                assertEquals(list, avl.keys());
            }
        }
    }

    }

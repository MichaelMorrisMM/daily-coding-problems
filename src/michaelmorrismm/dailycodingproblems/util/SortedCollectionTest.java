package michaelmorrismm.dailycodingproblems.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class SortedCollectionTest {

    private static SortedCollection<Integer> numCollection;
    private static Collection<Integer> collectionTenThroughOne;
    private static Collection<Integer> collectionOneThroughSix;
    private static Integer[] arrayOneThroughFive;

    @BeforeAll
    static void prepareCollections() {
        collectionTenThroughOne = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        collectionOneThroughSix = Arrays.asList(1, 2, 3, 4, 5, 6);
        arrayOneThroughFive = new Integer[]{1, 2, 3, 4, 5};
    }

    @BeforeEach
    void prepare() {
        numCollection = new SortedCollection<>();
    }

    @Test
    void size() {
        assertEquals(0, numCollection.size());
        numCollection.add(8);
        assertEquals(1, numCollection.size());
        numCollection.remove(10);
        assertEquals(1, numCollection.size());
        numCollection.remove(8);
        assertEquals(0, numCollection.size());
        for (int i = 1; i <= 10; i++) {
            numCollection.add(i);
        }
        assertEquals(10, numCollection.size());
    }

    @Test
    void isEmpty() {
        assertTrue(numCollection.isEmpty());
        numCollection.add(null);
        assertFalse(numCollection.isEmpty());
        numCollection.remove(null);
        assertTrue(numCollection.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(numCollection.contains(100));
        numCollection.add(100);
        assertTrue(numCollection.contains(100));
        numCollection.remove(100);
        assertFalse(numCollection.contains(100));
    }

    @Test
    void toArray() {
        for (int i = 5; i >= 1; i--) {
            numCollection.add(i);
        }
        Object[] expectedArray = new Object[]{1, 2, 3, 4, 5};
        assertArrayEquals(expectedArray, numCollection.toArray());
    }

    @Test
    void toArray2() {
        for (int i = 1; i <= 5; i++) {
            numCollection.add(i);
        }
        assertArrayEquals(arrayOneThroughFive, numCollection.toArray(new Integer[0]));

        Integer[] toIntegerArray = numCollection.toArray(new Integer[10]);
        assertNull(toIntegerArray[5]);
    }

    @Test
    void add() {
        assertTrue(numCollection.add(1000));
    }

    @Test
    void remove() {
        assertFalse(numCollection.remove(555));
        numCollection.add(555);
        assertTrue(numCollection.remove(555));
    }

    @Test
    void containsAll() {
        assertFalse(numCollection.containsAll(collectionOneThroughSix));
        for (int i = 1; i <= 5; i++) {
            numCollection.add(i);
        }
        assertFalse(numCollection.containsAll(collectionOneThroughSix));
        numCollection.add(6);
        assertTrue(numCollection.containsAll(collectionOneThroughSix));
    }

    @Test
    void addAll() {
        assertTrue(numCollection.addAll(collectionTenThroughOne));
        assertEquals(10, numCollection.size());
        assertTrue(numCollection.containsAll(collectionTenThroughOne));
    }

    @Test
    void removeAll() {
        assertFalse(numCollection.removeAll(collectionTenThroughOne));
        numCollection.addAll(collectionOneThroughSix);
        assertTrue(numCollection.removeAll(collectionTenThroughOne));
        assertTrue(numCollection.isEmpty());
    }

    @Test
    void retainAll() {
        numCollection.addAll(collectionTenThroughOne);
        assertTrue(numCollection.retainAll(collectionOneThroughSix));
        assertEquals(6, numCollection.size());
        assertTrue(numCollection.containsAll(collectionOneThroughSix));
    }

    @Test
    void clear() {
        numCollection.addAll(collectionTenThroughOne);
        assertFalse(numCollection.isEmpty());
        numCollection.clear();
        assertTrue(numCollection.isEmpty());
        for (Integer val : numCollection) {
            fail();
        }
    }

}

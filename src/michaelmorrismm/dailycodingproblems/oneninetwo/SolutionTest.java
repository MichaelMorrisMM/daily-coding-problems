package michaelmorrismm.dailycodingproblems.oneninetwo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void testArrayOfOne1() {
        int[] array = new int[] {0};
        assertTrue(Solution.canGetToEnd(array));
    }

    @Test
    void testArrayOfOne2() {
        int[] array = new int[] {5};
        assertTrue(Solution.canGetToEnd(array));
    }

    @Test
    void testProvided1() {
        int[] array = new int[] {1, 3, 1, 2, 0, 1};
        assertTrue(Solution.canGetToEnd(array));
    }

    @Test
    void testProvided2() {
        int[] array = new int[] {1, 2, 1, 0, 0};
        assertFalse(Solution.canGetToEnd(array));
    }

    @Test
    void testGeneral1() {
        int[] array = new int[] {1, 1, 1, 1, 1};
        assertTrue(Solution.canGetToEnd(array));
    }

    @Test
    void testGeneral2() {
        int[] array = new int[] {0, 0, 0, 0, 0};
        assertFalse(Solution.canGetToEnd(array));
    }

    @Test
    void testGeneral3() {
        int[] array = new int[] {5, 0, 0, 0, 0, 0};
        assertTrue(Solution.canGetToEnd(array));
    }

}

package michaelmorrismm.dailycodingproblems.oneninefour;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void testEmptyInput() {
        int[] p = new int[0];
        int[] q = new int[0];
        int value = Solution.determineIntersects(p, q);
        assertEquals(0, value);
    }

    @Test
    void testNoIntersects1() {
        int[] p = new int[] {-2, -1, 0, 1, 2};
        int[] q = new int[] {-2, -1, 0, 1, 2};
        int value = Solution.determineIntersects(p, q);
        assertEquals(0, value);
    }

    @Test
    void testNoIntersects2() {
        int[] p = new int[] {-2, -1, 0, 1, 2};
        int[] q = new int[] {-4, -3, -2, -1, 0};
        int value = Solution.determineIntersects(p, q);
        assertEquals(0, value);
    }

    @Test
    void testNoIntersects3() {
        int[] p = new int[] {-2, -1, 0, 1, 2};
        int[] q = new int[] {0, 1, 2, 3, 4};
        int value = Solution.determineIntersects(p, q);
        assertEquals(0, value);
    }

    @Test
    void testNoIntersects4() {
        int[] p = new int[] {-2, -1, 0, 1, 2};
        int[] q = new int[] {0, 0, 0, 0, 0};
        int value = Solution.determineIntersects(p, q);
        assertEquals(0, value);
    }

    @Test
    void testIntersects1() {
        int[] p = new int[] {-6, -5, -4, -1, 0, 1, 4, 5, 6};
        int[] q = new int[] {-4, -5, -6, 1, 0, -1, 6, 5, 4};
        int value = Solution.determineIntersects(p, q);
        assertEquals(9, value);
    }

    @Test
    void testManyIntersects1() {
        int n = 1000;
        int[] p = new int[n];
        int[] q = new int[n];
        int expected = 0;
        for (int i = 0; i < n; i++) {
            p[i] = i;
            q[i] = n - 1 - i;
            expected += n - 1 - i;
        }
        int value = Solution.determineIntersects(p, q);
        assertEquals(expected, value);
    }

    @Test
    void testManyIntersects2() {
        int n = 100000;
        int[] p = new int[n];
        int[] q = new int[n];
        int expected = 0;
        for (int i = 0; i < n; i++) {
            p[i] = i;
            q[i] = n - 1 - i;
            expected += n - 1 - i;
        }
        int value = Solution.determineIntersects(p, q);
        assertEquals(expected, value);
    }

    @Test
    void testManyIntersects3() {
        int n = 100000;
        int[] p = new int[n];
        int[] q = new int[n];
        int expected = 0;
        for (int i = 0; i < n; i++) {
            q[i] = i;
            p[i] = n - 1 - i;
            expected += n - 1 - i;
        }
        int value = Solution.determineIntersects(p, q);
        assertEquals(expected, value);
    }

}

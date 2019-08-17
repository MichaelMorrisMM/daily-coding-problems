package michaelmorrismm.dailycodingproblems.twozeroone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void testEmpty() {
        int[][] triangle = {};
        assertEquals(0, Solution.maxWeightPath(triangle));
    }

    @Test
    void testOneRow() {
        int[][] triangle = {{1}};
        assertEquals(1, Solution.maxWeightPath(triangle));
    }

    @Test
    void testProvided() {
        int[][] triangle = {{1}, {2, 3}, {1, 5, 1}};
        assertEquals(9, Solution.maxWeightPath(triangle));
    }

    @Test
    void testEdge() {
        int[][] triangle = {{1}, {1, 3}, {1, 1, 2}};
        assertEquals(6, Solution.maxWeightPath(triangle));
    }

    @Test
    void testNotGreedy() {
        int[][] triangle = {{1}, {1, 5}, {8, 1, 2}};
        assertEquals(10, Solution.maxWeightPath(triangle));
    }

}

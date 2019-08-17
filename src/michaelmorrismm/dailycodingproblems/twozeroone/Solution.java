package michaelmorrismm.dailycodingproblems.twozeroone;

/**
 * @author Michael Morris
 *
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Google.
 *
 * You are given an array of arrays of integers, where each array corresponds to a row in a triangle of numbers.
 * For example, [[1], [2, 3], [1, 5, 1]] represents the triangle:
 *
 *   1
 *  2 3
 * 1 5 1
 *
 * We define a path in the triangle to start at the top and go down one row at a time to an adjacent value,
 * eventually ending with an entry on the bottom row. For example, 1 -> 3 -> 5.
 * The weight of the path is the sum of the entries.
 *
 * Write a program that returns the weight of the maximum weight path.
 */
class Solution {

    static int maxWeightPath(int[][] triangle) {
        int[][] weightPaths = triangle.clone();
        for (int row = 1; row < weightPaths.length; row++) {
            int[] currentRow = weightPaths[row];
            int[] prevRow = weightPaths[row - 1];

            currentRow[0] += prevRow[0];
            for (int col = 1; col < currentRow.length - 1; col++) {
                currentRow[col] += Math.max(prevRow[col - 1], prevRow[col]);
            }
            currentRow[currentRow.length - 1] += prevRow[prevRow.length - 1];
        }

        int maxWeight = 0;
        if (weightPaths.length > 0) {
            for (int i = 0; i < weightPaths[weightPaths.length - 1].length; i++) {
                if (weightPaths[weightPaths.length - 1][i] > maxWeight) {
                    maxWeight = weightPaths[weightPaths.length - 1][i];
                }
            }
        }
        return maxWeight;
    }

}

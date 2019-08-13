package michaelmorrismm.dailycodingproblems.oneninefour;

/**
 * @author Michael Morris
 *
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Facebook.
 *
 * Suppose you are given two lists of n points, one list p1, p2, ..., pn on the line y = 0
 * and the other list q1, q2, ..., qn on the line y = 1. Imagine a set of n line segments
 * connecting each point pi to qi. Write an algorithm to determine how many pairs of the
 * line segments intersect.
 *
 */
class Solution {

    static int determineIntersects(int[] p, int[] q) {
        int intersects = 0;

        // Naive implementation
        Line[] lines = new Line[p.length];
        for (int i = 0; i < p.length; i++) {
            lines[i] = new Line(p[i], q[i]);
        }
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].intersectsWith(lines[j])) {
                    intersects++;
                }
            }
        }

        // Improved implementation
        // TODO

        return intersects;
    }

    static class Line {
        final int p;
        final int q;

        Line(int p, int q) {
            this.p = p;
            this.q = q;
        }

        boolean intersectsWith(Line other) {
            return (this.p < other.p && this.q > other.q) || (this.p > other.p && this.q < other.q);
        }

    }

}

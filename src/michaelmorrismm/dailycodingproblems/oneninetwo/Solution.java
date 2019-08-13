package michaelmorrismm.dailycodingproblems.oneninetwo;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Michael Morris
 *
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Google.
 *
 * You are given an array of nonnegative integers. Let's say you start at the beginning of the array
 * and are trying to advance to the end. You can advance at most, the number of steps that you're
 * currently on. Determine whether you can get to the end of the array.
 *
 * For example, given the array [1, 3, 1, 2, 0, 1], we can go from indices 0 -> 1 -> 3 -> 5, so return true.
 *
 * Given the array [1, 2, 1, 0, 0], we can't reach the end, so return false.
 *
 */
class Solution {

    static boolean canGetToEnd(int[] arr) {
        // Naive implementation
        PriorityQueueSet<Integer> queue = new PriorityQueueSet<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer index = queue.poll();
            if (index == arr.length - 1) {
                return true;
            }
            int stepsFromIndex = arr[index];
            for (int i = 1; i <= stepsFromIndex; i++) {
                if ((index + i) < arr.length) {
                    queue.add(index + i);
                }
            }
        }

        return false;
    }

    static class PriorityQueueSet<T> {
        private Set<T> set;
        private PriorityQueue<T> queue;

        PriorityQueueSet() {
            this.set = new HashSet<>();
            this.queue = new PriorityQueue<>();
        }

        boolean add(T item) {
            if (!this.set.contains(item)) {
                this.set.add(item);
                this.queue.add(item);
                return true;
            } else {
                return false;
            }
        }

        T poll() {
            T item = this.queue.poll();
            if (item != null) {
                this.set.remove(item);
            }
            return item;
        }

        boolean isEmpty() {
            return this.queue.isEmpty();
        }

    }

}

package michaelmorrismm.dailycodingproblems.twozerotwo;

import java.util.LinkedList;

/**
 * @author Michael Morris
 *
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Palantir.
 *
 * Write a program that checks whether an integer is a palindrome.
 * For example, 121 is a palindrome, as well as 888. 678 is not a palindrome.
 * Do not convert the integer into a string.
 */
class Solution {

    static boolean isPalindrome(final int number) {
        LinkedList<Integer> numberArray = new LinkedList<>();

        /*
        int decPlace = 1;
        while (true) {
            int modulusResult = number % (decPlace * 10);
            int integerDivResult = modulusResult / decPlace;
            if (integerDivResult == 0) {
                break;
            }
            numberArray.addFirst(integerDivResult);
            decPlace *= 10;
        }
         */

        int numberCopy = number;
        while (numberCopy > 0) {
            numberArray.addFirst(numberCopy % 10);
            numberCopy /= 10;
        }

        while (numberArray.size() > 1) {
            if (!numberArray.removeFirst().equals(numberArray.removeLast())) {
                return false;
            }
        }

        return true;
    }

}

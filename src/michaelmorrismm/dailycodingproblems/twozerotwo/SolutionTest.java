package michaelmorrismm.dailycodingproblems.twozerotwo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void testPalindrome1() {
        assertTrue(Solution.isPalindrome(5));
    }

    @Test
    void testPalindrome2() {
        assertTrue(Solution.isPalindrome(2442));
    }

    @Test
    void testPalindrome3() {
        assertTrue(Solution.isPalindrome(1234554321));
    }

    @Test
    void testProvided1() {
        assertTrue(Solution.isPalindrome(121));
    }

    @Test
    void testProvided2() {
        assertTrue(Solution.isPalindrome(888));
    }

    @Test
    void testProvided3() {
        assertFalse(Solution.isPalindrome(678));
    }

    @Test
    void testNotPalindrome1() {
        assertFalse(Solution.isPalindrome(1223));
    }

    @Test
    void testNotPalindrome2() {
        assertFalse(Solution.isPalindrome(7127));
    }

    @Test
    void testNotPalindrome3() {
        assertFalse(Solution.isPalindrome(123456789));
    }

}

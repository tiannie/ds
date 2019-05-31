import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("aA"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("good", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertFalse(palindrome.isPalindrome("aA", obo));
    }

    @Test
    public void testIsPalindromeOffByN() {
        OffByN ob5 = new OffByN(5);
        OffByN ob3 = new OffByN(3);
        assertTrue(palindrome.isPalindrome("dog", ob3));
        assertFalse(palindrome.isPalindrome("doc", ob3));
        assertTrue(palindrome.isPalindrome("af", ob5));
        assertFalse(palindrome.isPalindrome("ag", ob5));
        assertTrue(palindrome.isPalindrome("", ob5));
        assertTrue(palindrome.isPalindrome("f", ob5));
    }
}

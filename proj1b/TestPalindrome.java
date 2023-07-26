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
    } //Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testisPalindrome() {
        String a = "genshin";
        String b = "oho";
        String c = "impact";
        String d = "ohho";
        String e = "";
        String f = "g";
        assertFalse(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
        assertFalse(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(d));
        assertTrue(palindrome.isPalindrome(e));
        assertTrue(palindrome.isPalindrome(f));
    }

    @Test
    public void testisPalindromeExtra() {
        String a = "genshin";
        String b = "ohp";
        String c = "impact";
        String d = "oihn";
        String e = "";
        String f = "g";
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome(a, offByOne));
        assertTrue(palindrome.isPalindrome(b, offByOne));
        assertFalse(palindrome.isPalindrome(c, offByOne));
        assertTrue(palindrome.isPalindrome(d, offByOne));
        assertTrue(palindrome.isPalindrome(e, offByOne));
        assertTrue(palindrome.isPalindrome(f, offByOne));
    }
}

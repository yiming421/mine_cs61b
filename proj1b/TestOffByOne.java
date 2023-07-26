import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('B', 'a'));
        assertTrue(offByOne.equalChars('B', 'C'));
        assertTrue(offByOne.equalChars('C', 'B'));
        assertFalse(offByOne.equalChars('A', 'E'));
        assertFalse(offByOne.equalChars('@', '!'));
        assertTrue(offByOne.equalChars('(', ')'));
        assertTrue(offByOne.equalChars('Z', '['));
    }

    // Your tests go here.
}

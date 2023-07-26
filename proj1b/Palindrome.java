public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> q = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); ++i) {
            q.addLast(word.charAt(i));
        }
        return q;
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (first != last) {
            return false;
        } else {
            return this.isPalindrome(word);
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> q = this.wordToDeque(word);
        return this.isPalindrome(q);
    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        char first = word.removeFirst();
        char last = word.removeLast();
        if (cc.equalChars(first, last)) {
            return this.isPalindrome(word, cc);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> q = this.wordToDeque(word);
        return this.isPalindrome(q, cc);
    }
}

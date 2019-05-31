public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dc = new LinkedListDeque<>();
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            dc.addLast(word.charAt(i));
        }
        return dc;
    }

    private boolean dcContainsPalindrome(Deque<Character> dc) {
        if (dc.size() == 0 || dc.size() == 1) {
            return true;
        }

        char first = dc.removeFirst();
        char last = dc.removeLast();
        if (first != last) {
            return false;
        }
        return dcContainsPalindrome(dc);
    }
    private boolean dcContainsPalindromeWithCC(Deque<Character> dc, CharacterComparator cc) {
        if (dc.size() == 0 || dc.size() == 1) {
            return true;
        }

        char first = dc.removeFirst();
        char last = dc.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return dcContainsPalindromeWithCC(dc, cc);
    }
    public boolean isPalindrome(String word) {
        Deque<Character> dc = wordToDeque(word);

        return dcContainsPalindrome(dc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dc = wordToDeque(word);

        return dcContainsPalindromeWithCC(dc, cc);
    }
}

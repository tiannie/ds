public class OffByN implements CharacterComparator {
    private int offset;
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == offset || x - y == -offset;
    }

    public OffByN(int N) {
        offset = N;
    }
}

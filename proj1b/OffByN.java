public class OffByN implements CharacterComparator {
    private int n;

    OffByN(int _n) {
        n = _n;
    }

    @Override
    public boolean equalChars(char x, char y){
        int diff = x - y;
        return diff == n || diff == -n;
    }
}

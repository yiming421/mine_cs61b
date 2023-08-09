package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private int savedSeed = -1;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        int seed = parseString(input);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        NewWorld world = new NewWorld(finalWorldFrame, WIDTH, HEIGHT, seed);
        world.generateWorld();
        return finalWorldFrame;
    }

    private int parseString(String input) {
        int n = input.length();
        int seed = 0;
        boolean flag = false;
        for (int i = 0; i < n; ++i) {
            char now = input.charAt(i);
            if ((now == 'n' || now == 'N') && !flag) {
                flag = true;
            } else if (flag && now >= '0' && now <= '9') {
                seed = seed * 10 + (int) input.charAt(i);
            } else if (flag && (now == 's' || now == 'S')) {
                flag = false;
            } else if ((now == 'l' || now == 'L') && !flag) {
                seed = savedSeed;
                break;
            } else if ((now == 'q' || now == 'Q') && !flag) {
                if (input.charAt(i - 1) == ':') {
                    savedSeed = seed;
                }
            }
        }
        return seed;
    }
}

package byog.lab6;

import byog.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        String ret = "";
        for (int i = 0; i < n; ++i) {
            int num = RandomUtils.uniform(rand, 25);
            ret += CHARACTERS[num];
        }
        return ret;
    }

    public void drawFrame(String s) {
        StdDraw.clear();
        Font f = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(f);
        StdDraw.text(width / 2, height / 2, s);
        if (!gameOver) {
            Font smallFont = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(width / 2, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        int n = letters.length();
        for (int i = 0; i < n; ++i) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String ret = "";
        drawFrame(ret);
        while (ret.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            ret += StdDraw.nextKeyTyped();
            drawFrame(ret);
        }
        StdDraw.pause(500);
        return ret;
    }

    public void startGame() {
        round = 1;
        gameOver = false;
        playerTurn = false;
        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round:" + round);
            StdDraw.pause(500);
            String now = generateRandomString(round);
            flashSequence(now);
            playerTurn = true;
            String input = solicitNCharsInput(round);
            if (!now.equals(input)) {
                System.out.println();
                gameOver = true;
            } else {
                round++;
            }
        }
    }

}

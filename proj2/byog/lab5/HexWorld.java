package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static int s = 5;
    private static int WIDTH = 60;
    private static int HEIGHT = 60;
    private static Random rand = new Random();

    public static class Position {
        public int x;
        public int y;
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < s + 2 * i; ++j) {
                world[p.x - i + j][p.y + i] = t;
                world[p.x - i + j][p.y + 2 * s - i - 1] = t;
            }
        }
    }

    public static void drawWorld(TETile[][] world) {
        Position newp = new Position();
        for (int i = 0; i < 2; ++i) {
            newp.x = 5 * s - 3;
            newp.y = i * 8 * s;
            TETile t = randomTile();
            addHexagon(world, newp, s, t);
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 2; ++j) {
                newp.x = (3 * s - 2) + j * (4 * s - 2);
                newp.y = 2 * s * i + s;
                TETile t = randomTile();
                addHexagon(world, newp, s, t);
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                newp.x = (s - 1) + j * (4 * s - 2);
                newp.y = 2 * s * (i + 1);
                System.out.println(newp.x);
                System.out.println(newp.y);
                System.out.println();
                TETile t = randomTile();
                addHexagon(world, newp, s, t);
            }
        }
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (world[i][j] == null) {
                    world[i][j] = Tileset.NOTHING;
                }
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = rand.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.FLOOR;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        Random rand = new Random();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        drawWorld(world);
        ter.renderFrame(world);
    }
}

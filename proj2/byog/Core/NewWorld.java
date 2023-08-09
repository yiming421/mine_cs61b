package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class NewWorld {
    private TETile[][] world;
    private int WIDTH;
    private int HEIGHT;
    private int seed;
    boolean[][] connected;
    private final int[][] go = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private final int[][] wallGo = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private int areaFactor = 5;
    Random random;
    int recursionCnt = 0;

    public NewWorld(TETile[][] inputWorld, int width, int height, int randSeed) {
        world = inputWorld;
        WIDTH = width;
        HEIGHT = height;
        seed = randSeed;
        random = new Random(seed);
    }

    /* function for generating the world**/
    public void generateWorld() {
        int sumArea = 0;
        while (sumArea < WIDTH * HEIGHT / areaFactor) {
            sumArea += generateRoom();
        }
        generateHall();
        handleConnected();
        fillRestTile();
        generateWall();
    }

    /*function for generate the wall*/
    private void generateWall() {
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (connected[i][j]) {
                    for (int k = 0; k < 8; ++k) {
                        int newx = i + wallGo[k][0];
                        int newy = j + wallGo[k][1];
                        if (judgeInMap(newx, newy) && !connected[newx][newy]) {
                            world[newx][newy] = Tileset.WALL;
                        }
                    }
                }
            }
        }
    }

    /*remove those separate room*/
    private void handleConnected() {
        int area = 0;
        int cnt = 0;
        while (area < WIDTH * HEIGHT / 7) {
            int x = RandomUtils.uniform(random, WIDTH);
            int y = RandomUtils.uniform(random, HEIGHT);
            if (world[x][y] != null) {
                connected = new boolean[WIDTH][HEIGHT];
                connected[x][y] = true;
                recursionCnt = 1;
                dfs(x, y);
                area = recursionCnt;
                cnt++;
            }
            if (cnt > 5) {
                clearWorld();
                areaFactor--;
                generateWorld();
            }
        }
    }

    private void clearWorld() {
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                world[i][j] = null;
            }
        }
    }

    /*use dfs to find the connected part*/
    private void dfs(int x, int y) {
        for (int i = 0; i < 4; ++i) {
            int newx = x + go[i][0];
            int newy = y + go[i][1];
            if (judgeInMap(newx, newy) && world[newx][newy] != null
                    && !connected[newx][newy]) {
                connected[newx][newy] = true;
                recursionCnt++;
                dfs(newx, newy);
            }
        }
    }

    /*judge if a point is in the map*/
    private boolean judgeInMap(int x, int y) {
        return (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT);
    }

    /* judge if a room can be contained in the map**/
    private boolean judgeInMap(int x, int y, int width, int height) {
        return (x > 0 && x + width < WIDTH - 1 && y > 0 && y + height < HEIGHT - 1);
    }

    /* judge if the newly produced room overlap other rooms**/
    private boolean judgeNotOverlap(int x, int y, int width, int height) {
        for (int i = -1; i <= width; ++i) {
            for (int j = -1; j <= height; ++j) {
                if (world[x + i][y + j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /*actually draw the room**/
    private void drawRoom(int x, int y, int width, int height) {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                world[x + i][y + j] = Tileset.FLOOR;
            }
        }
    }

    private int generateRoom() {
        int x = RandomUtils.uniform(random, WIDTH);
        int y = RandomUtils.uniform(random, HEIGHT);
        int width = RandomUtils.uniform(random, 3) + 3;
        int height = RandomUtils.uniform(random, 3) + 3;
        int area = 0;
        if (judgeInMap(x, y, width, height) && judgeNotOverlap(x, y, width, height)) {
            drawRoom(x, y, width, height);
            area = width * height;
        }
        return area;
    }

    private void drawHall(int sx, int sy, int ex, int ey) {
        for (int i = Math.min(sx, ex); i <= Math.max(sx, ex); ++i) {
            world[i][sy] = Tileset.FLOOR;
        }
        for (int j = Math.min(sy, ey); j <= Math.max(sy, ey); ++j) {
            world[ex][j] = Tileset.FLOOR;
        }
    }

    private void generateHall() {
        int[][] record = new int[WIDTH * HEIGHT / 9][4];
        for (int i = 0; i < WIDTH * HEIGHT / 9; ++i) {
            record[i][0] = RandomUtils.uniform(random, WIDTH);
            record[i][1] = RandomUtils.uniform(random, HEIGHT);
            record[i][2] = RandomUtils.uniform(random, WIDTH);
            record[i][3] = RandomUtils.uniform(random, HEIGHT);
        }
        for (int i = 0; i < WIDTH * HEIGHT / 9; ++i) {
            if (world[record[i][0]][record[i][1]] != null && world[record[i][2]][record[i][3]] != null
                    && Math.abs(record[i][0] - record[i][2]) < WIDTH / 2
                    && Math.abs(record[i][1] - record[i][3]) < HEIGHT / 2) {
                drawHall(record[i][0], record[i][1], record[i][2], record[i][3]);
            }
        }
    }

    private void fillRestTile() {
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (!connected[i][j]) {
                    world[i][j] = Tileset.NOTHING;
                }
            }
        }
    }

}

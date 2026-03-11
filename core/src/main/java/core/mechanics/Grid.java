package core.mechanics;

import java.util.Random;

import com.badlogic.gdx.Input.TextInputListener;

public class Grid {
    private int cols;
    private int rows;
    private Tile[][] tiles;

    // start(x, y), end(x, y)
    private int startX, startY, endX, endY;

    public Grid() {
        this.cols = 0;
        this.rows = 0;
    }

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        tiles = new Tile[rows][cols];
    }

    public void setStartAndEnd(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    // Randomly initialize tiles <-- for testing purposes
    public void randomInitTile() {
        TileType[] types = TileType.values();
        Random random = new Random();
        for (int y=0; y<rows; y++) {
            for (int x=0; x<cols; x++) {
                Tile tile = new Tile(types[random.nextInt(types.length)]);
                for (int r=0; r< random.nextInt(4); r++) {tile.rotateClockwise();}
                tiles[y][x] = tile;
            }
        }
    }

    public boolean isPathComplete() {
        boolean[][] visited = new boolean[rows][cols];
        return checkPath(startX, startY, visited);
    }

    private boolean checkPath(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= cols || y < 0 || y >= rows || visited[y][x]) {
            return false;
        }
        if (x == endX && y == endY) {
            return true;
        }
        visited[y][x] = true;
        if (canConnect(x, y, x, y + 1, Tile.Direction.north)) {
            if (checkPath(x + 1, y, visited)) {
                return true;
            }
        }
        if (canConnect(x, y, x - 1, y, Tile.Direction.west)) {
            if (checkPath(x - 1, y, visited)) {
                return true;
            }
        }
        return false;
    }
    private boolean canConnect(int fromX, int fromY, int toX, int toY, Tile.Direction d) {
        if (toX < 0 || toX >= cols || toY < 0 || toY >= rows) {
            return false;
        }
        Tile fromTile = tiles[fromY][fromX];
        Tile toTile = tiles[toX][toY];
        if (fromTile == null || toTile == null) {
            return false;
        }
        return fromTile.hasConnection(d) && toTile.hasConnection(d.getOpposite());
    }
}

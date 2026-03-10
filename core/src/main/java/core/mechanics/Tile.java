package core.mechanics;

public class Tile {
    private TileType type;
    private int rotation; // 0, 90, 180, 270

    public Tile() {
        this.type = TileType.STRAIGHT;
        this.rotation = 0;
    }

    public Tile(TileType type) {
        this.type = type;
        this.rotation = 0;
    }

    public void rotateClockwise() {
        rotation = (rotation + 90) % 360;
    }

    public void rotateCounterClockwise() {
        rotation = (rotation - 90 + 360) % 360;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}

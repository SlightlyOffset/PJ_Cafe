package core.mechanics;

public class Tile {
    public TileType type;
    public int rotation; // 0, 90, 180, 270

    public Tile() {
        this.type = TileType.STRAIGHT;
        this.rotation = 0;
    }

    public enum Direction {
        // ลำดับที่ 0, 1, 2, 3
        north, east, south, west;
        public Direction getOpposite() {
            switch (this) {
                case north:
                    return south;
                case east:
                    return west;
                case south:
                    return north;
                case west:
                    return east;
                default:
                    return null;
            }
        }
    }

    public boolean hasConnection(Direction d) {
        int rotationStep = rotation / 90;
        int originalDirectionIndex = (4 + d.ordinal() - rotationStep) % 4;
        Direction originalDirection = d.values()[originalDirectionIndex];

        switch (type) {
            case STRAIGHT:
                return originalDirection == d.north || originalDirection == d.south;
            case L_TURN:
                return originalDirection == d.north || originalDirection == d.east;
            case T_JUNCTION:
                return originalDirection == d.west  || originalDirection == d.north || originalDirection == d.east;
            case CROSS:
                return true;
            default:
                return false;
        }
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

    public TileType getType() {
        return type;
    }
}

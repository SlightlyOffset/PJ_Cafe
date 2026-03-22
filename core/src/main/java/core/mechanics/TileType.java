package core.mechanics;

public enum TileType {
    STRAIGHT,
    L_TURN,
    T_JUNCTION,
    CROSS;

    public boolean[] getConnections(int rotation) {
        boolean[] ports = new boolean[4];
        switch (this) {
            case STRAIGHT:
                // North-South
                ports[0] = ports[2] = true;
                break;
            case L_TURN:
                // North-East
                ports[0] = ports[1] = true;
                break;
            case T_JUNCTION:
                // North-East-West
                ports[0] = ports[1] = ports[3] = true;
                break;
            case CROSS:
                // All directions
                ports[0] = ports[1] = ports[2] = ports[3] = true;
                break;
        }

        int steps = (rotation / 90) % 4;
        boolean[] rotatedPorts = new boolean[4];
        for (int i = 0; i < 4; i++) {
            rotatedPorts[(i + steps) % 4] = ports[i];
        }
        return rotatedPorts;
    }
}
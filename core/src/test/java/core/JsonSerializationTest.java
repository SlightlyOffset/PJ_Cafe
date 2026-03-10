package core;

import com.badlogic.gdx.utils.Json;
import core.mechanics.Grid;
import core.mechanics.Tile;
import core.mechanics.TileType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonSerializationTest {

    @Test
    public void testTileSerialization() {
        Json json = new Json();
        Tile tile = new Tile(TileType.STRAIGHT);
        tile.rotateClockwise(); // 90 degrees

        String jsonString = json.toJson(tile);
        Tile deserializedTile = json.fromJson(Tile.class, jsonString);

        assertEquals(tile.getType(), deserializedTile.getType());
        assertEquals(tile.getRotation(), deserializedTile.getRotation());
    }

    @Test
    public void testGridSerialization() {
        Json json = new Json();
        Grid grid = new Grid(2, 2);
        Tile[][] tiles = new Tile[2][2];
        tiles[0][0] = new Tile(TileType.STRAIGHT);
        tiles[0][1] = new Tile(TileType.L_TURN);
        tiles[1][0] = new Tile(TileType.T_JUNCTION);
        tiles[1][1] = new Tile(TileType.CROSS);
        grid.setTiles(tiles);

        String jsonString = json.toJson(grid);
        Grid deserializedGrid = json.fromJson(Grid.class, jsonString);

        assertEquals(grid.getCols(), deserializedGrid.getCols());
        assertEquals(grid.getRows(), deserializedGrid.getRows());
        assertNotNull(deserializedGrid.getTiles());
        assertEquals(grid.getTiles()[0][0].getType(), deserializedGrid.getTiles()[0][0].getType());
    }
}

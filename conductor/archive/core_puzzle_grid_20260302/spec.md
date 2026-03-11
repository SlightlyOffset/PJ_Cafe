---
tags:
  - "#conductor"
  - "#gemini"
  - "#AI"
---
# Track Specification - core_puzzle_grid_20260302

## Goal

Implement a 2D grid of rotatable path tiles that can be rendered and interacted with by the player.

## User Experience (UX)

- Players should see a grid of path segments (tiles).
- Clicking or touching a tile should rotate it 90 degrees clockwise.
- The visual feedback should clearly indicate the tile's current orientation.

## Technical Requirements

- **Tile System:**
  - Represent different path types (Straight, L-Turn, T-Junction, Cross).
  - Maintain state for rotation (0, 90, 180, 270 degrees).
  - Provide a way to check connection points based on the current rotation.
- **Grid System:**
  - Manage a 2D collection of `Tile` objects.
  - Coordinate rendering of all tiles within the grid.
  - Handle coordinate mapping (screen to grid) for input.
- **Rendering:**
  - Use `SpriteBatch` for efficient rendering.
  - Support different textures or placeholder graphics for tile types.
- **Input Handling:**
  - Detect clicks/touches on the screen.
  - Map click coordinates to a specific tile in the grid.
  - Trigger rotation on the selected tile.

## Constraints

- Adhere to the Java 25 toolchain and LibGDX framework.
- Maintain a clean OOP structure in the `core` module.
- Aim for >80% test coverage for logic-heavy classes (`Tile`, `Grid`).

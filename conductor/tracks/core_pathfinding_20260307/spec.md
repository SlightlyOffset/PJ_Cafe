---
tags:
  - "#conductor"
  - "#gemini"
  - "#AI"
---
# Specification - Core Pathfinding & Validation

## Overview
Implement the logic for searching a continuous path from a start tile to a finish tile in the `Grid`. This is the core win condition for P'J Cafe.

## Functional Requirements
1.  **Tile Port Logic**: Each `Tile` must expose its current connection ports (N, E, S, W) based on its `TileType` and current `rotation`.
2.  **Connection Logic**: Define how two adjacent tiles connect (e.g., Tile A's East port matches Tile B's West port).
3.  **Path Search**: Implement a traversal algorithm (BFS or DFS) to find if a valid path exists from a start point (e.g., Grid edge) to an end point.
4.  **Start/Finish Definitions**: Define specific coordinates or edge entry points as the puzzle's start and end.

## Technical Constraints
- Use Java 25.
- The algorithm should be efficient enough for real-time validation upon each rotation.
- Must be unit-tested thoroughly without a GUI dependency.

## Acceptance Criteria
- `Grid.isPathComplete(int startX, int startY, int endX, int endY)` returns true if a valid path exists.
- The implementation handles all `TileType` rotations correctly.
- Disconnected tiles or mismatched ports correctly break the path.

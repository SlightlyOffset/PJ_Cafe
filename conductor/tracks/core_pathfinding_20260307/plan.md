---
tags:
  - "#conductor"
  - "#gemini"
  - "#AI"
---
# Implementation Plan - Core Pathfinding

## Phase 1: Tile Port Logic
- [ ] Task: Refactor `Tile` to expose connectivity.
    - [ ] Add `boolean[] getPorts()` to `Tile.java` that returns `[N, E, S, W]` based on type and rotation.
    - [ ] Write unit tests in `TileTest.java` for all types and rotations.
- [ ] Task: Implement Port Matching Utility.
    - [ ] Create a static utility or `Grid` method to check if two adjacent tiles "handshake" their connection.

## Phase 2: Path Traversal Algorithm
- [ ] Task: Implement BFS/DFS in `Grid`.
    - [ ] Add `isPathComplete(int startX, int startY, int endX, int endY)` to `Grid.java`.
    - [ ] Handle visited tiles to prevent infinite loops in cycles.
- [ ] Task: Unit Testing Pathfinding.
    - [ ] Test straight connections.
    - [ ] Test L-turns and T-junctions.
    - [ ] Test blocked paths and loops.

## Phase 3: Visual Feedback (Integration)
- [ ] Task: Update `GameScreen` to highlight connected paths.
    - [ ] (Optional for this track but recommended) Change color of path lines when they form a valid circuit.

## Phase 4: Verification & Doc Sync
- [ ] Task: Final Manual Verification.
- [ ] Task: Synchronize `product.md` and `tracks.md`.

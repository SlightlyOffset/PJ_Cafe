# Tech Stack - Java 2D Horror Sidescroller

## Language & Runtime
- **Java (JDK 17+):** Chosen for its robust Object-Oriented features and cross-platform window management capabilities.

## Core Libraries & Frameworks
- **Java AWT/Swing:** Primary tool for OS-level window manipulation ("window-hopping"), handling resizing, positioning, and secondary popup windows.
- **LibGDX:** Utilized for core game engine features, including:
    - **Rendering:** SpriteBatch and Texture management for pixel art.
    - **Input Handling:** Consolidated input processing.
    - **Asset Management:** Efficient loading of textures, maps, and sounds.
    - **Math/Physics:** Vector math and basic collision utilities.

## Build & Dependency Management
- **Gradle:** To manage project dependencies (LibGDX, etc.) and provide a flexible build environment for multi-window coordination.

## Development Tools
- **IDE:** Recommended IntelliJ IDEA or Eclipse for strong Java/Gradle support.
- **Version Control:** Git (already initialized).

## Architecture Patterns (Targeted)
- **State Pattern:** For managing player states (Idle, Run, Jump, Dead) and game states (Main Window, Window-Hopping Mode).
- **Observer Pattern:** To synchronize events between the main game window and the external desktop popups.
- **Factory Pattern:** For dynamic creation of different types of popup windows or game entities.

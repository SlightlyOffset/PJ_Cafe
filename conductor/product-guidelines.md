# Product Guidelines - Java 2D Horror Sidescroller

## Narrative & Tone
- **Direct & Psychological:** The game should frequently address the player directly, breaking the fourth wall to create a sense of being "watched" or "targeted" by the game itself.
- **Unsettling Dialogue:** Text boxes and popups should use unsettling, direct language to challenge the player's sense of security.

## Visual Identity & Aesthetics
- **Glitch & Distortion:** Use chromatic aberration, static, and "sprite-tearing" effects to represent mental instability and the breakdown of the game's reality.
- **Moody Pixel Art:** Maintain a gritty, retro aesthetic with a focus on dark environments where glitch effects provide the primary source of light or visual interest.
- **Visual Stressors:** Implement a vignette effect that tightens during high-tension moments and screen shakes to emphasize impact or psychological distress.

## Meta-Gimmick Implementation (Window-Hopping)
- **Narrative Triggers:** Window movements and resizing should coincide with key story beats or the appearance of significant narrative entities.
- **Interaction Feedback:** The environment (including the window itself) should react to player actions—shifting or "shying away" when the player succeeds, or becoming more erratic when the player makes mistakes.
- **Synchronized Popups:** Desktop popups (like the "Eye") should be timed with in-game events to create a seamless experience between the game window and the OS.

## Development Principles
- **Robust State Management:** Ensure the core platforming remains stable and responsive even while the window is being manipulated.
- **Player Safety:** While the game "messes" with the desktop, it must never interfere with actual system stability or critical OS functions.

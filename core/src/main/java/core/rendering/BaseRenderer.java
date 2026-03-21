package core.rendering;

/**
 * Custom Abstract Class for pluggable rendering backends.
 * Satisfies OOP Requirement 1.2.
 * Provides a common foundation for AwtRenderer and GdxRenderer.
 */
public abstract class BaseRenderer implements IRenderer {
    
    @Override
    public void endFrame() {
        // Default no-op implementation. 
        // Subclasses can override if they need specific end-of-frame cleanup.
    }
}

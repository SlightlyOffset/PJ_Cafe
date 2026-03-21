package core.rendering;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * AWT-based implementation of the IRenderer interface.
 * Fulfills the project requirement of using standard Java Graphics without frameworks.
 */
public class AwtRenderer extends BaseRenderer {
    private Graphics2D g2d;
    private int screenWidth;
    private int screenHeight;

    public void setGraphics(Graphics2D g2d, int screenWidth, int screenHeight) {
        this.g2d = g2d;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void clearScreen(float r, float g, float b, float a) {
        if (g2d == null) return;
        g2d.setColor(new Color(r, g, b, a));
        g2d.fillRect(0, 0, screenWidth, screenHeight);
    }

    @Override
    public void drawRect(float x, float y, float width, float height, String colorHex) {
        if (g2d == null) return;
        g2d.setColor(Color.decode(colorHex));
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void drawPathLine(float cx, float cy, float halfWidth, float length, int direction, String colorHex) {
        if (g2d == null) return;
        g2d.setColor(Color.decode(colorHex));
        
        // 0=N, 1=E, 2=S, 3=W
        switch (direction) {
            case 0: // North
                g2d.fillRect((int) (cx - halfWidth), (int) (cy - length), (int) (halfWidth * 2), (int) length);
                break;
            case 1: // East
                g2d.fillRect((int) cx, (int) (cy - halfWidth), (int) length, (int) (halfWidth * 2));
                break;
            case 2: // South
                g2d.fillRect((int) (cx - halfWidth), (int) cy, (int) (halfWidth * 2), (int) length);
                break;
            case 3: // West
                g2d.fillRect((int) (cx - length), (int) (cy - halfWidth), (int) length, (int) (halfWidth * 2));
                break;
        }
    }
}

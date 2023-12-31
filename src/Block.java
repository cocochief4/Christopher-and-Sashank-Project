import processing.core.PApplet;
import processing.core.PImage;

public class Block {

    // Width, Height
    public static float[] SIZE = {32, 32};

    public static enum Type {GRASS, HONEY};

    private PImage sprite;
    private float x, y;
    private Type type;

    public Block (PImage sprite, float x, float y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;

        type = Type.GRASS;
    }

    public Block (PImage sprite, float x, float y, Type type) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;

        this.type = type;
    }

    public void render(PApplet app) {
        image(app);
    }

    public boolean blockCollision(Player p) {
        // Label bounds
        float bottom = y + SIZE[1]/2;
        float top = y - SIZE[1]/2;
        float right = x + SIZE[0]/2;
        float left = x - SIZE[0]/2;

        // Player bounds
        float pBottom = p.getY() + p.getSize()[1]/2;
        float pTop = p.getY() - p.getSize()[1]/2;
        float pRight = p.getX() + p.getSize()[0]/2;
        float pLeft = p.getX() - p.getSize()[0]/2;

        if (pBottom > top && pBottom < bottom && (p.getX() > left && p.getX() < right)) { // Top boundary
            p.setY(p.getY() - (pBottom - top));
            p.setYSpeed(0);
            if (type == Type.HONEY && pBottom + 2 > top && pBottom < bottom && (p.getX() > left && p.getX() < right)) {
                return true;
            }
        } else if (pTop < bottom && pTop > top && (p.getX() > left && p.getX() < right)) { // Bottom boundary
            p.setY(p.getY() + (bottom - pTop));
            p.setYSpeed(0);
        } else if (pRight > left && pRight < right && (p.getY() > top && p.getY() < bottom)) { // Coming in from left to right
            p.setX(p.getX() - (pRight - left));
        } else if (pLeft < right && pLeft > left && (p.getY() > top && p.getY() < bottom)) { // Coming in from left to right
            p.setX(p.getX() + (right - pLeft));
        }
        return false;
    }

    private void image(PApplet app) {
        app.image(sprite, x, y, SIZE[0], SIZE[1]);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

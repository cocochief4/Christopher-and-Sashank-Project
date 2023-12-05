import processing.core.PApplet;
import processing.core.PImage;

public class Block {

    // Width, Height
    private static final float[] SIZE = {32, 32};

    private PImage sprite;
    private float x, y;

    public Block (PImage sprite, float x, float y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(PApplet app) {
        image(app);
    }

    public void blockCollision(Player p) {
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

        // Check y bounds
        // y is between
        if (!(pTop > bottom || pBottom < top)) {
            if (pRight > left && pRight < right) { // Player coming in from the left of the block
                p.setX(p.getX() - (pRight - left));
            } else if (pLeft > left && pLeft < right) { // Player coming in from the right of the block
                p.setX(p.getX() + (right-pLeft));
            }
        }

        // x is between
        if (!(pRight < left || pLeft > right)) {
            if (pBottom < bottom && pBottom > top) { // Player coming in from the top of the block
                p.setY(p.getY() - (pBottom - top));
            } else if (pTop < bottom && pTop > top) { // Player coming in from the bottom of the block
                p.setY(p.getY() + (bottom-pTop));
            }
        }
    }

    private void image(PApplet app) {
        app.image(sprite, x, y, SIZE[0], SIZE[1]);
    }

}

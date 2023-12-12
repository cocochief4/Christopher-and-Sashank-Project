import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    
    /*
     * Notes:
     * 
     * (0, 0) of the entire game board is the top left
     * 
     * image() is the method that displays the sprite. Params are:
     * PImage image, x, y, width-scale, height-scale
     * 
     * image, x, y are not optional; width-scale, height-scale is optional. Scaling is the pixel amount to scale to, not a ratio.
     * x, y are of the center, with x+ going right and y+ going down.
     * 
     * sprites are 16 x 16 pixels.
     */

    private static final int JUMP_VAL = 8;
    private static final float GRAVITY_VAL = 0.5f;
    public static final float DEFAULT_SPEED = 5;

    // Width, Height
    public static float[] SIZE = {32, 32};

    private float speed;

    private int pNum;
    private char up;
    private char left;
    private char right;
    private float x,y;
    private float ySpeed;
    private float xSpeed;

    private boolean isGhost;
    private int ghostTick;
    private Block honeyBlock;

    private PImage currentImage;

    public Player (int playerNum, char up, char left, char right) {
        this.pNum = playerNum;
        this.up = up;
        this.left = left;
        this.right = right;

        if (playerNum == 1) {
            x = Joust.DEFAULT_WIDTH/4;
            y = (int) (Joust.DEFAULT_HEIGHT/2);
        } else if (playerNum == 2) {
            x = Joust.DEFAULT_WIDTH * 3/4 - SIZE[0];
            y = (int) (Joust.DEFAULT_HEIGHT/2);
        }

        isGhost = false;
        ghostTick = 0;
        honeyBlock = null;

        speed = DEFAULT_SPEED;


    }

    /**
     * Main method, call to move Player
     */
    public void move(PApplet app, ArrayList<Block> blockArr) {

        PImage[] localImages;

        if (pNum == 1) localImages = Joust.p1Image;
        else localImages = Joust.p2Image;

        // Deal with y-value
        ySpeed += GRAVITY_VAL;
        y += ySpeed;
        if (Joust.keys.get(up)) {
            // Two options - reset, or addition. Addition works better
            // Reset use smaller values (0 - 3)
            // Addition can use bigger (5 - 20)
            ySpeed = -1 * JUMP_VAL;
            y -= 1;
            Joust.keys.put(up, false);

            Joust.flap.play();
        }

        // Check boundaries
        // Bottom
        if (y >= Joust.DEFAULT_HEIGHT - SIZE[1]/2 + 1) {
            // ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT - SIZE[1]/2; // Must be one less or else the condition keeps tripping
        }

        // Top
        if (y <= -1) {
        //     ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT;
        }

        // Side
        if (x <= -1) {
            x = Joust.DEFAULT_WIDTH-1;
        }
        if (x >= Joust.DEFAULT_WIDTH) {
            x = 0;
        }

        // Check block collisions
        if (!isGhost) {
            if (honeyBlock != null) {
                if (!honeyBlock.blockCollision(this)) {
                    honeyBlock = null;
                }
            }
            for (Block block : blockArr) {
                if (block.blockCollision(this) && honeyBlock == null) {
                    honeyBlock = block;
                }
            }
        } else {
            app.tint(255, 150);
            ghostTick++;
            if (ghostTick > 180) {
                ghostTick = 0;
                isGhost = false;
            }
        }

        // Check powerup collisions
        for (int i = 0; i < Powerup.powerupArr.size(); i++) {
            if (Powerup.powerupArr.get(i).hasCollided(this)) {
                Powerup.powerupArr.remove(i);
                isGhost = true;
                ghostTick = 0;
                Joust.invis.play();
            }
        }

        // Deal with x-value
        if (honeyBlock != null) {
            speed = DEFAULT_SPEED/4;
        }
        else {
            speed = DEFAULT_SPEED;
        }
        if (Joust.keys.get(left)) {
            x -= speed;
            currentImage = localImages[1];
        } else if (Joust.keys.get(right)) {
            x += speed;
            currentImage = localImages[0];
        }

        // Display
        image(currentImage, app);
        app.tint(255, 255);
    }

    public void reset(PApplet app) {
        y = (int) (Joust.DEFAULT_HEIGHT/2);
        ghostTick = 0;
        isGhost = false;
        honeyBlock = null;
        ySpeed = 0;
        if (pNum == 1) {
            x = Joust.DEFAULT_WIDTH/6;
            currentImage = Joust.p1Image[0];
            image(Joust.p1Image[0], app);
        } else if (pNum == 2) {
            x = Joust.DEFAULT_WIDTH*5/6;
            currentImage = Joust.p2Image[1];
            image(Joust.p2Image[1], app);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setYSpeed(float speed) {
        this.ySpeed = speed;
    }
    public void setXSpeed(float newSpeed) {
        this.speed = newSpeed;
    }

    public float[] getSize() {
        return SIZE;
    }

    /**
     * newSize is in {WIDTH, HEIGHT}
     * 
     * @param image
     * @param newSize
     */
    private void image (PImage image, int[] newSize, PApplet app) {
        float wScale = newSize[0] / SIZE[0];
        float hScale = newSize[1] / SIZE[1];

        app.image(image, x, y, wScale, hScale);        
    }

    private void image (PImage image, float scale, PApplet app) {
        app.image(image, x, y, scale*SIZE[0], scale*SIZE[1]);
    }

    private void image(PImage image, PApplet app) {
        // app.image(image, x, y, SIZE[0]/16, SIZE[1]/16);
        app.image(image, x, y, SIZE[0], SIZE[1]);
    }

    // Note: image scaling is based on number of pixels, not a scale of original image.


}
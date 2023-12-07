import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Powerup {

    // Going to be circular.
    private static final int SIZE = 32;

    public static ArrayList<Powerup> powerupArr = new ArrayList<Powerup>();

    public static enum Type {GHOST};
    
    float x, y;

    Type powerupType;

    PImage sprite;

    public Powerup(ArrayList<Block> blockArr) { // Generate a random powerup on a random block.
        // Write code for random powerup if more are added
        sprite = Joust.ghost;
        powerupType = Type.GHOST;

        int randomBlockInt = (int) (Math.random() * blockArr.size());

        float blockX = blockArr.get(randomBlockInt).getX();
        float blockY = blockArr.get(randomBlockInt).getY() - Block.SIZE[1]/2;

        x = blockX;
        y = blockY - SIZE/2;

        powerupArr.add(this);

    }

    public void render(PApplet app) {
        
        app.image(sprite, x, y, SIZE, SIZE);

    }

    public boolean hasCollided(Player p) {
        if (Math.sqrt((x-p.getX())*(x-p.getX()) + (y-p.getY())*(y-p.getY())) < (Player.SIZE[0]/2 + SIZE/2)) { // Assume player is a square
            return true;
        }

        return false;
    }

}

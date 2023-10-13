package Brawler;

import java.util.ArrayList;

import javafx.stage.DirectoryChooser;

public class Brawler {
    final static double SIZE = 0; // Radius of the brawler

    final static int MAX_MAGAZINE = 3;

    int x, y; // Position of the brawler

    int damage; // How much damage each bullet does

    int health;

    int speed;

    int reloadSpeed; // Measured in ticks

    int magazine;

    double direction; // Where the brawler is aimings

    void updateHealth(int damage) {
        health -= damage;
    }

    boolean isTouchingBullet(Bullet bullet) {
        return true;
    }

    void takeDamage(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (isTouchingBullet(bullet)) {
                updateHealth(bullet.damage);
            }
        }
    }

    void shoot(ArrayList<Bullet> bulletList) {
        if (hasAmmo()) {
            bulletList.add(new Bullet());
            magazine--;
        }
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean hasAmmo() {
        return magazine > 0;
    }

    void main(long tick) {
        
    }

}

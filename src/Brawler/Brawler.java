package Brawler;

import java.util.ArrayList;

import processing.core.PApplet;

public class Brawler {
    final static float SIZE = 100; // Diameter of the brawler

    final static int MAX_MAGAZINE = 3;

    final static double TURN_SPEED = 1;

    float x, y; // Position of the brawler

    int damage; // How much damage each bullet does

    int health;

    int speed;

    int reloadSpeed; // Measured in ticks

    int magazine;

    double direction; // Where the brawler is aimings

    public Brawler(int health, int damage, int speed, int reloadSpeed) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.reloadSpeed = reloadSpeed;
    }

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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean hasAmmo() {
        return magazine > 0;
    }



    void main(PApplet window, long tick, ArrayList<Bullet> bulletList) {

        if (isKeyPressed("w")) {
            y = y+ speed;
        }
        if (isKeyPressed("a")) {
            x = x - speed;
        }
        if (isKeyPressed("s")) {
            y = y - speed;
        }
        if (isKeyPressed("d")) {
            x = x + speed;
        }

        if (isKeyPressed("k")) {
            direction = direction + TURN_SPEED;
        }

        if (isKeyPressed("l")) {
            direction = direction - TURN_SPEED;
        }


//        if (isKeyPressed("TAB") && tick%reloadSpeed == 0) {
//            shoot(bulletList);
//        }



        draw(window);
    }

    public void draw(PApplet window) {
        window.fill(window.color(255, 0, 0));
        window.ellipse(x, y, SIZE, SIZE);
    }

}

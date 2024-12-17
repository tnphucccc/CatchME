package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public Player() {
        this.name = "player";
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width = 32;

        getPlayerImage();
        state = 1;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void getPlayerImage() {
        try {
            getSprite("/Sprite/Black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }
}
package entities;

import variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Boss extends Entity {
    public boolean collision;

    public Boss() {
        this.name = "boss";
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width = 32;

        setDefault();
        getBossImage();
    }

    public void setDefault() {
        x = Constant.TILE_SIZE * 8;
        y = Constant.TILE_SIZE * 7;
        collision = true;
        speed = 2;
    }

    public void getBossImage() {
        try {
            getSprite("/Sprite/White.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {

    }


    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, this.x, this.y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
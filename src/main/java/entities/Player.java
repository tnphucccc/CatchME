package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import config.Game;
import core.Collision;
import core.KeyHandler;
import variables.Constant;
import config.Window;

public class Player extends Entity {

    public Collision collisionCheck = new Collision();
    KeyHandler keyH = Window.getKeyH();

    public Player() {
        this.name = "player";
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.height = 32;
        solidArea.width = 32;

        setDefault();
        getPlayerImage();
        state = 1;
    }

    public void setDefault() {
        x = Constant.TILE_SIZE * 2;
        y = Constant.TILE_SIZE * 2;
        speed = 2;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void getPlayerImage() {
        try {
            getSprite("/Models/Black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        collisionOn = false;

        //Check collision
        collisionCheck.checkTile(this);

        //Check object collision
        int interactObject = collisionCheck.checkObject(this, true);
        teleport(interactObject);

        if ((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && state == 1) {
            if (keyH.upPressed) { //Character Movement
                direction = 0;
            } else if (keyH.downPressed) {
                direction = 2;
            } else if (keyH.leftPressed) {
                direction = 3;
            } else {
                direction = 1;
            }

            //Animation
            if (!collisionOn) {
                switch (direction) {
                    case 0 -> y -= speed;
                    case 2 -> y += speed;
                    case 3 -> x -= speed;
                    case 1 -> x += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum != 4) {
                    spriteNum++;
                } else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void teleport(int index) {

        if (index != -1 && !Game.PortInList[index].cd) {
            //choose a random port
            int portIndex = (int) Math.floor(Math.random() * 10);
            while (Game.PortList[portIndex] == null) {
                portIndex = (int) Math.floor(Math.random() * 10);
            }
            //teleport
            this.x = Game.PortList[portIndex].objectX;
            this.y = Game.PortList[portIndex].objectY;
            Game.PortInList[index].setCD(true);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, getX(), getY(), Constant.TILE_SIZE,Constant.TILE_SIZE, null);
    }
}
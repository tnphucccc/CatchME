package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import config.Game;
import core.KeyHandler;
import variables.Constant;

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
            getSprite("/Sprite/Black.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

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
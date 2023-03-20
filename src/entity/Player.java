package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;
    public final int standardScreenX;
    public final int standardScreenY;
    public final int standardVelocity = 4;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;


        standardScreenX = gp.screenWidth/2 - (gp.tileSize/2);
        standardScreenY = gp.screenHeight/2 - (gp.tileSize/2);
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        hitBox = new Rectangle(8, 8, 32, 32);
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){
        x = gp.worldWidth/2;
        y = gp.worldHeight/2 - gp.tileSize;
        velocity = standardVelocity;
        direction = "down";
    }

    public void update(){
        if (keyH.downPressed || keyH.leftPressed || keyH.upPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";

            }
            if (keyH.downPressed) {
                direction = "down";

            }
            if (keyH.leftPressed) {
                direction = "left";

            }
            if (keyH.rightPressed) {
                direction = "right";

            }
            collision = false;
            velocity = standardVelocity;
            slow = false;
            gp.cCheck.checkCollision(this);

            if (!collision){
                switch (direction){
                    case "up":
                        if (y - gp.screenHeight/2 + gp.tileSize/2 < 0) {
                            screenY -= velocity;
                        }
                        else if (y + gp.screenHeight/2 + gp.tileSize > gp.worldHeight){
                            screenY -= velocity;
                        }
                        else {screenY = standardScreenY;}
                        y -= velocity;
                        break;
                    case "down":
                        if (y - gp.screenHeight/2 + gp.tileSize/2  < 0){
                            screenY += velocity;
                        }
                        else if (y + gp.screenHeight/2 + gp.tileSize > gp.worldHeight){
                            screenY += velocity;
                        }
                        else {screenY = standardScreenY;}

                        y += velocity;
                        break;
                    case "left":
                        if (x - gp.screenWidth/2 + gp.tileSize/3 < 0)
                        {
                            screenX -= velocity;
                        }
                        else if (x + gp.screenWidth/2 + gp.tileSize > gp.worldWidth)
                        {
                            screenX -= velocity;
                        }
                        else {screenX = standardScreenX;}

                        x -= velocity;
                        break;
                    case "right":
                        if (x - gp.screenWidth/2 + gp.tileSize/3 < 0)
                        {
                            screenX += velocity;
                        }
                        else if (x + gp.screenWidth/2 + gp.tileSize > gp.worldWidth)
                        {
                            screenX += velocity;
                        }
                        else {screenX = standardScreenX;}

                        x += velocity;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

   public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/right2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/down2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
   }

   public void draw(Graphics2D g2){
       BufferedImage image = null;

       switch (direction){
           case "up":
               if (spriteNum == 1){image = up1;}
               if (spriteNum == 2){image = up2;}
               break;
           case "down":
               if (spriteNum == 1){image = down1;}
               if (spriteNum == 2){image = down2;}
               break;
           case "left":
               if (spriteNum == 1){image = left1;}
               if (spriteNum == 2){image = left2;}
               break;
           case "right":
               if (spriteNum == 1){image = right1;}
               if (spriteNum == 2){image = right2;}
               break;
       }
       g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
   }
}

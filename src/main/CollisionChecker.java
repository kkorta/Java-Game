package main;

import entity.Entity;

public class CollisionChecker {


    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkCollision(Entity entity){
        int entityLeftX = entity.x + entity.hitBox.x;
        int entityRightX = entity.x + entity.hitBox.x + entity.hitBox.width;
        int entityUpY = entity.y + entity.hitBox.y;
        int entityDownY = entity.y + entity.hitBox.y + entity.hitBox.height;

        int entityLeft =  entityLeftX / gp.tileSize;
        int entityRight = entityRightX / gp.tileSize;
        int entityUp = entityUpY / gp.tileSize;
        int entityDown = entityDownY / gp.tileSize;

        int tile1, tile2;

        switch (entity.direction){
            case "up":
                entityUp = (entityUpY - entity.velocity) / gp.tileSize;
                tile1 = gp.tileM.mapTile[entityLeft][entityUp];
                tile2 = gp.tileM.mapTile[entityRight][entityUp];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision)
                {
                    entity.collision = true;
                }
                if (gp.tileM.tile[tile1].slow || gp.tileM.tile[tile2].slow)
                {
                    entity.slow = true;
                    entity.velocity -=2;
                }

                break;
            case "down":
                entityDown = (entityDownY + entity.velocity) / gp.tileSize;
                tile1 = gp.tileM.mapTile[entityLeft][entityDown];
                tile2 = gp.tileM.mapTile[entityRight][entityDown];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision)
                {
                    entity.collision = true;
                }
                if (gp.tileM.tile[tile1].slow || gp.tileM.tile[tile2].slow)
                {
                    entity.slow = true;
                    entity.velocity -= 2;
                }
                break;
            case "left":
                entityLeft = (entityLeftX - entity.velocity) / gp.tileSize;
                tile1 = gp.tileM.mapTile[entityLeft][entityUp];
                tile2 = gp.tileM.mapTile[entityLeft][entityDown];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision)
                {
                    entity.collision = true;
                }
                if (gp.tileM.tile[tile1].slow || gp.tileM.tile[tile2].slow)
                {
                    entity.slow = true;
                    entity.velocity -= 2;
                }
                break;
            case "right":
                entityRight = (entityRightX + entity.velocity) / gp.tileSize;
                tile1 = gp.tileM.mapTile[entityRight][entityUp];
                tile2 = gp.tileM.mapTile[entityRight][entityDown];
                if (gp.tileM.tile[tile1].collision || gp.tileM.tile[tile2].collision)
                {
                    entity.collision = true;
                }
                if (gp.tileM.tile[tile1].slow || gp.tileM.tile[tile2].slow)
                {
                    entity.slow = true;
                    entity.velocity-= 2;
                }
                break;
        }



    }
}

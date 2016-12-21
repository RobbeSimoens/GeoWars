package com.spaceraider.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.spaceraider.entities.SpaceObject;
import com.spaceraider.entities.player.Player;

/**
 * Created by robbe on 12/19/2016.
 */
public class EnemyBullet extends SpaceObject {
    private float speedX = 1000;
    private float x, y;
    private float spaceShipX, spaceShipY;
    private static SpriteBatch batch;
    private static Texture texture;
    private boolean remove;
    float dirX;
    float dirY;
    private Rectangle rect;
    private AttackerEnemy shooter;
    private Player player;

    private float speed = 40;

    public EnemyBullet(float x, float y,float spaceShipX, float spaceShipY, AttackerEnemy shooter, Player player){ // x,y = player
        this.x = x;
        this.y = y;
        this.spaceShipX = spaceShipX;
        this.spaceShipY =  spaceShipY;
        this.shooter = shooter;
        this.player = player;
        remove = false;
        batch = new SpriteBatch();
        texture = new Texture("core/assets/rsz_enemyBullet.png");
        rect = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
        setDirection();
    }

    public void setDirection(){
        dirX = spaceShipX - x;
        dirY = spaceShipY - y;
    }

    public void bulletMove(float dt){

        double destinationLength = Math.sqrt(dirX * dirX + dirY * dirY);
        x += (dirX * speed *dt ) / destinationLength *10;
        rect.setX(x);
        y += (dirY * speed *dt) / destinationLength *10;
        rect.setY(y);
    }

    public void update(float dt){
        checkCollision();
        if(!remove) {
            bulletMove(dt);
            if (x > Gdx.graphics.getWidth() || y > Gdx.graphics.getHeight()) {
                shooter.removeBullet(this);
            }
        }
    }

    private void checkCollision() {
            if(rect.overlaps(player.getRect()))
            {
                player.reduceHitpoints();
                removeBullet();
            }
        }



    public void removeBullet() {
        remove = true;
        shooter.removeBullet(this);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture,x,y);
        batch.end();
    }

    public boolean isRemove()
    {
        return remove;
    }
}

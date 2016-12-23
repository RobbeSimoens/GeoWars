package com.spaceraider.entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spaceraider.game.Spaceraider;

/**
 * Created by Kevin on 9/11/2016.
 */
public class SpaceObject {
    /*Position*/
    protected float x;
    protected float y;
    /*Vector*/
    protected float dx;
    protected float dy;
    /*Direction*/
    protected float radians;
    protected float speed;
    protected float rotationSpeed;

    protected int width;
    protected int height;

    protected Sprite sprite;
    protected Texture texture;

    /*Om door de randen van het spel te vliegen en aan de andere kant van het "game universum" er terug uit te komen*/
    protected void wrap(){
        if(x < 0) x = Spaceraider.WIDTH;
        if(x > Spaceraider.WIDTH) x = 0;
        if(y < 0) y = Spaceraider.HEIGHT;
        if(y > Spaceraider.HEIGHT) y = 0;
    }

    protected void wrap(String side)
    {
        if (side.equals("left"))
        {
            if(x < 0) x = Spaceraider.WIDTH / 2;
            if(x > Spaceraider.WIDTH / 2) x = 0;
            if(y < 0) y = Spaceraider.HEIGHT;
            if(y > Spaceraider.HEIGHT) y = 0;
        }
        else
        {
            if(x < Spaceraider.WIDTH / 2) x = Spaceraider.WIDTH;
            if(x > Spaceraider.WIDTH) x = Spaceraider.WIDTH / 2;
            if(y < 0) y = Spaceraider.HEIGHT;
            if(y > Spaceraider.HEIGHT) y = 0;
        }
    }


}

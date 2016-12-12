package com.spaceraider.entities;

/**
 * Created by robbe on 12/10/2016.
 */
public class Vector {
    public float x;
    public float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void normalize()
    {
        float l = (float) Math.sqrt(x*x + y*y);
        x /= l;
        y /= l;
    }

        // http://www.java-gaming.org/topics/bullet-shooting-in-mouse-direction/34029/view.html


}

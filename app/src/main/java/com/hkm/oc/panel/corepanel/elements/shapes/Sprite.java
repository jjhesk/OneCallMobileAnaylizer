package com.hkm.oc.panel.corepanel.elements.shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.hkm.oc.panel.corepanel.MapPanel;

/**
 * This is the BaseBottom sprite engine for a working one
 * Created by Hesk on
 */
public class Sprite {
    int x, y;
    int xspeed, yspeed;
    int height, width;
    Bitmap cir;
    MapPanel panel;
    //initial direction once from the starting point
    int direction = 3;
    private int currentFrame = 0;
    private int sprite_rows = 4;
    private int sprite_columns = 10;

    public Sprite(MapPanel p, Bitmap c) {
        panel = p;
        cir = c;
        height = cir.getHeight() / sprite_rows;
        width = cir.getWidth() / sprite_columns;
        x = y = 0;
        xspeed = 2;
        yspeed = 0;
    }

    //https://www.youtube.com/watch?v=J29V0nvmZ2M
    public void defineSprite(int r, int c) {
        sprite_columns = c;
        sprite_rows = r;
    }

    public void setconfig(int rows, int columns) {
        sprite_rows = rows;
        sprite_columns = columns;
    }

    public void update() {

        /*  moving the sprite in the canvas */
        //0 = up
        //1 = down
        //2 = left
        //3 = right
        //facing down
        if (x > panel.getWidth() - width - xspeed) {
            xspeed = 0;
            yspeed = 5;
            direction = 1;
        }
        //going left
        if (y > panel.getHeight() - height - yspeed) {
            xspeed = -5;
            yspeed = 0;
            direction = 2;
        }
        //facing up
        if (x + xspeed < 0) {
            x = 0;
            xspeed = 0;
            yspeed = -5;
            direction = 0;
        }
        //facing right
        if (y + yspeed < 0) {
            y = 0;
            xspeed = 5;
            yspeed = 0;
            direction = 3;
        }
        x += xspeed;
        y += yspeed;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentFrame = ++currentFrame % sprite_columns;
    }

    public void onDraw(Canvas cvs) {
        update();
        int srcY = direction * height;
        int srcX = currentFrame * width;
        //startin on the size of the rendering object
        // Rect src = new Rect(0, 0, width, height);
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        //starting on the position of the render object
        Rect dst = new Rect(x, y, x + width, y + height);
        cvs.drawBitmap(cir, src, dst, null);
    }
}

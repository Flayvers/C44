package com.example.appexamen2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class TraitContinu{
    private Path path;
    private int pathWidth;
    private Paint pencil;
    private float y = 500;
    private float x = 500;

    public TraitContinu(int width) {
        path = new Path();
        path.moveTo(500,500);
        pathWidth = width;
        pencil = new Paint();
        pencil.setColor(Color.BLACK);
        pencil.setStrokeWidth(getpathWidth());
        pencil.setStyle(Paint.Style.STROKE);
    }

    public int getpathWidth() {
        return pathWidth;
    }

    public Path getPath() {
        return path;
    }

    public void setPathWidth(int pathWidth) {
        this.pathWidth = pathWidth;
        pencil.setStrokeWidth(pathWidth);
    }

    public void moveX(float x) {
        this.x += x;
    }

    public void moveY(float y) {
        this.y += y;
    }

    public void dessiner(Canvas canvas){
        path.lineTo(x,y);
        canvas.drawPath(path,pencil);

    }
}

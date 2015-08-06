package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import sun.rmi.runtime.Log;

/**
 * Created by student on 7/30/2015.
 */
public class Player {
    private Texture img;
    private final float startAccelX;
    private final float startAccelY;
    private float accelX;
    private float accelY;
    private int maxWidth;
    private int maxHeight;
    private int minWidth;
    private int minHeight;
    private int width;
    private int height;
    private Vector2 locPlayer;
    private Vector2 velPlayer;
    private boolean growing;
    private boolean hitGround;
    private boolean dead;
    private int score;


    public Player(int locX, int locY){
        img=new Texture("circle.png");
        maxWidth=300;
        maxHeight=300;
        minWidth=100;
        minHeight=100;
        width=maxWidth;
        height=maxHeight;
        locPlayer=new Vector2(locX-width/2, locY-width/2);
        velPlayer=new Vector2(1, 1);
        growing=false;
        score=0;
        startAccelX=0;
        startAccelY=0;
    }

    public void draw(SpriteBatch batch){
        batch.draw(img, locPlayer.x-width/2, locPlayer.y-height/2, width, height);
        tilt();
        bounce();

        if (width==minWidth && height==minHeight){
            hitGround=true;
        }
        else{
            hitGround=false;
        }
    }

    public void tilt(){
        //x and y accelerometer values
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();

        if ((int)accelX==(int)startAccelX){
            velPlayer.x=0;
        }
        else if ((int)accelX==(int)startAccelX-1 || (int)accelX==(int)startAccelX+1){
            velPlayer.x=1;
        }
        else if ((int)accelX==(int)startAccelX-2 || (int)accelX==(int)startAccelX+2){
            velPlayer.x=2;
        }
        else if ((int)accelX==(int)startAccelX-3 || (int)accelX==(int)startAccelX+3){
            velPlayer.x=3;
        }
        else if ((int)accelX==(int)startAccelX-4 || (int)accelX==(int)startAccelX+4){
            velPlayer.x=5;
        }
        else if ((int)accelX==(int)startAccelX-5 || (int)accelX==(int)startAccelX+5){
            velPlayer.x=8;
        }
        else if ((int)accelX==(int)startAccelX-6 || (int)accelX==(int)startAccelX+6){
            velPlayer.x=10;
        }
        else if ((int)accelX==(int)startAccelX-7 || (int)accelX==(int)startAccelX+7){
            velPlayer.x=12;
        }
        else if ((int)accelX==(int)startAccelX-8 || (int)accelX==(int)startAccelX+8){
            velPlayer.x=14;
        }
        else if ((int)accelX==(int)startAccelX-9 || (int)accelX==(int)startAccelX+9){
            velPlayer.x=16;
        }
        else if ((int)accelX==(int)startAccelX-10 || (int)accelX==(int)startAccelX+10){
            velPlayer.x=18;
        }
        else{
            velPlayer.x=20;
        }

        if ((int)accelY==(int)startAccelY){
            velPlayer.y=0;
        }
        else if ((int)accelY==(int)startAccelY-1 || (int)accelY==(int)startAccelY+1){
            velPlayer.y=1;
        }
        else if ((int)accelY==(int)startAccelY-2 || (int)accelY==(int)startAccelY+2){
            velPlayer.y=2;
        }
        else if ((int)accelY==(int)startAccelY-3 || (int)accelY==(int)startAccelY+3){
            velPlayer.y=3;
        }
        else if ((int)accelY==(int)startAccelY-4 || (int)accelY==(int)startAccelY+4){
            velPlayer.y=5;
        }
        else if ((int)accelY==(int)startAccelY-5 || (int)accelY==(int)startAccelY+5){
            velPlayer.y=8;
        }
        else if ((int)accelY==(int)startAccelY-6 || (int)accelY==(int)startAccelY+6){
            velPlayer.y=10;
        }
        else if ((int)accelY==(int)startAccelY-7 || (int)accelY==(int)startAccelY+7){
            velPlayer.y=12;
        }
        else if ((int)accelY==(int)startAccelY-8 || (int)accelY==(int)startAccelY+8){
            velPlayer.y=14;
        }
        else if ((int)accelY==(int)startAccelY-9 || (int)accelY==(int)startAccelY+9){
            velPlayer.y=16;
        }
        else if ((int)accelY==(int)startAccelY-10 || (int)accelY==(int)startAccelY+10){
            velPlayer.y=18;
        }
        else{
            velPlayer.y=20;
        }


        //when tilting right
        if ((int)accelX<-1) {
            locPlayer.x += velPlayer.x;
        }
        //when tilting left
        else if ((int)accelX>1){
            locPlayer.x -= velPlayer.x;
        }

        //when tilting up
        if ((int)accelY<-1) {
            locPlayer.y += velPlayer.y;
        }
        //when tilting down
        else if ((int)accelY>1){
            locPlayer.y -= velPlayer.y;
        }

    }

    public void bounce(){
        //when ball at largest point, start shrinking
        if (width==maxWidth && height==maxHeight){
            growing=false;
        }
        //when ball at smallest point, start growing
        else if (width==minWidth && height==minHeight){
            if (dead==true){
                growing=false;
            }
            else if (dead==false){
                growing=true;
                score++;
            }
        }

        //when ball is going down
        if (growing==false){
            if (width>0 && height>0) {
                width -= 2;
                height -= 2;
            }
        }
        //when ball is going up
        else{
            width+=2;
            height+=2;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean getHitGround(){
        return hitGround;
    }

    public Vector2 getLocPlayer(){
        return locPlayer;
    }

    public void setDead(boolean dead){
        this.dead=dead;
    }

    public boolean getDead(){
        return dead;
    }

    public void setScore(int score){
        this.score=score;
    }

    public int getScore(){
        return score;
    }
}

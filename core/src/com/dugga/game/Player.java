package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import sun.rmi.runtime.Log;

/**
 * Created by student on 7/30/2015.
 */
public class Player {
    private Texture img;
    private float startAccelX;
    private float startAccelY;
    private float accelX;
    private float accelY;
    private int maxWidth;
    private int maxHeight;
    private int minWidth;
    private int minHeight;
    private double width;
    private double height;
    private Vector2 locPlayer;
    private Vector2 velPlayer;
    private boolean growing;
    private boolean hitGround;
    private boolean dead;
    private int score;
    private double bounceSpeed;
    private double moveSpeed;
    private boolean scoring;
    private double relativeSpeed;


    public Player(int locX, int locY){
        maxWidth=300;
        maxHeight=300;
        minWidth=100;
        minHeight=100;
        width=maxWidth;
        height=maxHeight;
        locPlayer=new Vector2(locX-(float)width/2, locY-(float)height/2);
        velPlayer=new Vector2(1, 1);
        growing=false;
        score=0;
        startAccelX=0;
        startAccelY=0;
        bounceSpeed=2;
        moveSpeed=bounceSpeed*0.5;
        dead=false;
        img=new Texture("circle.png");
        scoring=false;
        relativeSpeed=0.5;
    }

    public void draw(SpriteBatch batch){
        batch.draw(img, locPlayer.x-(float)width/2, locPlayer.y-(float)height/2, (float)width, (float)height);
        tilt();
        bounce();
        wrap();

        //checks if player hit ground
        if (width<=minWidth && height<=minHeight){
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

        moveSpeed=bounceSpeed*0.5;

        if ((int)accelX==(int)startAccelX){
            velPlayer.x=0*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-1 || (int)accelX==(int)startAccelX+1){
            velPlayer.x=1*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-2 || (int)accelX==(int)startAccelX+2){
            velPlayer.x=2*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-3 || (int)accelX==(int)startAccelX+3){
            velPlayer.x=3*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-4 || (int)accelX==(int)startAccelX+4){
            velPlayer.x=5*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-5 || (int)accelX==(int)startAccelX+5){
            velPlayer.x=8*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-6 || (int)accelX==(int)startAccelX+6){
            velPlayer.x=10*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-7 || (int)accelX==(int)startAccelX+7){
            velPlayer.x=12*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-8 || (int)accelX==(int)startAccelX+8){
            velPlayer.x=14*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-9 || (int)accelX==(int)startAccelX+9){
            velPlayer.x=16*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-10 || (int)accelX==(int)startAccelX+10){
            velPlayer.x=18*(float)moveSpeed;
        }
        else{
            velPlayer.x=20*(float)moveSpeed;
        }

        if ((int)accelY==(int)startAccelY){
            velPlayer.y=0*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-1 || (int)accelY==(int)startAccelY+1){
            velPlayer.y=1*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-2 || (int)accelY==(int)startAccelY+2){
            velPlayer.y=2*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-3 || (int)accelY==(int)startAccelY+3){
            velPlayer.y=3*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-4 || (int)accelY==(int)startAccelY+4){
            velPlayer.y=5*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-5 || (int)accelY==(int)startAccelY+5){
            velPlayer.y=8*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-6 || (int)accelY==(int)startAccelY+6){
            velPlayer.y=10*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-7 || (int)accelY==(int)startAccelY+7){
            velPlayer.y=12*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-8 || (int)accelY==(int)startAccelY+8){
            velPlayer.y=14*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-9 || (int)accelY==(int)startAccelY+9){
            velPlayer.y=16*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-10 || (int)accelY==(int)startAccelY+10){
            velPlayer.y=18*(float)moveSpeed;
        }
        else{
            velPlayer.y=20*(float)moveSpeed;
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
        if (width>=maxWidth && height>=maxHeight){
            growing=false;
        }
        //when ball at smallest point, start growing
        else if (width<=minWidth && height<=minHeight){
            scoring=true;
            if (dead){
                growing=false;
            }
            else if (!dead){
                growing=true;
            }
        }

        if (width>minWidth && height>minHeight){
            if (scoring) {
                score++;
                scoring=false;
            }
        }

        //when ball is going down
        if (!growing){
            if (width>0 && height>0) {
                width-=bounceSpeed;
                height-=bounceSpeed;
            }
        }
        //when ball is going up
        else if (growing){
            width+=bounceSpeed*0.9;
            height+=bounceSpeed*0.9;
        }
    }

    public void wrap(){
        if (locPlayer.x+width/2<0){
            locPlayer.x=Gdx.graphics.getWidth()+(float)width/2;
        }
        if (locPlayer.x-width/2>Gdx.graphics.getWidth()){
            locPlayer.x=0-(float)width/2;
        }

        if (locPlayer.y+height/2<0){
            locPlayer.y=Gdx.graphics.getHeight()+(float)height/2;
        }
        if (locPlayer.y-height/2>Gdx.graphics.getHeight()){
            locPlayer.y=0-(float)height/2;
        }
    }

    public Vector2[] checkPlayerLoc(){
        Vector2[] loc=new Vector2[(int)(width*height)];
        for (int i=(int)locPlayer.x-(int)width/2; i<width; i++){

        }
        for (int i=(int)locPlayer.y-(int)height/2; i<height; i++){

        }
        return loc;
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

    public void setBounceSpeed(double bounceSpeed){
        this.bounceSpeed=bounceSpeed;
    }

    public double getBounceSpeed(){
        return bounceSpeed;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getRelativeSpeed(){
        return relativeSpeed;
    }

    public void setRelativeSpeed(double relativeSpeed){
        this.relativeSpeed=relativeSpeed;
    }
}

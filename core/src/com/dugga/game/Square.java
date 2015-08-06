package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 8/5/15.
 */
public class Square {
    private Texture squareImg;
    private Vector2 velocity;
    public Square(){
        velocity=new Vector2(0,0);
    }
    public void draw(SpriteBatch batch, int locX, int locY, int sideLocX, int sideLocY, int width, int height, int direction, boolean white){
        if (white){
            squareImg=new Texture("squareW.png");
        }
        else{
            squareImg=new Texture("square.png");
        }
        if (direction==0){
            if (locX>=Gdx.graphics.getWidth()/2){
                velocity.x=-500;
                while (sideLocX>locX) {
                    sideLocX += velocity.x;
                }
            }
            else if (locX<Gdx.graphics.getWidth()/2){
                velocity.x=500;
                while (sideLocX<locX) {
                    sideLocX += velocity.x;
                }
            }
        }
        if (direction==1){
            if (locY>=Gdx.graphics.getHeight()/2){
                velocity.y=-500;
                while (sideLocY>locY) {
                    sideLocY += velocity.y;
                }
            }
            else if (locY<Gdx.graphics.getHeight()/2){
                velocity.y=500;
                while (sideLocY<locY) {
                    sideLocY += velocity.y;
                }
            }
        }
        batch.draw(squareImg, locX, locY, width, height);
    }
}

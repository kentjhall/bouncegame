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
    public Square(){
        squareImg=new Texture("square");
    }
    public void draw(SpriteBatch batch, Vector2 velocity, int locX, int locY, int direction){
        if (direction==0){
            if (locX>=Gdx.graphics.getWidth()){

            }
            else if (locX<Gdx.graphics.getWidth()){

            }
        }
        if (direction==1){
            if (locY>=Gdx.graphics.getHeight()){

            }
            else if (locY<Gdx.graphics.getHeight()){

            }
        }
        else if (direction==1){

        }
    }
}

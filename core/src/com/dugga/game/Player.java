package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by student on 7/30/2015.
 */
public class Player {
    private Texture img;
    private Rectangle hitBox;
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
    private int highScore;
    private double bounceSpeed;
    private double moveSpeed;
    private boolean scoring;
    private Sprite player;
    private boolean deathChange;
    private Preferences prefs;
    private int startingHighScore;
    private Animation dustAnimation;
    private TextureRegion[] dust;
    private TextureRegion dustFrame;
    private float stateTime;
    private boolean playDust;
    private Vector2 locDust;
    private double dustInterval;
    private final double initialBounceSpeed;

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
        highScore=0;
        startAccelX=0;
        startAccelY=0;
        initialBounceSpeed=2;
        bounceSpeed=initialBounceSpeed;
        moveSpeed=bounceSpeed*0.5;
        dead=false;
        img=new Texture("circle.png");
        scoring=false;
        hitBox=new Rectangle();
        player=new Sprite(img);
        deathChange=true;
        prefs=Gdx.app.getPreferences("Save Data");
        startingHighScore=prefs.getInteger("highScore");
        prefs.putInteger("gamesPlayed", prefs.getInteger("gamesPlayed")+1);
        locDust=new Vector2(0, 0);

        dust=new TextureRegion[9];
        for (int i=0; i<dust.length; i++){
            dust[i]=new TextureRegion(new Texture("animation/dust/dust"+(i+1)+".png"));
        }
        dustInterval=0.035;
        dustAnimation=new Animation((float)dustInterval, dust[0], dust[1], dust[2], dust[3], dust[4], dust[5], dust[6], dust[7], dust[8]);
        playDust=false;
        stateTime=0f;
    }

    public void draw(SpriteBatch batch){
        System.out.println(dustInterval);
        if(playDust){
            playDustAnimation(batch);
            dustAnimation.setFrameDuration((float)dustInterval);
        }
        player.setPosition(locPlayer.x - (float) width / 2, locPlayer.y - (float) height / 2);
        player.setSize((float) width, (float) height);
        player.setTexture(img);
        player.draw(batch);
        prefs.flush();
        if (prefs.getInteger("highScore")>0){
            if (score>prefs.getInteger("highScore")){
                highScore=score;
                prefs.putInteger("highScore", highScore);
            }
        }
        else{
            if (score>highScore){
                highScore=score;
                prefs.putInteger("highScore", highScore);
            }
        }

        if (!deathChange && width > maxWidth / 2 && height > maxHeight/2) {
            deathChange = true;
        }

    wrap();
        hitBox.set(player.getBoundingRectangle());
        tilt();
        bounce();

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
            velPlayer.x=3*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-3 || (int)accelX==(int)startAccelX+3){
            velPlayer.x=5*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-4 || (int)accelX==(int)startAccelX+4){
            velPlayer.x=7*(float)moveSpeed;
        }
        else if ((int)accelX==(int)startAccelX-5 || (int)accelX==(int)startAccelX+5){
            velPlayer.x=10*(float)moveSpeed;
        }

        if ((int)accelY==(int)startAccelY){
            velPlayer.y=0*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-1 || (int)accelY==(int)startAccelY+1){
            velPlayer.y=1*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-2 || (int)accelY==(int)startAccelY+2){
            velPlayer.y=3*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-3 || (int)accelY==(int)startAccelY+3){
            velPlayer.y=5*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-4 || (int)accelY==(int)startAccelY+4){
            velPlayer.y=7*(float)moveSpeed;
        }
        else if ((int)accelY==(int)startAccelY-5 || (int)accelY==(int)startAccelY+5){
            velPlayer.y=10*(float)moveSpeed;
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
                deathChange=false;
                growing=false;
            }
            else if (!dead){
                growing=true;
                playDust=true;
                locDust=new Vector2(locPlayer);
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

    public void playDustAnimation(SpriteBatch batch){
        stateTime+=Gdx.graphics.getDeltaTime();
        dustFrame=dustAnimation.getKeyFrame(stateTime, false);
        batch.draw(dustFrame, locDust.x-dustFrame.getRegionWidth()/2, locDust.y-dustFrame.getRegionHeight()/2);
        if (dustAnimation.isAnimationFinished(stateTime)){
            stateTime=0f;
            playDust=false;
        }
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

    public void setDeathChange(boolean deathChange){
        this.deathChange=deathChange;
    }

    public boolean getDeathChange(){
        return deathChange;
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

    public Rectangle getHitBox(){
        return hitBox;
    }

    public Preferences getPrefs(){
        return prefs;
    }

    public int getStartingHighScore(){
        return startingHighScore;
    }

    public void setStartingHighScore(int startingHighScore){
        this.startingHighScore=startingHighScore;
    }

    public void setDustInterval(double dustInterval){
        this.dustInterval=dustInterval;
    }

    public double getDustInterval(){
        return dustInterval;
    }

    public double getInitialBounceSpeed(){
        return initialBounceSpeed;
    }
}

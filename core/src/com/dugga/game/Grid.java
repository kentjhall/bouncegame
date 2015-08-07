package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by student on 7/30/2015.
 */
public class Grid {
    private Texture img;
    private int locX;
    private int locY;
    private int width;
    private int height;
    private int boxCount;
    private Rectangle[] hitBox;
    private int[] rand;
    private Random generator;
    private int hitX;
    private int hitY;
    private boolean[] bounceBlock;
    private int blockRarity;
    private ArrayList<Integer> gjPattern;
    private TextureAtlas atlas;
    private BitmapFont font;
    private Vector2 velocity;
    private Texture square;
    private Texture squareW;
    private double growWidth;
    private double growHeight;
    private boolean growing;
    private TextureAtlas.AtlasRegion region;
    private Sprite bounceSquare;
    private int[] bounceTracker;

    public Grid(int locX, int locY, int width){
        this.width=width;
        height=width;
        this.locX=locX - this.width/2;
        this.locY=locY - this.height/2;
        img=new Texture("grid.gif");
        generator = new Random();
        rand=new int[45];
        bounceBlock=new boolean [45];
        bounceTracker=new int[45];
        blockRarity=2;
        gjPattern=new ArrayList<Integer>(Arrays.asList(25, 26, 27, 30, 0, 5, 6, 7, 2, 17, 18, 19, 21, 23, 36, 38, 41, 42, 43));
        atlas=new TextureAtlas();
        font = new BitmapFont(Gdx.files.internal("fontB36.fnt"), atlas.findRegion("fontB36.png"), false);
        velocity=new Vector2(0, 0);
        square=new Texture("square.png");
        squareW=new Texture("squareW.png");
        growWidth=0;
        growHeight=0;
        //initialize rand and bounceBlock
        for(int i=0; i<rand.length;i++){
            rand[i]=2;
            rand[25]=0;
            rand[26]=0;
            rand[27]=0;
            rand[30]=0;
            rand[0]=0;
            rand[5]=0;
            rand[6]=0;
            rand[7]=0;
            rand[2]=0;
            rand[15]=0;
            rand[16]=0;
            rand[17]=0;
            rand[20]=0;
            rand[22]=0;
            rand[35]=0;
            rand[37]=0;
            rand[4]=0;
            rand[9]=0;
            rand[14]=0;
            rand[19]=0;
            rand[39]=0;
            rand[40]=0;
            rand[41]=0;
            rand[42]=0;
            bounceBlock[i]=false;
            bounceTracker[i]=0;
        }
    }

    public void draw(SpriteBatch batch){
        createHitBoxes(width / 5, height / 5, batch);
        updateGround();
        blockTransition();
    }

    public void createHitBoxes(int width, int height, SpriteBatch batch){
        int centerX=Gdx.graphics.getWidth()/2;
        int centerY=Gdx.graphics.getHeight()/2;
        int row1a=centerY+4*height;
        int row1b=centerY+3*height;
        int row1=centerY+2*height;
        int row2=centerY+height;
        int row3=centerY;
        int row4=centerY-height;
        int row5=centerY-2*height;
        int row5a=centerY-3*height;
        int row5b=centerY-4*height;
        int column1=centerX-2*width;
        int column2=centerX-width;
        int column3=centerX;
        int column4=centerX+width;
        int column5=centerX+2*width;
        
        hitBox=new Rectangle[45];
        for (boxCount=0; boxCount<hitBox.length; boxCount++) {
            switch (boxCount){
                case 0:
                    hitY=row1-width/2;
                    hitX=column1-height/2;
                    /*if (rand[0]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[0]=true;
                    }*/
                    break;
                case 1:
                    hitY=row1-width/2;
                    hitX=column2-height/2;
                    /*if (rand[1]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[1]=true;
                    }*/
                    break;
                case 2:
                    hitY=row1-width/2;
                    hitX=column3-height/2;
                    /*if (rand[2]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[2]=true;
                    }*/
                    break;
                case 3:
                    hitY=row1-width/2;
                    hitX=column4-height/2;
                    /*if (rand[3]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[3]=true;
                    }*/
                    break;
                case 4:
                    hitY=row1-width/2;
                    hitX=column5-height/2;
                    /*if (rand[4]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[4]=true;
                    }*/
                    break;
                case 5:
                    hitY=row2-width/2;
                    hitX=column1-height/2;
                    /*if (rand[5]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[5]=true;
                    }*/
                    break;
                case 6:
                    hitY=row2-width/2;
                    hitX=column2-height/2;
                    /*if (rand[6]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[6]=true;
                    }*/
                    break;
                case 7:
                    hitY=row2-width/2;
                    hitX=column3-height/2;
                    /*if (rand[7]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[7]=true;
                    }*/
                    break;
                case 8:
                    hitY=row2-width/2;
                    hitX=column4-height/2;
                    /*if (rand[8]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[8]=true;
                    }*/
                    break;
                case 9:
                    hitY=row2-width/2;
                    hitX=column5-height/2;
                    /*if (rand[9]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[9]=true;
                    }*/
                    break;
                case 10:
                    hitY=row3-width/2;
                    hitX=column1-height/2;
                    /*if (rand[10]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[10]=true;
                    }*/
                    break;
                case 11:
                    hitY=row3-width/2;
                    hitX=column2-height/2;
                    /*if (rand[11]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[11]=true;
                    }*/
                    break;
                case 12:
                    hitY=row3-width/2;
                    hitX=column3-height/2;
                    /*if (rand[12]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[12]=true;
                    }*/
                    break;
                case 13:
                    hitY=row3-width/2;
                    hitX=column4-height/2;
                    /*if (rand[13]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[13]=true;
                    }*/
                    break;
                case 14:
                    hitY=row3-width/2;
                    hitX=column5-height/2;
                    /*if (rand[14]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[14]=true;
                    }*/
                    break;
                case 15:
                    hitY=row4-width/2;
                    hitX=column1-height/2;
                    /*if (rand[15]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[15]=true;
                    }*/
                    break;
                case 16:
                    hitY=row4-width/2;
                    hitX=column2-height/2;
                    /*if (rand[16]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[16]=true;
                    }*/
                    break;
                case 17:
                    hitY=row4-width/2;
                    hitX=column3-height/2;
                    /*if (rand[17]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[17]=true;
                    }*/
                    break;
                case 18:
                    hitY=row4-width/2;
                    hitX=column4-height/2;
                    /*if (rand[18]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[18]=true;
                    }*/
                    break;
                case 19:
                    hitY=row4-width/2;
                    hitX=column5-height/2;
                    /*if (rand[19]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[19]=true;
                    }*/
                    break;
                case 20:
                    hitY=row5-width/2;
                    hitX=column1-height/2;
                    /*if (rand[20]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[20]=true;
                    }*/
                    break;
                case 21:
                    hitY=row5-width/2;
                    hitX=column2-height/2;
                    /*if (rand[21]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[21]=true;
                    }*/
                    break;
                case 22:
                    hitY=row5-width/2;
                    hitX=column3-height/2;
                    /*if (rand[22]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[22]=true;
                    }*/
                    break;
                case 23:
                    hitY=row5-width/2;
                    hitX=column4-height/2;
                    /*if (rand[23]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[23]=true;
                    }*/
                    break;
                case 24:
                    hitY=row5-width/2;
                    hitX=column5-height/2;
                    /*if (rand[24]==0) {
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[24]=true;
                    }*/
                    break;
                case 25:
                    hitY=row1a-width/2;
                    hitX=column1-height/2;
                    /*if (rand[25]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[25]=true;
                    }*/
                    break;
                case 26:
                    hitY=row1a-width/2;
                    hitX=column2-height/2;
                    /*if (rand[26]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[26]=true;
                    }*/
                    break;
                case 27:
                    hitY=row1a-width/2;
                    hitX=column3-height/2;
                    /*if (rand[27]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[27]=true;
                    }*/
                    break;
                case 28:
                    hitY=row1a-width/2;
                    hitX=column4-height/2;
                    /*if (rand[28]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[28]=true;
                    }*/
                    break;
                case 29:
                    hitY=row1a-width/2;
                    hitX=column5-height/2;

                    batch.draw(squareW, hitX, hitY, width, height);
                    font.draw(batch, ""+MyGdxGame.getPlayer().getScore(), hitX+width/2, hitY+height/2);
                    break;
                case 30:
                    hitY=row1b-width/2;
                    hitX=column1-height/2;
                    /*if (rand[30]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[30]=true;
                    }*/
                    break;
                case 31:
                    hitY=row1b-width/2;
                    hitX=column2-height/2;
                    /*if (rand[31]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[31]=true;
                    }*/
                    break;
                case 32:
                    hitY=row1b-width/2;
                    hitX=column3-height/2;
                    /*if (rand[32]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[32]=true;
                    }*/
                    break;
                case 33:
                    hitY=row1b-width/2;
                    hitX=column4-height/2;
                    /*if (rand[33]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[33]=true;
                    }*/
                    break;
                case 34:
                    hitY=row1b-width/2;
                    hitX=column5-height/2;
                    /*if (rand[34]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[34]=true;
                    }*/
                    break;
                case 35:
                    hitY=row5a-width/2;
                    hitX=column1-height/2;
                    /*if (rand[35]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[35]=true;
                    }*/
                    break;
                case 36:
                    hitY=row5a-width/2;
                    hitX=column2-height/2;
                    /*if (rand[36]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[36]=true;
                    }*/
                    break;
                case 37:
                    hitY=row5a-width/2;
                    hitX=column3-height/2;
                    /*if (rand[37]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[37]=true;
                    }*/
                    break;
                case 38:
                    hitY=row5a-width/2;
                    hitX=column4-height/2;
                    /*if (rand[38]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[38]=true;
                    }*/
                    break;
                case 39:
                    hitY=row5a-width/2;
                    hitX=column5-height/2;
                    /*if (rand[39]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[39]=true;
                    }*/
                    break;
                case 40:
                    hitY=row5b-width/2;
                    hitX=column1-height/2;
                    /*if (rand[40]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[40]=true;
                    }*/
                    break;
                case 41:
                    hitY=row5b-width/2;
                    hitX=column2-height/2;
                    /*if (rand[41]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[41]=true;
                    }*/
                    break;
                case 42:
                    hitY=row5b-width/2;
                    hitX=column3-height/2;
                    /*if (rand[42]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[42]=true;
                    }*/
                    break;
                case 43:
                    hitY=row5b-width/2;
                    hitX=column4-height/2;
                    /*if (rand[43]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[43]=true;
                    }*/
                    break;
                case 44:
                    hitY=row5b-width/2;
                    hitX=column5-height/2;
                    /*if (rand[44]==0){
                        batch.draw(square, hitX, hitY, width, height);
                        bounceBlock[44]=true;
                    }*/
                    break;
                default:
                    hitY=row1-width/2;
                    hitX=column1-height/2;
                    break;
            }
            if (rand[boxCount]==0 && boxCount!=29){
                region=new TextureAtlas.AtlasRegion(square, hitX, hitY, width, height);
                bounceSquare=new Sprite(region);
                bounceSquare.setOriginCenter();
                bounceSquare.setPosition(hitX, hitY);
                bounceSquare.setScale((float)growWidth, (float)growHeight);
                bounceSquare.draw(batch);
                //batch.draw(square, hitX, hitY, growWidth, growHeight);
                bounceBlock[boxCount]=true;
            }
            hitBox[boxCount]=new Rectangle(hitX, hitY, width, height);

            if (hitBox[boxCount].contains(MyGdxGame.getPlayer().getLocPlayer().x, MyGdxGame.getPlayer().getLocPlayer().y) && MyGdxGame.getPlayer().getHitGround()){
                if (!bounceBlock[boxCount]) {
                    if (bounceTracker[boxCount]>0) {
                        bounceTracker[boxCount]--;
                    }
                    MyGdxGame.getPlayer().setDead(true);
                }
                else if (bounceBlock[boxCount]){
                    if (bounceTracker[boxCount]<2) {
                        bounceTracker[boxCount]++;
                    }
                    if (MyGdxGame.getPlayer().getScore()%5==0 && MyGdxGame.getPlayer().getScore()!=0){
                        blockRarity++;
                    }
                }
            }
            if (bounceBlock[boxCount] == true) {
                bounceBlock[boxCount] = false;
            }
        }

    }

    //allows me to make a pattern of blocks simply by inputting their numbers into this function through and arraylist
    public void makePattern(ArrayList<Integer> arrayList){
        for (int x=0;x<rand.length;x++){
            rand[x]=2;
        }
        for (int i=0;i<arrayList.size();i++){
            rand[arrayList.get(i)]=0;
        }
    }

    public void updateGround(){
        if (MyGdxGame.getPlayer().getHitGround()) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (MyGdxGame.getPlayer().getScore()%10==0){
                        if (growWidth>=0.9 && growHeight>=0.9){
                            growing=false;
                        }
                        makePattern(gjPattern);
                    }
                    else {
                        if (growWidth>=0.9 && growHeight>=0.9){
                            growing=false;
                        }

                    }
                }
            }, (float) 1.25);
        }
        if (growWidth<=0 && growHeight<=0 && MyGdxGame.getPlayer().getScore()%10 != 0){
            for (int i = 0; i < rand.length; i++) {
                rand[i] = generator.nextInt(blockRarity);
            }
        }
    }

    public void blockTransition(){
        System.out.println(growWidth);
        if (growWidth<0 && growHeight<0) {
            growing=true;
        }
        if (growing && growWidth<=0.9 && growHeight<=0.9){
            growWidth+=0.1;
            growHeight+=0.1;
        }
        else if (!growing){
            growWidth-=0.1;
            growHeight-=0.1;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getBoxCount(){
        return boxCount;
    }
}

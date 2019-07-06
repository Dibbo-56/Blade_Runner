
package tilegame.Entity;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.game;


public abstract class Entity {
    
    protected Handler handler;
    protected float x,y;
    protected int width,height;
    
    public Entity(Handler handler,float x,float y,int width,int height){
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public float getx(){
        return x;
    }
    
    public void setx(float x){
        this.x=x;
    }
    
    public float gety(){
        return y;
    }
    
    public void sety(float y){
        this.y=y;
    }
    
    public int getwidth(){
        return width;
    }
    
    public void setwidth(int width){
        this.width=width;
    }
        
    public int getheight(){
        return height;
    }
    
    public void setheight(int height){
        this.height=height;
    }
}

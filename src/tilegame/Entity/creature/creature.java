
package tilegame.Entity.creature;

import java.awt.Graphics;
import tilegame.Entity.Entity;
import tilegame.Handler;
import tilegame.game;


public abstract class creature extends Entity{

    public static final int default_health=10;
    public static final float default_speed=3;
    public static final int default_c_width=60;
    public static final int default_c_height=60;
    
    protected int health;
    protected float speed;
    protected float xmove,ymove;
    
    
    public creature(Handler handler,float x, float y,int width,int height) {
        super(handler,x,y,width,height);
        health=default_health;
        speed=default_speed;
        xmove=0;
        ymove=0;
    }

    
    public void move(){
        x+=xmove;
        y+=ymove;
    }
        
    
    public float getxmove(){
        return xmove;
    } 
    
    public void setxmove(float xmove){ 
        this.xmove=xmove;
    }

    public float getymove(){
        return ymove;
    } 
    
    public void setymove(float ymove){ 
        this.ymove=ymove;
    }
    
    public int gethealth(){
        return health;
    }
    
    public void sethealth(int health){
        this.health=health;
    }

    public float getspeed(){
        return speed;
    }
    
    public void setspeed(float speed){
        this.speed=speed;
    }
    
    
}

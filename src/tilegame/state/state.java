
package tilegame.state;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.game;


public abstract class state {
    
    private static state currentstate = null;
    
    public static void setstate(state st){
        currentstate = st;
    }
    
    public static state getstate(){
        return currentstate;
    } 
    
    protected Handler handler;
    
    public state(Handler handler){
        this.handler=handler;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);

   
}

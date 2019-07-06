
package tilegame;

import tilegame.gfx.gamecamera;
import tilegame.input.MouseManager;
import tilegame.input.keymg;


public class Handler {
    private game ga;
    
    public Handler(game ga){
        this.ga=ga;
    }
    
    public game getgame(){
        return ga;
    }
    
    public void setgame(game ga){
        this.ga=ga;
    }
    
    public int getwidth(){
        return ga.getwidth();
    }
    
    public int getheight(){
        return ga.getheight();
    }
    public keymg getKeyManager(){
        return ga.getKeyManager();
    }
    
    public gamecamera getgamecamera(){
        return ga.getgamecamera();
    }
    
    public MouseManager getMouseManager(){
        return ga.getMouseManager();
    }
}

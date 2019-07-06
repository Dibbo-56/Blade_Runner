
package tilegame.state;

import java.awt.Graphics;
import tilegame.Entity.creature.player;
import tilegame.Handler;
import tilegame.game;
import tilegame.gfx.asset;


public class gamestate extends state {
   
    private player p;
    
    public gamestate(Handler handler){
        super(handler);
        p = new player(handler,20,400);
        handler.getgamecamera().move(100,200);
    }

    
    
    public void tick(){
        p.tick();
        handler.getgamecamera().move(1,0);
    }
    
    public void render(Graphics g){
        p.render(g);
        
    }
}

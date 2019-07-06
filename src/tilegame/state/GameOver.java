
package tilegame.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tilegame.Entity.creature.player;
import tilegame.Handler;
import tilegame.gfx.asset;


public class GameOver extends state{

    String s;
    
    public GameOver(Handler handler) {
        super(handler);
    }
    
    public void tick(){
        
    }
    

    
    public void render(Graphics g) {
        g.drawImage(asset.gamepic, 0,0, null);
        g.drawImage(asset.go, 350,20, null);
        Graphics2D g2d=(Graphics2D) g;
        Font fnt= new Font("areial",Font.BOLD,50);
        g.setFont(fnt);
        g.setColor(Color.black);
        g.drawString("High Score",430,250);
        s=String.valueOf(player.point); 
        g.drawString(s,720,250);
    }
}

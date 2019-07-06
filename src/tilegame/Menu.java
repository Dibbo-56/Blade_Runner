
package tilegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import static tilegame.game.handler;


public class Menu {
    
   // Handler handler;
    public Rectangle titlebutton = new Rectangle(430,50,365,70);
    public Rectangle playbutton = new Rectangle(530,160,120,55);
    //public Rectangle playbutton = new Rectangle(430,50,365,70);
    public void tick(){
        if(handler.getMouseManager().isLeftpressed()){
        
            game.sta=game.STATE.GAME;
        }
    }
    public void render(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        Font fnt= new Font("areial",Font.BOLD,50);
        g.setFont(fnt);
        g.setColor(Color.black);
        g.drawString("Blade Runner",450,100);
        g2d.draw(titlebutton);
        Font fnt1= new Font("areial",Font.BOLD,40);
        g.setFont(fnt1);
        g.setColor(Color.black);
        g.drawString("Play",550,200);
        g2d.draw(playbutton);
    }
}

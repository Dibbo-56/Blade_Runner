
package tilegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import tilegame.game;
import tilegame.game.STATE;


public class MouseManager implements MouseListener,MouseMotionListener{

    private boolean leftpressed;
    private int mx,my;
    
    public MouseManager(){
    
    }
    
    public boolean isLeftpressed(){
        return leftpressed;
    }
    
    public int getMouseX(){
        return mx;
    }
    
    public int getMouseY(){
        return my;
    }
    
    public void mouseClicked(MouseEvent e) {
         
    }

   
    public void mousePressed(MouseEvent e) {
        int x=getMouseX();
        int y=getMouseY();
        if(x >= 415 && x <= 700){
            if(y >= 200 && y <= 318){
                if(e.getButton()==MouseEvent.BUTTON1 && game.sta==STATE.MENU){
                    leftpressed=true;
                    game.sta=STATE.GAME;
                }
            }
        }
        if(game.sta==STATE.GAMEOVER){
            if(e.getButton()==MouseEvent.BUTTON1){
                    leftpressed=true;
                    game.sta=STATE.MENU;
                }
        }
    }

    
    public void mouseReleased(MouseEvent e) {
         if(e.getButton()==MouseEvent.BUTTON1){
             leftpressed=false;
         }
    }

    
    public void mouseEntered(MouseEvent e) {
         
    }

    
    public void mouseExited(MouseEvent e) {
         
    }

    
    public void mouseDragged(MouseEvent e) {
         
    }

    
    public void mouseMoved(MouseEvent e) {
        mx=e.getX();
        my=e.getY();
    }
    
}

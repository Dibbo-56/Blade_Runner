
package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class keymg implements KeyListener {

    private boolean[] keys;
    public boolean up,down,left,right;
    
    public keymg(){
        keys= new boolean[256];
    }
    
    public void tick(){
        up=keys[KeyEvent.VK_UP];
        down=keys[KeyEvent.VK_DOWN];
        left=keys[KeyEvent.VK_LEFT];
        right=keys[KeyEvent.VK_RIGHT];
    }
    
    public void keyTyped(KeyEvent e) {
       
    }

    
    public void keyPressed(KeyEvent e) {
       keys[e.getKeyCode()]=true;
      // System.out.println("pressed!");
    }

   
    public void keyReleased(KeyEvent e) {
       keys[e.getKeyCode()]=false;
    }
    
    
    
}

package tilegame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    public static boolean isPressed = false;

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Something is happening!");
            int mx = e.getX();
            int my = e.getY();

            //public Rectangle titlebutton = new Rectangle(430,50,365,70);
            //public Rectangle playbutton = new Rectangle(530,160,120,55);
            //playbutton
            if (mx >= 530 && mx <= 650) {
                if (my >= 160 && my <= 215) {
                    isPressed = true;
                    game.sta = game.STATE.GAME;
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

}


package tilegame.gfx;

import java.awt.image.BufferedImage;


public class asset {
    
    public static BufferedImage s1,s2,rb,gb,gab,b1b,b2b,kb,cn,startpic,playButton,gamepic,go;
    
    public static void init(){
        spritesheet sheet=new spritesheet(Image.loadimage("/text/ss.png"));
        startpic=Image.loadimage("/text/title.png");
        playButton=Image.loadimage("/text/PlayButton.png");
        gamepic=Image.loadimage("/text/gamepic.png");
        go=Image.loadimage("/text/gameover.png");
        s1=sheet.crop(12,279,74,71);
        s2=sheet.crop(99,277,74,71);
        b1b=sheet.crop(95,7,81,91);
        b2b=sheet.crop(208,117,102,141);
        rb=sheet.crop(9,7,78,97);
        gb=sheet.crop(186,8,79,91);
        gab=sheet.crop(278,6,94,99);
        kb=sheet.crop(97,111,98,97);
        cn=sheet.crop(6,116,27,25);
    }
}

package tilegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;
import tilegame.Entity.creature.player;
import tilegame.display.Display;
import tilegame.gfx.Image;
import tilegame.gfx.asset;
import tilegame.gfx.gamecamera;
import tilegame.gfx.spritesheet;
import tilegame.input.MouseManager;
import tilegame.input.keymg;
import tilegame.state.GameOver;
import tilegame.state.gamestate;
import tilegame.state.menustate;
import tilegame.state.state;

public class game implements Runnable {

    private Display display;
    //balloon
    public static int rbx = 1100, rby = 363, rby2;
    public static int gbx = 1100, gby = 369, gby2;
    public static int b1bx = gbx + 250, b1by = 369, b1by2;
    public static int gabx = b1bx + 400, gaby = 361, gaby2;
    public static int kbx = gabx + 250, kby = 363, kby2;

    //coin
    public static int c1x = rbx + 150, c1y = 435, c1y2;
    public static int c2x = gbx + 200, c2y = 435, c2y2;
    public static int c3x = b1bx + 350, c3y = 435, c3y2;
    public static int c4x = kbx + 50, c4y = 435, c4y2;

    // speed/time
    private int Speed[]={2,3,4,6};
    private int time[]={600,800,950,1000};
    private int i=0;
    
    private String title;
    private int width, height;

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage ti;
    private spritesheet sheet;

    //state
    private state gastate;
    private state mstate;
    private state gostate;

    //key
    private keymg keymanage;
    //mouse
    private MouseManager mouse;

    //gamecamera
    private gamecamera gaca;

    //Handler
    public static Handler handler;

    //Random
    Random randomGenerator = new Random();

    private boolean running = false;
    boolean ff = false;
    //int x=1100,y=380;

    //timer
    private long currentTime;
    private long delta;
    private long previousTime = System.currentTimeMillis();
    private long passedTime=10;
    private int cnt = 1;

    //enemy
    public static boolean Rb = true, Gb, B1B, Gab, Kb, c1, c2, c3, c4;

    int randomInt = randomGenerator.nextInt(401);
    int tt = 1000;

    //Menu
    public static enum STATE {
        MENU,
        GAMEOVER,
        GAME
    };

    public static STATE sta = STATE.MENU;

    

    public game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keymanage = new keymg();
        mouse = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keymanage);
        display.getFrame().addMouseListener(mouse);
        display.getFrame().addMouseMotionListener(mouse);
        display.getCanvas().addMouseListener(mouse);
        display.getCanvas().addMouseMotionListener(mouse);
        
        asset.init();

        gaca = new gamecamera(0, 0);
        handler = new Handler(this);
        gastate = new gamestate(handler);
        mstate = new menustate(handler);
        gostate = new GameOver(handler);
        
        state.setstate(gastate);

    }

    private void tick() {

        if (sta == STATE.GAME) {
            timer();
            pos();

            updateEnemyPosition();

            keymanage.tick();
            if (state.getstate() != null) {
                state.getstate().tick();
            }

        }
        

    }

    private void updateEnemyPosition() {
        rby2 = rby + 97;
        gby2 = gby + 91;
        b1by2 = b1by + 91;
        kby2 = kby + 97;
        gaby2 = gaby + 99;
        c1y2 = c1y + 25;
        c2y2 = c2y + 25;
        c3y2 = c3y + 25;
        c4y2 = c4y + 25;
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, 1200, 500);

        if (sta == STATE.GAME) {
            if (state.getstate() != null) {
                state.getstate().render(g);
            }
            
            drawball();

            //For continuos Rendering of crrently Active Balls
            if (Rb) {
                g.drawImage(asset.rb, rbx, rby, null);
            }
            if (Gb) {
                g.drawImage(asset.gb, gbx, gby, null);
            }
            if (B1B) {
                g.drawImage(asset.b1b, b1bx, b1by, null);
            }
            if (Gab) {
                g.drawImage(asset.gab, gabx, gaby, null);
            }
            /*if (Kb) {
                g.drawImage(asset.kb, kbx, kby, null);
            }*/
            if (c1) {
                g.drawImage(asset.cn, c1x, c1y, null);
            }
            if (c2) {
                g.drawImage(asset.cn, c2x, c2y, null);
            }
            if (c3) {
                g.drawImage(asset.cn, c3x, c3y, null);
            }
            if (c4) {
                g.drawImage(asset.cn, c4x, c4y, null);
            }
        } else if (sta == STATE.MENU) {
            tt=1000;
            player.bx=0;
            player.by=0;
            player.bx2=player.bx+1200;
            i=0;
            passedTime=100;
            player.point=0;
            mstate.render(g);
        }
        else if(sta == STATE.GAMEOVER){
            Rb = false;
            Gb=false;
            B1B=false;
            Gab=false;
            Kb=false;
            c1=false;
            c2=false;
            c3=false;
            c4=false;
            
            //rbx=1100;
            gostate.render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 10;
        double timepertick = 100000000 / fps;
        double delta = 0;
        long now;
        long last = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - last) / timepertick;
            timer += (now - last);
            last = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }
        }

        stop();
    }

    public void timer() {
        currentTime = System.currentTimeMillis();
        delta = currentTime - previousTime;
        passedTime += delta;
        previousTime = currentTime;
    }

    public void pos() {
        if(player.point==10){
            i=1;
            tt=950;
        }
        if(player.point==20){
            i=2;
            tt=800;
        }
        if(player.point==30){
            i=3;
            tt=600;
        }
        int sss=Speed[i];
        player.bx-=Speed[i];
        player.bx2-=Speed[i];
        player.bx3-=Speed[i];
        //System.out.println("Speed "+ sss + " tt "+tt+" Point "+player.point);
        if (rbx >= 0 && Rb == true) {
            rbx -= Speed[i];
        } else {
            Rb = false;
            rbx = 1100;
        }

        if (gbx >= 0 && Gb == true) {
            gbx -= Speed[i];
        } else {
            Gb = false;
            gbx = 1100;
        }

        if (b1bx >= 0 && B1B == true) {
            b1bx -= Speed[i];
        } else {
            B1B = false;
            b1bx = 1100;
        }

        if (gabx >= 0 && Gab == true) {
            gabx -= Speed[i];
        } else {
            Gab = false;
            gabx = 1100;
        }
        if (c1x >= 0 && c1 == true) {
            c1x -= Speed[i];
        } else {
            c1 = false;
            c1x = 1100;
        }
        if (c2x >= 0 && c2 == true) {
            c2x -= Speed[i];
        } else {
            c2 = false;
            c2x = 1100;
        }
        if (c3x >= 0 && c3 == true) {
            c3x -= Speed[i];
        } else {
            c3 = false;
            c3x = 1100;
        }
        if (c4x >= 0 && c4 == true) {
            c4x -= Speed[i];
        } else {
            c4 = false;
            c4x = 1100;
        }

    }

    public void drawball() {
        
        if (passedTime >= tt) {
          
            //System.out.println(passedTime);
            if (cnt == 1) {
                rbx = 1100;
                g.drawImage(asset.rb, rbx, rby, null);
                Rb = true;
            }
            if (cnt == 2) {
                c1x = 1100;
                g.drawImage(asset.cn, c1x, c1y, null);
                c1 = true;
            }
            if (cnt == 3) {
                gbx = 1100;
                g.drawImage(asset.gb, gbx, gby, null);
                Gb = true;
            }
            if (cnt == 4) {
                c2x = 1100;
                g.drawImage(asset.cn, c2x, c2y, null);
                c2 = true;
            }
            if (cnt == 5) {
                b1bx = 1100;
                g.drawImage(asset.b1b, b1bx, b1by, null);
                B1B = true;
            }
            if (cnt == 6) {
                c3x = 1100;
                g.drawImage(asset.cn, c3x, c3y, null);
                c3 = true;
            }
            if (cnt == 7) {
                gabx = 1100;
                g.drawImage(asset.gab, gabx, gaby, null);
                Gab = true;
            }
            if (cnt == 8) {
                c4x = 1100;
                g.drawImage(asset.cn, c4x, c4y, null);
                c4 = true;
            }
            passedTime = 0;
            cnt++;
            if(cnt==9 ){
               cnt=0;
            }
        }
    }

    public keymg getKeyManager() {
        return keymanage;
    }

    public MouseManager getMouseManager() {
        return mouse;
    }

    public gamecamera getgamecamera() {
        return gaca;
    }

    public int getwidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

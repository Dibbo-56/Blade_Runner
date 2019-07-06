
package tilegame.Entity.creature;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import tilegame.Handler;
import tilegame.game;
import tilegame.game.STATE;
import tilegame.gfx.asset;


public class player extends creature {
    
    //game ga;
    public boolean jump=false;
    private boolean rb,gb,gab,b1b,b2b;
    public static boolean cn1,cn2,cn3,cn4;
    public long jumptime=950;
    public boolean fall=false;
    public boolean ff=false;
    public static int point=0;
    public static int bx=0,by=0,bx2=bx+1200,bx3;
    public double gra=0.1;
    
    public player (Handler handler,float x,float y){
        super(handler,x,y,creature.default_c_width,creature.default_c_height);
        //this.ga=ga;
    }
    
    
    public void tick(){
        getInput();
        move();
        collisionManager();
        Point();  
    }
    
    void collisionManager(){
        rb = gb = gab = b1b = false;
        rb = detectCollision(game.rby, game.rby2, game.rbx);
        gb = detectCollision(game.gby, game.gby2, game.gbx);
        b1b = detectCollision(game.b1by, game.b1by2, game.b1bx);
        gab = detectCollision(game.gaby, game.gaby2, game.gabx);
        
        if(gb == true || rb == true || b1b == true || gab == true){
            //System.out.println("Collision with ball\n");
            game.sta=STATE.GAMEOVER;
            //y=400;
            //point=0;
            jump=fall=false;
            y=400;
        }
    }
    
    void Point(){
        cn1=cn2=cn3=cn4=false;
        
        cn1=detectCoinCollision(game.c1y,game.c1y2,game.c1x);
        cn2=detectCoinCollision(game.c2y,game.c2y2,game.c2x);
        cn3=detectCoinCollision(game.c3y,game.c3y2,game.c3x);
        cn4=detectCoinCollision(game.c4y,game.c4y2,game.c4x);
        
        if(cn1 == true){
            point++;
            game.c1=false;
            //System.out.println(point);
        }
        if(cn2 == true){
            point++;
            game.c2=false;
            //System.out.println(point);
        }
        if(cn3 == true){
            point++;
            game.c3=false;
            //System.out.println(point);
        }
        if(cn4 == true){
            point++;
            game.c4=false;
            //System.out.println(point);
        }
    }
    
    
    
    // for coin count
    boolean detectCoinCollision(int coinY1,int coinY2,int coinX){
        int tx,tx2,ty;
        tx = (int) getx();
        ty = (int) gety() + getheight() ;
        tx2 = tx + (int)getwidth();
        
        if(ty>=coinY1 && ty<=coinY2 && tx2>=coinX){
            return true;
        }
        return false;
    }
    
    //for collision detection
    boolean detectCollision(int enemyY1, int enemyY2,int enemyX){
        int tx,tx2,ty;
        
        tx = (int) getx();
        ty = (int) gety() + getheight() ;
        tx2 = tx + (int)getwidth();
        
        //System.out.println( "eny1  " +enemyY1);
        //System.out.println("eny2  " +enemyY2);
        //System.out.println("tx2   " +tx2);
        /*if(ty >= enemyY1 && ty <= enemyY2 && enemyX <= tx2){
            return true;  //Collision detected
        }*/
        

        if(ty>=enemyY1 && ty<=enemyY2 && tx2>=enemyX){
            return true;
        }
        
        return false;
    }
    
    
    
    public void getInput(){
        xmove=0;
        ymove=0;
        //System.out.println(y);
        
        if(handler.getKeyManager().up && (y>=400 && y<=403)){
           //ymove=-speed;
           jump=true;
        }
        if(jump ){
            y-=3;
            //y=-speed;
        }
        if(y<=187){
            jump=false;
            fall=true;
        }
        
        if(fall ){
            y+=3;
            //ymove=speed;
        }
        if(y>=400){
            fall=false;
        }
        
        
    }
    
   
    
    public void render(Graphics g){
        
        g.drawImage(asset.gamepic, bx,by, null);
        if(bx2<1200){
                bx3=bx2;
                g.drawImage(asset.gamepic, bx3,by, null);
        }
        if(bx2<=0){
            bx2=bx3+1200;
            bx=0;
        }
    
        if(ff==false){
           g.drawImage(asset.s1,(int) x,(int) y,width,height,null);
           ff=true;
        }
        else{
            g.drawImage(asset.s2,(int) x,(int) y,width,height,null);
            ff=false;
        }
    }
    
    public class thrd implements Runnable{

        public void run() {
            try{
                
                Thread.sleep(jumptime);
                jump=false;
                fall=true;
            }
            catch(Exception e){
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }
        
    }
    
    public class thread implements Runnable{

        public void run() {
            try{
                Thread.sleep(jumptime);
                fall=false;
            }
            catch(Exception e){
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }
        
    }
    
    
}

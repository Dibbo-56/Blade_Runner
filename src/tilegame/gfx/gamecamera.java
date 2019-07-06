
package tilegame.gfx;



public class gamecamera {
 
    private float xoffset,yoffset;
    
    public gamecamera(float xoffset,float yoffset){
        this.xoffset=xoffset;
        this.yoffset=yoffset;
    }
    
    public void move(float xamt,float yamt){
        xoffset+=xamt;
        yoffset+=yamt;
    }
    
    public float getxoffset(){
        return xoffset;
    }
    
    public void setxoffset(float xoffset){
        this.xoffset=xoffset;
    }
    
    public float getyoffset(){
        return yoffset;
    }
    
    public void setyoffset(float yoffset){
        this.yoffset=yoffset;
    }
}

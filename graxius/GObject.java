
package graxius;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class GObject {
    
    //Position
    double x;
    double y;
    
    //Attributes
    int direction;
    double speed;
    String name;
    int type;
    
    BufferedImage image;
    
    Rectangle bounds;
    
   
    public GObject(){
        
    }
    
    public GObject(double x, double y, int direction, double speed,int type){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.type = type;
        
    }
    
    
    public GObject(double x, double y, int direction, double speed){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        
    }
    
    public GObject(String name,double x, double y, int direction, double speed,int type){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.name = name;
        this.type = type;
    }
    
    public Rectangle getBounds(){
        bounds = new Rectangle((int)Math.round(x),(int)Math.round(y),image.getWidth(),image.getHeight());
        return bounds;
    }
    
    public BufferedImage getImage(){       
        return image;
    }
    
    
    //higher precision image drawing double double
    AffineTransform point;
    public void draw(Graphics2D g){
        point = new AffineTransform();
	point.translate(x,y);
	g.drawImage(image,point,null);
    }
    
    
    
    public void move(){
        double radians = Math.toRadians(this.direction);

        double hspeed =  ((this.speed) * Math.cos(radians));
        double vspeed =  ((this.speed) * Math.sin(radians));

        this.x += hspeed;
        this.y += vspeed;
    }
    
    
    //SETTERS
    public void setType(int type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
}

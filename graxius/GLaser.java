/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graxius;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class GLaser extends GObject{

    
    Rectangle laserBounds;
    
    
    public GLaser(double x, double y,int direction,double speed, String type){
        super(x,y,direction,speed);
        if(type.equals("ice_laser")){
            image = Graxius.imageMan.getImage("ice_laser");

        }else if(type.equals("shock_laser")){
            image = Graxius.imageMan.getImage("shock_laser");
        }
    }

//    public BufferedImage getUpgradedLaserImg(){
//        return shipObject.laserImg;
//    }

    
    //public Rectangle getLaserBounds(){
        //AffineTransform at = new AffineTransform();
	//at.translate(laserX,laserY);
        //Point2D.Double rofl = new Point2D.Double(laserX,laserY);
        //r; //http://docs.oracle.com/javase/6/docs/api/java/awt/geom/RectangularShape.html
    //}  
}

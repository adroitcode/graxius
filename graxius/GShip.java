
package graxius;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class GShip extends GObject {
    ArrayList laserList = new ArrayList();
    ArrayList spellList = new ArrayList<GSpell>();
    final int SPEED_MAX;
    final double SPEED_MIN;
    int life;
    int armor;
    int damage;
    int upgrade;
    
    String laser_type = "ice_laser";
    
    
    int potentialDirection;

    boolean accelerate = false;
    
    double friction = 0.01;
                

    
    public GShip(int x,int y,int direction,int speed,int life,int armor,int damage, int type){
        super(x,y,direction,speed,type);
        image = Graxius.imageMan.getImage("ice_ship");
        
        this.SPEED_MAX = speed;
        this.SPEED_MIN = 0.004;
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.type = type;
        potentialDirection = direction;
        changeFrame(direction);
        upgrade = 0;
    }
    
    public GShip(int x,int y,int direction,int speed,int life,int armor,int damage, int type, String laser_type){
        super(x,y,direction,speed,type);
        image = Graxius.imageMan.getImage("ice_ship");
        
        this.SPEED_MAX = speed;
        this.SPEED_MIN = 0.004;
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.type = type;
        potentialDirection = direction;
        changeFrame(direction);
        upgrade = 0;
        
        
        this.laser_type = laser_type;
        
    }
    public GShip(String name,int x,int y,int direction,int speed,int life,int armor,int damage, int type){
        super(name,x,y,direction,speed,type);
        image = Graxius.imageMan.getImage("ice_ship");
        
        this.SPEED_MAX = speed;
        this.SPEED_MIN = 0.004;
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.type = type;
        potentialDirection = direction;
        changeFrame(direction);
        upgrade = 0;
        setUpSpells();
    }
    
    
    
    
    
    private void setUpSpells(){
        GSpell spellOne = new GHeal();
        spellOne.setKeybind("1");
        spellList.add(spellOne);
    }
    
    public void fireLaser(){
        
        //x,y,direction,speed
        GLaser newLaser = new GLaser(x + 22,y,potentialDirection,15, laser_type);
        laserList.add(newLaser);
    }
    public void applyFriction(){
        if(!accelerate){
            if(speed > SPEED_MIN){
                speed -= friction;    
            }
        }
    }
    
    public int getDamage(){
        return this.damage;
    }
    public ArrayList<GLaser> getActiveLasers(){
        return laserList;
    }
    
    public void hit(int damageTaken){
        this.life -= damageTaken - (armor * 0.5);
        System.out.println("OUCH, took " + damageTaken + " damage");
        if(life <= 0){
            System.out.println("DEAD");
        }
    }
    
    @Override
    public void move(){
        applyFriction();
        double radians = Math.toRadians(direction);
        double hspeed =  ((speed) * Math.cos(radians));
	double vspeed =  ((speed) * Math.sin(radians));
      
	x += hspeed;
        y += vspeed;
        
        moveLasers();
        
    }
    
    
    
    
    
    public void moveLasers(){
        if(laserList.size() > 0){
            for(int x=0;x<laserList.size();x++){
                GLaser singleLaser = (GLaser) laserList.get(x);
                laserGarbageCollector(singleLaser,x);
                singleLaser.move();
            } 
        }
            
    }
    
    public void changeDirection(int passedDirection){
        if(passedDirection > 0){
            if(direction < 355){
                direction += passedDirection;
                potentialDirection += passedDirection;
            }
            else if(direction == 355){
                direction += passedDirection;
                direction = 0;
                potentialDirection += passedDirection;
                potentialDirection = 0;
            }
        }
        else if(passedDirection < 0){
            if(direction > 5){
                direction += passedDirection;
                potentialDirection += passedDirection;
            }
            else if(direction == 5){
                direction += passedDirection;
                direction = 360;
                potentialDirection += passedDirection;
                potentialDirection = 360;
            }
            else if(direction == 0){
                direction += passedDirection;
                direction = 355;
                potentialDirection += passedDirection;
                potentialDirection = 355;
            }
        }
        changeFrame(potentialDirection);
        
    }
    public void changePotentialDirection(int passedPotentialDirection){
        if(passedPotentialDirection > 0){
            if(potentialDirection < 355){
                potentialDirection += passedPotentialDirection;
            }
            else if(potentialDirection == 355){
                potentialDirection += passedPotentialDirection;
                potentialDirection = 0;
            }
        }
        else if(passedPotentialDirection < 0){
            if(potentialDirection > 5){
                potentialDirection += passedPotentialDirection;
            }
            else if(potentialDirection == 5){
                potentialDirection += passedPotentialDirection;
                potentialDirection = 360;
            }else if(potentialDirection == 0){
                
                potentialDirection += passedPotentialDirection;
                potentialDirection = 355;
            }
        }
        changeFrame(potentialDirection);
    }
    
    
    
    public void accelerate(){
        
        double accValue = 0.05;
        
        double decValue = 0.1;

        if(potentialDirection != direction){       
            changeFrame(potentialDirection);
            if(speed > 1){
                speed -= decValue;
                
            }
            else{
                direction = potentialDirection;
            }
        }
        else{
            changeFrame(direction);
            
            if(speed < SPEED_MAX){
                speed += accValue; 
            }
            else{
                //fullspeed
            }
        }
        
    }
    public void decelerate(){
        
    }
    
    
    
    public void wrapAroundScreen(){
        if(x < 0){
            x = 1000;
        }
        if(x > 1000){
            x = 0;
        }
        if(y > 800){
            y = 0;
        }
        if(y < 0){
            y = 800;
        }
    }
    
    
    
    
    public void changeFrame(int direction){
        int frameDiff = 360 - direction;
        int frame = (frameDiff / 5) - 1;
        if(frame > 71){
            frame = 71;
        }
        if(frame < 0){
            frame = 0;
        }
        image = Graxius.imageMan.getFrame("ship",frame);
        //image = Graxius.imageMan.rotate(image,direction);
    }
    
    
    
    
    public void laserGarbageCollector(GLaser singleLaser,int index){      
        if(singleLaser.x < 0 || singleLaser.y < 0 || singleLaser.x > 1000 || singleLaser.y > 800){
            laserList.remove(index);
            //System.out.println("delete laser");
        }
    }
    
    
    //Spells
    public class GHeal extends GSpell{
        public GHeal(){
            super();
        }
        
        public void activate(){
            life += 100;
            drawAnimation();
        }
        public void drawAnimation(){
            
        }
    }
}

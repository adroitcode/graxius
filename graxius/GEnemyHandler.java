
package graxius;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class GEnemyHandler {
    
    
    
    Timer enemyHandlerTimer;
    GShip enemyObject;
    GShip newEnemyObject;
    ArrayList actionList;
    private ArrayList enemyList;
    GShip shipObject;
    BufferedImage enemyImg;
    BufferedImage laserImg;
    int life;
    int speed;
    int armor;
    int damage;
    int type;
    int direction;
    int shipX;
    int shipY;
    
    int actionCounter = 0;
    
    public GEnemyHandler(GShip passedShipObject){
        shipObject = passedShipObject;
        enemyList = new ArrayList();
        actionList = new ArrayList();
        spawnEnemies();
        
    }
    
    
    
    public void update(){
        GShip enemy;
        for(int x=0;x<enemyList.size();x++){  
            enemy = (GShip) enemyList.get(x);
            performActions(enemy,x);
            enemy.move();
            enemy.wrapAroundScreen();
        }
            
    }
    
    public void performActions(GShip enemy, int index){
        switch(((Integer)actionList.get(index)).intValue()){
            case 1:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.fireLaser();
                enemy.changeDirection(5);
                break;
            case 2:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.changeDirection(-5);
                break;               
            case 3:
                enemy.changePotentialDirection(5);
                enemy.fireLaser();
                break;
            case 4:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.changePotentialDirection(-5);
                enemy.fireLaser();
                break;
            case 5:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.changePotentialDirection(-5);
                enemy.fireLaser();
                break;
            case 6:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.changePotentialDirection(-5);
                enemy.fireLaser();
                break;
            case 7:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.fireLaser();
                break;
            case 8:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.fireLaser();      
                break;
            case 9:
                enemy.accelerate = true;
                enemy.accelerate();
                enemy.fireLaser();
                break;  
            default:
                break;
        }
        
        
        
        actionCounter++;
        if(actionCounter == 1000){
            System.out.println("Changing actions");
            actionCounter = 0;
            Collections.shuffle(actionList);
        }
    }
    
    
    public void spawnEnemies(){
        for(int x=0;x<4;x++){
            //(BufferedImage enemyImg, BufferedImage laserImg, int passedX, int passedY, int passedLife,int passedSpeed,int passedArmor,int passedDamage,
            //int passedType,int passedDirection,gBuffImg passedBuffImgObject,gSpaceship passedShipObject)
            int shipInUseNumber = shipObject.type;          
            if(x != shipInUseNumber){
                shipSelect(x);
            } 
            System.out.println("spawn lolol");
        }
        
        for (int i = 0; i <= 10; i++) { 
            actionList.add(i);
        }
    }
    
    
    public ArrayList getEnemyList(){
        return this.enemyList;
    }
    
    
    
    public GShip shipSelect(int shipId){
        if(shipId == 0){
            enemyList.add(shipOneSelect());
        }
        else if(shipId == 1){
            enemyList.add(shipTwoSelect());
        }
        else if(shipId == 2){
            enemyList.add(shipThreeSelect());
        }
        else if(shipId == 3){
            enemyList.add(shipFourSelect());
        }
    
        return null;
    }
    
    public GShip shipOneSelect(){ // fire
        //create all the stats for ship 1
        shipX = 25;
        shipY = 25;
        direction = 75;
        speed = 5;
        life = 100;
        armor = 25;
        damage = 10;
        type = 1;
        
        
        return enemyObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public GShip shipTwoSelect(){ // ice
        //create all the stats for ship 1
        life = 75;
        speed = 10;
        armor = 15;
        damage = 15;
        type = 2;
        direction = 0;
        shipX = 25;
        shipY = 25;
        return enemyObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public GShip shipThreeSelect(){ //lighting
        //create all the stats for ship 1
        life = 100;
        speed = 8;
        armor = 25;         
        type = 3;
        direction = 320;
        shipX = 20;
        shipY = 700;
        return enemyObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public GShip shipFourSelect(){ //earth
        //create all the stats for ship 1
        shipX = 25;
        shipY = 25;
        direction = 20;
        speed = 8;
        life = 100;   
        armor = 25;
        damage = 10;
        type = 3;
        
        
        return enemyObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
}

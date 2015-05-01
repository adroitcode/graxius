
package graxius;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GPlay extends JPanel{
    
    Timer gameLoopTimer;
    GShip player;
    GEnemyHandler enemyHandlerObject;
    KeyboardInput keyboard;
    GShip [] enemyArray = new GShip[3];
    boolean play;
    
    
    public GPlay(GShip passedShipObject){
        //initiate objects
        keyboard = new KeyboardInput();
        player = passedShipObject;
        //(BufferedImage passedShipImg, BufferedImage passedLaserImg,int passedX, int passedY, int passedLife,int passedSpeed,int passedArmor,int passedDamage, int passedType,int passedDirection,gBuffImg passedBuffImgObject,gSpaceship passedShipObject)
        enemyHandlerObject = new GEnemyHandler(player);
        //print what ship I am using
        System.out.println("You are using proto-type: " + player.type + ".");
        
        //add to frame
        setFocusable(true);
        addKeyListener( keyboard );
        addKeyListener ( new KeyListener() );
        play = true;
        //start game timer
        gameLoopTimer = new Timer(10, gameLoop); //calls .epaint every 5 milliseconds;
        gameLoopTimer.start();
    }
    ActionListener gameLoop = new ActionListener() { //every 5 miliseconds
        public void actionPerformed(ActionEvent evt) {
            
            if(play){
                player.move();
                player.wrapAroundScreen();
                enemyHandlerObject.update();
                checkCollisions();
            }
            keyboard.poll();
            processInput();
            //moveLasers();
            repaint();
        }
    };
    
    public void paint(Graphics g) {
        Graphics2D drawThis = (Graphics2D) g;
        //draw background
        drawThis.drawImage(Graxius.imageMan.getImage("bg"),0,0,1000,800,null);
        //draw ship
        player.draw(drawThis);
        //draw lasers
        if(player.laserList.size() > 0){
            GLaser singleLaser;
            for(int x=0;x<player.laserList.size();x++){
                singleLaser = (GLaser) player.laserList.get(x);
                //draw
                singleLaser.draw(drawThis);
            }
        }     
        //draw enemies
        try{
            GShip singleEnemy;
            GLaser laser;
            for(int x=0;x<enemyHandlerObject.getEnemyList().size();x++){
                singleEnemy = (GShip) enemyHandlerObject.getEnemyList().get(x);
                singleEnemy.draw(drawThis);
                
                for(int i = 0;i<singleEnemy.laserList.size();i++){
                    laser = (GLaser) singleEnemy.laserList.get(i);
                    laser.draw(drawThis);
                }
            }
        }catch(Exception e){}
        
        //drawThis.drawImage(shipObject.shipImg,shipObject.point.,null);
    }
    
    //higher precision image drawing double double
//    public void draw(Graphics2D g, Image image, double x, double y){
//        AffineTransform point = new AffineTransform();
//	point.translate(x,y);
//	g.drawImage(image,point,null);
//    }
    
    
    public void checkCollisions(){
        GShip enemy;
        GLaser laser;
        //Check for player's laser collisions
        for(int x=0;x<player.laserList.size();x++){
            laser = (GLaser)player.laserList.get(x);
            
            for(int i=0;i<enemyHandlerObject.getEnemyList().size();i++){
                enemy = (GShip) enemyHandlerObject.getEnemyList().get(i);
                if(laser.getBounds().intersects(enemy.getBounds())){
                    enemy.hit(player.getDamage());
                }
            }        
        }
        
        //Check enemy laser collisions
        for(int x=0;x<enemyHandlerObject.getEnemyList().size();x++){
            enemy = (GShip) enemyHandlerObject.getEnemyList().get(x);
            for(int i=0;i<enemy.getActiveLasers().size();i++){
                laser = enemy.getActiveLasers().get(i);
                for(int y=0;y<enemyHandlerObject.getEnemyList().size();y++){
                    if(laser.getBounds().intersects(((GShip)enemyHandlerObject.getEnemyList().get(y)).getBounds())){
                        ((GShip)enemyHandlerObject.getEnemyList().get(y)).hit(enemy.getDamage());
                    }
                }               
                if(laser.getBounds().intersects(player.getBounds())){
                    player.hit(enemy.getDamage());
                }
            }
        }
    }
    
    
    protected void processInput() {
        if( keyboard.keyDown( KeyEvent.VK_UP)){
            play = true;
            player.accelerate = true;
            player.accelerate();
        }
        if( keyboard.keyDown( KeyEvent.VK_SPACE)){ //Once
            if(play){
                player.fireLaser();
            }
            
        }
        if( keyboard.keyDownOnce( KeyEvent.VK_UP)){
            //shipObject.movingDirection = shipObject.potentialDirection;
        }
        if( keyboard.keyDown( KeyEvent.VK_DOWN)){
            player.decelerate();
        }
        if( keyboard.keyDown( KeyEvent.VK_RIGHT ) ) {
            if(player.accelerate){
                player.changeDirection(5);
            }
            else if(!player.accelerate){
                player.changePotentialDirection(5);
            }  
        }
        if( keyboard.keyDown( KeyEvent.VK_LEFT ) ) {
            try{
                if(player.accelerate){
                player.changeDirection(-5);
            }
            else if(!player.accelerate){
                player.changePotentialDirection(-5);
            } 
            }catch(Exception e){System.out.println(e.getMessage());}
                     
        }
        
    } 
    
    private class KeyListener extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_UP){
                player.accelerate = false;
            }
	}
	public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ENTER){
                player.accelerate = false;
            }
	}
    }
    
}

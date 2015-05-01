
package graxius;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class GSelect extends JPanel {
    //Constructor - first method called in a class
    JFrame playFrame,selectionFrame;
    GShip shipObject;
    JButton playButton;
    JButton exitButton;
    Rectangle selectOneBounds;
    Rectangle selectTwoBounds;
    Rectangle selectThreeBounds;
    Rectangle selectFourBounds;
    BufferedImage shipImg;
    BufferedImage laserImg;
    int life;
    int speed;
    int armor;
    int damage;
    int type;
    int direction;
    int shipX;
    int shipY;

    public GSelect(JFrame passedSelectionFrame){
        selectionFrame = passedSelectionFrame;
        initSelectionBoxes();
        ClickListener btnClick = new ClickListener();
        
        playButton = new JButton(" Play ");
        exitButton = new JButton(" Exit ");
        
        playButton.addActionListener(btnClick);
        exitButton.addActionListener(btnClick);
        
        add(playButton);
        add(exitButton);
        addMouseListener(new mouseEvent());
        
        
        shipTwoSelect();
        startGame();
        
        selectionFrame.setVisible(false);
        //playButton.doClick();

    }
    public void paint(Graphics g) {
        Graphics2D drawThis = (Graphics2D) g;
        //background
        drawThis.drawImage(Graxius.imageMan.getImage("bg"),0,0,1000,800,null);
        //draw first spaceship selection box
        drawThis.drawImage(Graxius.imageMan.getImage("ship_select_1"),25,0,462,362,null);
        //draw two spaceship selection box
        drawThis.drawImage(Graxius.imageMan.getImage("ship_select_2"),512,0,462,362,null);
        //draw three spaceship selection box
        drawThis.drawImage(Graxius.imageMan.getImage("ship_select_3"),25,400,462,362,null);
        //
        drawThis.drawImage(Graxius.imageMan.getImage("ship_select_4"),512,400,462,362,null);
    }
    public void initSelectionBoxes(){
        //Rectangle(int x, int y, int width, int height) 
        //Constructs a new Rectangle whose top-left corner is specified as (x, y) 
        //and whose width and height are specified by the arguments of the same name.
        selectOneBounds = new Rectangle(25,25,462,362);
        selectTwoBounds = new Rectangle(512,25,462,362);
        selectThreeBounds = new Rectangle(25,412,462,362);
        selectFourBounds = new Rectangle(512,412,462,362);  //362 height // 462 //211 // 342
    }
    public void startGame(){
   
        selectionFrame.setVisible(false);

        //selectionFrame.add(new GPlay(shipObject));
        
        
        playFrame = new JFrame();
        playFrame.setSize(1000,600);
        playFrame.setTitle("Graxius");
        playFrame.setLocationRelativeTo(null);
        playFrame.setResizable(false);
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playFrame.add(new GPlay(shipObject));
        playFrame.show();
        
        
    }
    public class ClickListener implements ActionListener { // see what action was performed
      // detects button press  
        public void actionPerformed(ActionEvent e) {
            //Check to see what sent the Action event
            if (e.getSource() == playButton) {
                if(shipObject != null){
                    startGame();
                }
                else{
                    System.out.println("Select a spaceship");
                }                     
            }
            else if (e.getSource() == exitButton) {
                System.exit(0);
            }
        }
    } 
    //mouse event
    private class mouseEvent implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            
        }
        public void mousePressed(MouseEvent e) {
            Rectangle mouseBounds;
            int mouseX = e.getX();
            int mouseY = e.getY();
            mouseBounds = new Rectangle(mouseX,mouseY,1,1);
            if(mouseBounds.intersects(selectOneBounds)){
                shipOneSelect();
                System.out.println("You have selected spaceship 1");
            }
            else if(mouseBounds.intersects(selectTwoBounds)){
                shipTwoSelect();
                System.out.println("You have selected spaceship 2");
            }
            else if(mouseBounds.intersects(selectThreeBounds)){
                shipThreeSelect();
                System.out.println("You have selected spaceship 3");
            }
            else if(mouseBounds.intersects(selectFourBounds)){
                shipFourSelect();
            }
            else{
                
            }
        }
        public void mouseReleased(MouseEvent e) {
        
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
    public void shipOneSelect(){ // fire
        //create all the stats for ship 1
        life = 100;
        speed = 5;
        armor = 25;
        damage = 10;
        type = 1;
        direction = 0;
        shipX = 25;
        shipY = 25;
        shipObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public void shipTwoSelect(){ // ice
        //create all the stats for ship 1
        
        life = 75;
        speed = 10;
        armor = 15;
        damage = 15;
        type = 2;
        direction = 0;
        shipX = 25;
        shipY = 25;
        
        
        
        shipObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public void shipThreeSelect(){ //lighting
        //create all the stats for ship 1
        life = 100;
        speed = 10;
        armor = 25;         
        type = 3;
        direction = 320;
        shipX = 20;
        shipY = 700;
        shipObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
    public void shipFourSelect(){ //earth
        //create all the stats for ship 1
        life = 100;
        speed = 8;
        armor = 25;
        damage = 10;
        type = 3;
        direction = 0;
        shipX = 25;
        shipY = 25;
        shipObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type);
    }
}

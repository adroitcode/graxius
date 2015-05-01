
package graxius;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;


public class Graxius {
    
    private JButton startButton, exitButton;
    private Box southBox;
    JFrame introFrame,selectionFrame;
    JPanel introPanel,buttonPanel;
    static GImageManager imageMan;

    
    
    public Graxius(){
        initImages();
        //create listener object
        ClickListener btnClick = new ClickListener();
        //Create Intro JFrame
        introFrame = new JFrame();
        introFrame.setTitle("Graxius");
        introFrame.setSize(1000,600);
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setLocationRelativeTo(null);
        introFrame.setResizable(false);
        //Create Intro Panel
        
        introPanel = new JPanel(); 
        //add the class gIntroPanelPaint to the Jpanel so the into image is painted onto the JPanel, which is then
        //added to the JFrame introFrame
        introPanel.setLayout(new BorderLayout());
        
        
        
        
        int life;
        int speed;
        int armor;
        int damage;
        int type;
        int direction;
        int shipX;
        int shipY;
        
        life = 75;
        speed = 10;
        armor = 15;
        damage = 15;
        type = 2;
        direction = 0;
        shipX = 25;
        shipY = 25;
        
        

        GShip shipObject = new GShip(shipX,shipY,direction,speed,life,armor,damage,type,"shock_laser");
        
        introPanel.add(new GPlay(shipObject));
        //introPanel.add(new GIntroPanelPaint());
        
        
        buttonPanel = new JPanel();
        //create the buttons
        // Start Button
        startButton = new JButton("                    Start                    ");
        startButton.addActionListener(btnClick);
        
        // Exit Button
        exitButton = new JButton("                     Exit                     ");
        exitButton.addActionListener(btnClick);
        // add to south box
        southBox = Box.createVerticalBox();
        //southBox.add(Box.createVerticalStrut(250));
        southBox.add(startButton);
        southBox.add(Box.createVerticalStrut(40));
        southBox.add(exitButton);
        // add to south panel
        //buttonPanel.add(startButton);
        //buttonPanel.add(exitButton);
        //add button panel to souther area
        //introPanel.add(buttonPanel, BorderLayout.SOUTH);
        //Add panel object to Frame 
        introFrame.add(introPanel);
        introFrame.setVisible(true);
    }
    
    public void initImages(){
        imageMan = new GImageManager();
        imageMan.addImage("ship_select_1",getClass().getResource("/Images/shipSelectLayout.png"));
        imageMan.addImage("ship_select_2",getClass().getResource("/Images/shipSelectLayout.png"));    
        imageMan.addImage("ship_select_3",getClass().getResource("/Images/shipSelectLayout.png"));    
        imageMan.addImage("ship_select_4",getClass().getResource("/Images/shipSelectLayout.png"));      
        imageMan.addImage("fire_laser",getClass().getResource("/Images/fireLaser.png"));      
        imageMan.addImage("shock_laser",getClass().getResource("/Images/elec.png"));  
        imageMan.addImage("ice_laser",getClass().getResource("/Images/ballOfIce.png")); 

        imageMan.addImage("bg",getClass().getResource("/Images/graxiusBG.png"));
        imageMan.addImage("ice_ship",getClass().getResource("/Images/transShip.gif"));
        imageMan.addGif("ship",getClass().getResource("/Images/transShip.gif"));
    }
    
    
    public static void main(String[] args) {
        new Graxius();
        
    }
    
    
    public class ClickListener implements ActionListener { 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                // start game
                introFrame.setVisible(false); 
                // create the gameFrame   
                selectionFrame = new JFrame();
                selectionFrame.setTitle("Graxius");
                selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                selectionFrame.setResizable(false);
                selectionFrame.setSize(1000, 600);
                selectionFrame.setLocationRelativeTo(null);
                selectionFrame.setVisible(true);
                selectionFrame.add(new GSelect(selectionFrame));
                
            }
            else if (e.getSource() == exitButton) {
                // close game
                System.exit(0);
                
            }
        }
  }
}

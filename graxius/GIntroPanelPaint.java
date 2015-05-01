
package graxius;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GIntroPanelPaint extends JPanel {
    // Global Variables
    Image gameLogoImg;
    ImageIcon gameLogo = new ImageIcon("C:/javaImages/graxiusintro.jpg");
    // public playZone Constructor
    public GIntroPanelPaint() {    // constructor
        gameLogoImg = gameLogo.getImage();
    }
    // action re-paint
    public void actionPerformed(ActionEvent e) {
		repaint();
    }
    public void paint(Graphics g) {
        // declare graphics instance
        Graphics2D drawThis = (Graphics2D) g;
        // draw stuff
        
        drawThis.drawImage(gameLogoImg, 0, 0, null);
    }
}
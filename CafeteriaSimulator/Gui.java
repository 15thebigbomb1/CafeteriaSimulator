/**
 * Write a description of class guiTen here.
 *
 * @author Gabriel Gibson
 * @version 14/07/25
 */
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Gui extends JFrame implements ActionListener
{
  JMenuBar menuBar;
  JMenu menu;
  JMenuItem menuItem;
  JButton myButton;
  GridBagConstraints gbc = new GridBagConstraints();
  public Gui() {
    setTitle("Cafeteria Simulator");
    menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);  
    
    menu = new JMenu("Options");
    menuBar.add(menu);
    
    
    menuItem = new JMenuItem("Exit");
    menuItem.addActionListener(this);
    menu.add(menuItem);
    
    myButton = new JButton();
    
    
    myButton.setText("Press me");
    myButton.setPreferredSize(new Dimension(150,50));
    myButton.setOpaque(true);
    
    
    
    
    
    
    this.setLayout(new GridBagLayout());
    gbc.gridx = 0;
    gbc.gridy = 2;
    this.add(myButton, gbc);
    this.getContentPane().setPreferredSize(new Dimension(1900,1080));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    //defing the size of the window and that it will exit when closed
    this.pack();
    //packing eveything
    this.toFront();
    this.setVisible(true);
    //putting the window to the fronting and defining its viasibility to true
  }
  
  public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      // defining the name of the menu option pressed as string.
      System.out.println(cmd);
      //printing it.
      switch(cmd) {
          case "Exit":
              System.exit(0);
              break;
      }
  }
  
  public void paint (Graphics g ) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g; 
        // int rectWidth = releasedX - pressedX;
        // int rectHeight = releasedY - pressedY;
        // System.out.println(rectWidth);
        // System.out.println(rectHeight);        
        // g2.drawRect(pressedX,pressedY,rectWidth,rectHeight);
    }
  
}
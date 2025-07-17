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
  JPanel northWPanel, northPanel, northEPanel, centerWPanel, centerPanel, centerEPanel,bottomWPanel,bottomPanel,bottomEPanel;
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
    
      
    this.setLayout(new GridBagLayout());
    northWPanel = new JPanel(); northPanel = new JPanel(); northEPanel = new JPanel(); centerWPanel = new JPanel(); centerPanel = new JPanel(); centerEPanel = new JPanel(); bottomWPanel = new JPanel(); bottomPanel = new JPanel(); bottomEPanel = new JPanel();
    
    gbc.anchor = gbc.NORTHWEST;
    northWPanel.setPreferredSize(new Dimension(100,100));
    northW
    this.add(northWPanel,gbc);
    gbc.anchor = gbc.NORTH;
    this.add(northPanel,gbc);
    gbc.anchor = gbc.NORTHEAST;
    this.add(northEPanel,gbc);
    gbc.anchor = gbc.EAST;
    this.add(centerEPanel,gbc);
    
    myButton = new JButton();
    myButton.setText("Press me");
    myButton.setPreferredSize(new Dimension(150,50));
    myButton.setOpaque(true);
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = gbc.NORTHEAST;
    this.add(myButton,gbc);
    
    this.getContentPane().setPreferredSize(new Dimension(900,900));
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
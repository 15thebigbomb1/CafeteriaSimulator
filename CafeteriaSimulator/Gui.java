/**
 * Write a description of class guiTen here.
 *
 * @author Gabriel Gibson
 * @version 14/07/25
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Scanner;
import java.awt.event.*;
import java.util.ArrayList;

public class Gui extends JFrame implements ActionListener,MouseListener
{
    JMenuBar menuBar;
    //Menubar object that holds the menu.
    JMenu menu;
    //Menu obejct that holds the menu item.
    JMenuItem menuItem;
    //Menu item found in the menu.
    JPanel startingPanel;
    JPanel mainPanel;
    JPanel deletePanel;

    //Menu Panel that holds the background colour and canvas.
    Canvas startingGraphic;
    Canvas mainGraphic;
    //Canvas that displays the drawn properties like the drawn buttons and -
    //- images.
    ImageIcon whsLogo = new ImageIcon("whsLogo.png");
    //The url for the image that is displayed on the canvas.
    Color panelColor = new Color(135, 224, 144);
    //Defines the colour of the panel, which will change when going to a diffrent-
    //-page.
    //Menu objects.

    public int windowWidth = 450;
    public int windowHeight = 450;
    //Window variables for size.

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    //Tools for finding screen dimensions
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    //Screen size
    int drawMenuValue = 1;
    
    private Cafeteria cafetria = new Cafeteria();
    private ArrayList<Float> staffAverageList = cafetria.staffAverageList;
    private ArrayList<Float> studentAverageList = cafetria.studentAverageList;
    private ArrayList<Float> averageList = cafetria.averageList;
    
    
    public Gui() {
        setTitle("Cafeteria Simulator");
        menuBar = new JMenuBar();
        //Defines menubar in method.
        menuBar.setBackground(Color.GRAY);
        //Changes the colour of the menubar to gray.
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        //Removes a white border line that is there by default between the bar and canvas to make the windows-
        //-colours blend more nicely.
        this.setJMenuBar(menuBar);  
        //adds menubar to default panel.
        menu = new JMenu("Options");
        // Defines the option menu in constucter
        menu.setForeground(Color.WHITE);
        //Defines the colour of the menus text.
        menuItem = new JMenuItem("Exit");
        //defines menuItem in method.
        menuItem.addActionListener(this);
        //Adds a listener so when its pressed it'll run a method.
        menuBar.add(menu);
        menu.add(menuItem);
        //Adds the menu to the menubar and adds the menuitem to the menu.
        
        startingPanel = new JPanel();
        mainPanel = new JPanel();
        //Defines the panels that will be used in the gui.
        startingPanel.setBackground(panelColor);
        mainPanel.setBackground(new Color(135, 224, 144));
        //Changes the colour to the color chosen for the background.
        startingGraphic = new Canvas();
        mainGraphic = new Canvas();
        //Defines the canvas that will be used in the gui.
        startingPanel.add(startingGraphic); 
        mainPanel.add(mainGraphic);
        //Adds the canvases to the panels.

        
        // Reupdates the startingPanel to update the colour of the background.
        this.getContentPane().setPreferredSize(new Dimension(windowWidth,windowHeight));
        //Defines the size of the window at 450x450.
        this.setResizable(false);
        //Makes it so the user cant resize the window.
        this.setLocation((screenWidth/2 - windowWidth/2),(screenHeight/2 - windowHeight/2));
        //Sets the location of the window on the screen by using a formula involving your screens-
        //-aspect ratio to make sure its in the middle on any screen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //defing that it will exit when closed
        addMouseListener(this);
        //putting the window to the fronting and defining its viasibility to true\
        startingPanel();
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // Defining the name of the menu option pressed as string.
        System.out.println(cmd);
        //Printing what the menu option is called.
        switch(cmd) {
            case "Exit":
                System.exit(0);
                break;
        }
    }

    public void ChooseMenu(int chooseMenuValue) {
        switch (chooseMenuValue) {
            case 1:
                System.out.println("Starting menu chosen");
                repaint();
                drawMenuValue = 1;
                startingPanel();
                break;
            case 2:
                System.out.println("Second menu chosen");
                mainPanel();
                drawMenuValue = 2;
                repaint();
                break;
            case 3:
                System.out.println("Option menu chosen:");
                drawMenuValue = 3;
                repaint();
                break;
        }
    }

    public void startingPanel() {
        if (deletePanel == null) {
            System.out.println("first panel, nothing to delete");
        } else {
            this.getContentPane().remove(deletePanel);
        }
        this.getContentPane().add(startingPanel);
        this.pack();
        //packing eveything
        this.toFront();
        //brings everything to the front
        this.setVisible(true);
        deletePanel = startingPanel;
    }
    
    public void mainPanel() {
        this.getContentPane().remove(deletePanel);
        this.getContentPane().add(mainPanel);
        this.pack();
        this.toFront();
        deletePanel = mainPanel;
    }
    
    public void paint (Graphics g ) {
        super.paint(g);
        //Defines the painting method
        Graphics2D g2 = (Graphics2D) g; 
        //Defines the 2d paint methods for circles/rectangles etc.

        switch (drawMenuValue) {

            case 1:
                whsLogo.paintIcon(this,g,157,100);
                //Paints the whs logo with its x and y corrodinates.
                g2.setFont(new Font("Franklin Gothic Demi",Font.BOLD,35));
                //Sets font.
                g2.setColor(new Color(255,238,140));
                //Sets colour.
                g2.drawString("Wellington High",97,270);
                g2.drawString("Cafeteria Simulator",71,300);
                //Paints the text onto the canvas.
                g2.setColor(Color.WHITE);
                //Sets colour.
                g2.fillRect(200,320,65,25);
                //Paints a colour filled rectangle and defines size and corrodinates.
                g2.setColor(Color.BLACK);
                //Sets Colour.
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(200,320,65,25);
                //Draws another rectangle over the filled rectangle in a diffrent colour-
                //-to create a border for it.
                g2.setFont(new Font("Franklin Gothic Demi",Font.BOLD,25));
                //Sets font.
                g2.setColor(Color.BLACK);
                //Sets colour.
                g2.drawString("start",203,340);
                //Draws the text found in the rectancle to make it look like a-
                //-button.
                break;
            case 2:
                g2.setColor(new Color(255,238,140));
                g2.setFont(new Font("Franklin Gothic Demi",Font.BOLD,35));
                g2.drawString("Hello World!",97,270);
                g2.setColor(Color.WHITE);
                //Sets colour.
                g2.fillRect(200,340,65,25);
                //Paints a colour filled rectangle and defines size and corrodinates.
                g2.setColor(Color.BLACK);
                //Sets Colour.
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(200,340,65,25);
                //Draws another rectangle over the filled rectangle in a diffrent colour-
                //-to create a border for it.
                g2.setFont(new Font("Franklin Gothic Demi",Font.BOLD,25));
                //Sets font.
                g2.setColor(Color.BLACK);
                //Sets colour.
                g2.drawString("Back",203,360);
                  String myString = String.valueOf(staffAverageList.get(0));
                  g2.drawString("average for staff at 1 minute is "+myString,150,270);
                break;
        }
    }
    public void mouseExited(MouseEvent e) {};

    public void mouseEntered(MouseEvent e) {};

    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        //Defines the mouses x corrodinate.
        int mouseY = e.getY();
        //Defines the mouses Y corrodinate.
        switch (drawMenuValue) {
            case 1: 
                if (mouseX >= 200 && mouseX <= 265 && mouseY >= 320 && mouseY <= 345) {
                    System.out.println("button Pressed");
                    ChooseMenu(2);
                }
                break;
            case 2:
                if (mouseX >= 200 && mouseX <= 265 && mouseY >= 340 && mouseY <= 365) {
                    System.out.println("button Pressed");
                    ChooseMenu(1);
                }
                break;
        }
    }

    public void mousePressed(MouseEvent e) {};

    public void mouseClicked(MouseEvent e) {}
}
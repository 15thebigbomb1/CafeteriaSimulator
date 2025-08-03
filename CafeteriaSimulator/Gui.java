 

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
    JMenuItem menuItemTwo;
    //Menu item found in the menu.
    JTextField textField;
    //Text inputs found in menu that the user uses to input how long the simulator will run for.

    JPanel startingPanel;
    JPanel mainPanel;
    JPanel settingsPanel;
    JPanel inputPanel;
    JPanel averageDisplayedPanel;
    //All panels that are used in my gui.
    JPanel deletePanel;
    //Used to define the panel that will be deleted before running the new panel.
    Canvas startingGraphic;
    Canvas mainGraphic;
    Canvas settingsGraphic;
    Canvas inputGraphic;
    Canvas averageGraphic;
    //Canvas that displays the drawn properties like the drawn buttons and-
    //-images.
    String fontChoice = "Franklin Gothic Demi";
    //Font that will be used throught my project.
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
    int lastMenuValue = 1;
    //value used in switch statement when choosing what to draw for what panel.

    private Cafeteria cafeteria = new Cafeteria();
    private ArrayList<Float> staffAverageList = cafeteria.staffAverageList;
    private ArrayList<Float> studentAverageList = cafeteria.studentAverageList;
    private ArrayList<Float> averageList = cafeteria.averageList;
    //defines Cafeteria class and the cafeterias average array variables.
    int getAverageFromList = 0;
    // int value that will be used when getting the averages from the lists.

    boolean drawStringError = false;
    //If set to true will draw error text on input panel about using a number.
    boolean drawNumberError = false;
    //If set to true will draw error text on input panel about having a number between 1-60.
    
    boolean priorityQueueSetting = true;
    boolean randomnessSetting = false;
    public Gui() {
        setTitle("Cafeteria Simulator");
        cafeteria.DefineCSVFile();
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
        //Defines menuItem in method.
        menuItem.addActionListener(this);
        //Adds a listener so when its pressed it'll run a method..
        menuItemTwo = new JMenuItem("Last page");
        //Defines second menuitem in method.
        menuItemTwo.addActionListener(this);
        //Adds a listenter so when its pressed it'll run a method.
        menuBar.add(menu);
        menu.add(menuItem);
        menu.add(menuItemTwo);
        //Adds the menu to the menubar and adds the menuitems to the menu.

        textField = new JTextField();
        //defines in method
        textField.setBounds(175, 175, 100, 60);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 33));
        textField.addActionListener(this);
        //First line defines size of the text field, second line defines alligment of text and-
        //-third line defines font used when the user types in text

        startingPanel = new JPanel();
        mainPanel = new JPanel();
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        averageDisplayedPanel = new JPanel();
        
        //Defines the panels that will be used in the gui.
        startingPanel.setBackground(panelColor);
        mainPanel.setBackground(new Color(135, 224, 144));
        settingsPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(new Color(207,226,243));
        averageDisplayedPanel.setBackground(new Color(207,226,243));
        //Changes the colour to the color chosen for the background.
        startingGraphic = new Canvas();
        mainGraphic = new Canvas();
        inputGraphic = new Canvas();
        averageGraphic = new Canvas();
        //Defines the canvas that will be used in the gui.
        startingPanel.add(startingGraphic); 
        mainPanel.add(mainGraphic);
        inputPanel.add(inputGraphic);
        averageDisplayedPanel.add(averageGraphic);
        //Adds the canvases to the panels.
        inputPanel.add(textField);

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
            case "Last page":
                ChooseMenu(lastMenuValue);
                drawStringError = false;
                drawNumberError = false;
                break;

        }
        //switch statement for menu items
        
        if (e.getSource() == textField) {
            String textFieldInput = textField.getText().trim();
            //Gets the number from the textfield while getting rid of spaces added by the user.
            if (textFieldInput.matches("\\d+")) {
                int cafeteriaRunNumber = Integer.parseInt(textFieldInput);
                //Deines the string as an int.
                if (cafeteriaRunNumber >= 1 && cafeteriaRunNumber <= 60) {
                    cafeteria.RunCafeteria(cafeteriaRunNumber);
                    //Runs the cafeteria simulator for how long the user has inputed.
                    drawNumberError = false;
                    drawStringError = false;
                    
                    ChooseMenu(5);
                    //resetting the error text
                } else {
                    drawNumberError = true;
                    repaint();
                    //Defines a boolean as true that when the cavas gets repainted will allow-
                    //-error text to draw about the number not being between 1-60.
                }
                //will run cafeteria simulator unless 
            } else {
                drawStringError = true;
                repaint();
                //Defines a boolean as true that when  the cavas gets repainted will allow-
                //-error text to draw that will be how it has to be a number.
            }
        }
        //Code  that runs when the user presses enter on the keyboard        
    }

    public void ChooseMenu(int chooseMenuValue) {
        switch (chooseMenuValue) {
            case 1:
                System.out.println("Starting menu chosen");
                drawMenuValue = 1;
                //chooses 1 in a switch statement in the paint class to draw everything for the-
                //-starting menu.
                repaint();
                //repaints the canvas.
                startingPanel();
                //runs starting panel method.
                break;
            case 2:
                System.out.println("Main menu chosen");
                drawMenuValue = 2;
                //chooses 2 in a switch statement in the paint class to draw everything for the-
                //-main menu.
                repaint();
                //repaints the canvas.
                mainPanel();
                //runs main menu panel method.
                break;
            case 3:
                System.out.println("settings menu chosen");
                drawMenuValue = 3;
                repaint();
                //repaints the canvas.
                settingsPanel();
                break;
            case 4:
                System.out.println("Input menu chosen");
                drawMenuValue = 4;
                repaint();
                inputPanel();
                break;
            case 5:
                System.out.println("Average menu chosen");
                drawMenuValue = 5;
                repaint();
                averageDisplayedPanel();
        }
        //Switch statement that chooses the menu by running one of the three menu methods, and-
        //-repainting for the new panel
    }
    //Method class for choosing which menu
    public void startingPanel() {
        if (deletePanel == null) {
            System.out.println("first panel, nothing to delete");
        } else {
            this.getContentPane().remove(deletePanel);
            //Removes previous panel.
        }
        //If statement that runs at the start of the project to check if this is the first panel-
        //and therefore there is nothing to delete.
        this.getContentPane().add(startingPanel);
        //Adds the new panel. 
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.
        
        this.setVisible(true);
        //sets it visible
        deletePanel = startingPanel;
        //defines the panel to delete next time
        lastMenuValue = 1;
        //defines the last menu value in switch statement
    }

    public void mainPanel() {
        this.getContentPane().remove(deletePanel);
        //Removes previous panel.
        this.getContentPane().add(mainPanel);
        //Adds the new panel. 
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.
        
        deletePanel = mainPanel;
        //defines the panel to delete next time
        lastMenuValue = 1;
        //defines the last menu value in switch statement
    }
    
    public void settingsPanel() {
        this.getContentPane().remove(deletePanel);
        //Removes previous panel.
        this.getContentPane().add(settingsPanel);
        //Adds the new panel. 
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.
        
        deletePanel = settingsPanel;
        //defines the panel to delete next time
        lastMenuValue = 2;
        //defines the last menu value in switch statement
    }

    public void inputPanel() {
        this.getContentPane().remove(deletePanel);
        //Removes previous panel.
        this.getContentPane().add(inputPanel);
        //Adds the new panel. 
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.

        deletePanel = inputPanel;
        //defines the panel to delete next time
        lastMenuValue = 2;
        //defines the last menu value in switch statement
    }
    
    public void averageDisplayedPanel() {
        this.getContentPane().remove(deletePanel);
        //Removes previous panel.
        this.getContentPane().add(averageDisplayedPanel);
        //Adds the new panel. 
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.
        
        deletePanel = averageDisplayedPanel;
        //defines the panel to delete next time
        lastMenuValue = 4;
        //defines the last menu value in switch statement
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
                g2.setFont(new Font(fontChoice,Font.BOLD,35));
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
                g2.setFont(new Font(fontChoice,Font.BOLD,25));
                //Sets font.
                g2.setColor(Color.BLACK);
                //Sets colour.
                g2.drawString("start",205,340);
                //Draws the text found in the rectancle to make it look like a-
                //-button.
                break;
            case 2:
                whsLogo.paintIcon(this,g,157,100);
                //Paints the whs logo with its x and y corrodinates.
                g2.setColor(Color.WHITE);
                g2.fillRect(62,262,335,40);
                g2.setStroke(new BasicStroke(4));
                g2.setColor(Color.BLACK);
                g2.drawRect(62,262,335,40);
                //Draws start button with outline.
                g2.setFont(new Font(fontChoice,Font.BOLD,35));
                g2.setColor(Color.BLACK);
                g2.drawString("START SIMULATOR",70,294);
                //draws start button text
                g2.setColor(Color.WHITE);
                g2.fillRect(145,315,168,40);
                g2.setStroke(new BasicStroke(4));
                g2.setColor(Color.BLACK);
                g2.drawRect(145,315,168,40);
                //Draws setting button and outline.
                g2.setFont(new Font(fontChoice,Font.BOLD,33));
                g2.setColor(Color.BLACK);
                g2.drawString("SETTINGS",155,347);
                //Draws setting buttons text.
                g2.setFont(new Font(fontChoice,Font.BOLD,15));
                String myString = String.valueOf(studentAverageList.get(10));
                g2.drawString("At 11 minutes the average for students is "+myString,20,400);
                break;
            case 3:
                g2.setFont(new Font(fontChoice,Font.BOLD,40));
                g2.setColor(Color.WHITE);
                g2.drawString("SETTINGS",140,110);
                //Draw setting for settings text.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Have priority queue for teachers",10,163);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,140,25,25);
                if (priorityQueueSetting == true) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(6));
                    g2.drawLine(412,140,422,159);
                    g2.drawLine(422,159,446,120);
                }
                //Draws priority setting and check mark.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Add a 1-5% randomness on wait times",10,213);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,190,25,25);
                if (randomnessSetting == true) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(6));
                    g2.drawLine(412,190,422,209);
                    g2.drawLine(422,209,446,170);
                }
                //Draws Randomness setting and checkmark.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Placeholder #1",10,263);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,240,25,25);
                //Draws placeholder 1.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Placeholder #2",10,313);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,290,25,25);
                //Draws placeholder 2.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Placeholder #2",10,363);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,340,25,25);
                //Draws placeholder 3.
                break;
            case 4:
                g2.setFont(new Font(fontChoice,Font.BOLD,30));
                g2.setColor(Color.BLACK);
                g2.drawString("Enter below how long you",48,134);
                g2.drawString("want to run the simulator from",20,164);
                g2.drawString("(1-60) minutes!",125,200);
                //drawing Lines telling the user what to do.
                if (drawStringError == true) {
                    g2.setColor(Color.RED);
                    g2.setFont(new Font(fontChoice,Font.BOLD,20));
                    g2.drawString("(Input must be a whole number, and cannot be a",9,350);
                    g2.drawString("word!)",10,380);
                }
                //Draws error line for when user enters a word.
                if (drawNumberError == true) {
                    g2.setColor(new Color(153,0,0));
                    g2.setFont(new Font(fontChoice,Font.BOLD,20));
                    g2.drawString("(input must be a number from 1-60!)",10,410);
                }
                //Draws a error line for when the user enters something not between 1-60.
                break;
            case 5:
                g2.setColor(Color.WHITE);
                g2.fillRect(90,440,65,25);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(90,440,65,25);
                g2.setFont(new Font(fontChoice,Font.BOLD,25));
                g2.setColor(Color.BLACK);
                g2.drawString("Last",95,460);
                g2.setFont(new Font(fontChoice,Font.BOLD,30));
                //Draws last button.
                g2.setColor(Color.WHITE);
                g2.fillRect(310,440,65,25);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(310,440,65,25);
                g2.setFont(new Font(fontChoice,Font.BOLD,25));
                g2.setColor(Color.BLACK);
                g2.drawString("Next",315,460);
                 //draws next button.
                g2.setFont(new Font(fontChoice,Font.BOLD,30));
                g2.drawString("<",112,435);
                g2.drawString(">",332,435);
                //Draws arrows.
                //g2.fillRect(155,440,155,25);
                g2.setFont(new Font(fontChoice,Font.PLAIN,40));
                g2.setColor(Color.GRAY);
                String drawAverage = Integer.toString(getAverageFromList + 1);;
                g2.drawString(drawAverage,215,100);
                g2.setColor(Color.GRAY);
                
                g2.drawString("Averages for "+drawAverage+" minute:",30,150);
                // Draws top number and text to show user what minute average it is.
                g2.setFont(new Font(fontChoice,Font.BOLD,40));
                g2.setColor(Color.BLACK);
                g2.drawString("Students",30,220);
                g2.drawString("Teacher",30,290);
                g2.drawString("Both",30,360);
                //Draws variable names.
                g2.drawString("=",240,220);
                g2.drawString("=",240,290);
                g2.drawString("=",240,360);
                //Draws = signs for all varaibles
                g2.setFont(new Font(fontChoice,Font.BOLD,20));
                g2.setColor(Color.RED);
                g2.drawString(studentAverageList.get(getAverageFromList)+" Mins",285,212);
                g2.drawString(staffAverageList.get(getAverageFromList)+" Mins",285,282);
                g2.drawString(averageList.get(getAverageFromList)+" Mins",285,352);
                //Draws the actual variables.
                
                
                
                 
        }
    }

    public void mouseExited(MouseEvent e) {};

    public void mouseEntered(MouseEvent e) {};

    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        //Defines the mouses x corrodinate.
        int mouseY = e.getY();
        //Defines the mouses Y corrodinate.
        //System.out.println(mouseX+"X "+mouseY+"Y");
        switch (drawMenuValue) {
            case 1: 
                if (mouseX >= 200 && mouseX <= 265 && mouseY >= 320 && mouseY <= 345) {
                    ChooseMenu(2);
                    //Chooses main Menu.
                }
                //Start button corrodinates that when clicked will change the panel to the main panel.
                break;
            case 2:
                if (mouseX >= 62 && mouseX <= 397 && mouseY >= 262 && mouseY <= 302 ) {
                    ChooseMenu(4);
                    //Chooses input panel.
                }
                //Start simulator button corrodinates that when clicked will change the panel to the input panel.
                
                if (mouseX >= 145 && mouseX <= 313 && mouseY > 315 && mouseY <= 355) {
                    ChooseMenu(3);
                    //Chooses settings menu.
                }
                //Setting button corrodinates that when click will change the panel to the settings panel.
                break;
            case 3:
                if (mouseX >= 410 && mouseX <= 435 && mouseY >= 140 && mouseY<= 165) {
                    if (priorityQueueSetting == false) {
                        priorityQueueSetting = true;
                        repaint();
                        
                    } else if (priorityQueueSetting == true) {
                        priorityQueueSetting = false;
                        repaint();
                    }
                }
                
                if (mouseX >= 410 && mouseX <= 435 && mouseY >= 190 && mouseY<= 215) {
                    if (randomnessSetting == false) {
                        randomnessSetting = true;
                        repaint();
                        
                    } else if (randomnessSetting == true) {
                        randomnessSetting = false;
                        repaint();
                    }
                }
                break;
            case 4:
                
                break;
            case 5:
                if (mouseX >= 90 && mouseX <=155 && mouseY >= 440 && mouseY <= 465) {
                   if (getAverageFromList != 0) {
                       getAverageFromList = getAverageFromList - 1;
                       //Chooses the average beforehand to be drawn.
                       repaint();
                       //Repaints with new averages chosen.
                   }
                }
                // Last button corrodinates that when clicked will go to the last average-
                //-ie drawing the third average instead of the fourth average.
                
                if (mouseX >= 310 && mouseX < 375 && mouseY >= 440 && mouseY <= 465) {
                    if (getAverageFromList != 59) {
                       getAverageFromList = getAverageFromList + 1;
                       //Chooses the next average to be drawn
                       repaint();
                       //Repaints with new averages chosen.
                   }
                }
                 // Last button corrodinates that when clicked will go to the next average-
                 //-ie drawing the fourth average instead of the third average.

        }

    }

    public void mousePressed(MouseEvent e) {};

    public void mouseClicked(MouseEvent e) {}
}

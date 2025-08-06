
/**
 * Write a description of class guiTen here.
 *
 * @author Gabriel Gibson
 * @version 3/08/25
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Scanner;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
//Imports librarys.

public class Gui extends JFrame implements ActionListener,MouseListener
{
    JMenuBar menuBar;
    //Defines menubar
    JMenu menu;
    //Defines menu
    JMenuItem menuItem;
    JMenuItem menuItemTwo;
    //Defines menuItem
    JTextField textField;
    JTextField textFieldTwo;
    //Defines textfield

    JPanel startingPanel;
    JPanel mainPanel;
    JPanel settingsPanel;
    JPanel inputPanel;
    JPanel averageDisplayedPanel;
    //defines all panels used
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
    Color panelColorTwo = new Color(207,226,243);
    //panel colours used on panel backgrounds.

    public int windowWidth = 450;
    public int windowHeight = 450;
    //Window variables for size.

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    //Tools for finding screen dimensions
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    //Screen dimensions used to put the window in the middle of the display.
    int drawMenuValue = 1;
    int lastMenuValue = 1;
    //value used in switch statement when choosing what to draw for what panel.
    boolean useLastMenuValue = true;

    private Cafeteria cafeteria = new Cafeteria();
    int cafeteriaRunNumber;
    int amountOfMinutes = 0;
    private ArrayList<Float> staffAverageList = cafeteria.staffAverageList;
    private ArrayList<Float> studentAverageList = cafeteria.studentAverageList;
    private ArrayList<Float> averageList = cafeteria.averageList;
    //defines Cafeteria class and the cafeterias average array variables.
    int getAverageFromList = 0;
    // int value that will be used when getting the averages from the lists.

    boolean drawStringError = false;
    //If set to true will draw error text on input panel about using a number.
    boolean drawNumberError = false;
    //If set to true will draw error text on input panel about having a number between 1 and amount of minute averages.
    boolean drawFileNotFoundError = false;
    //If set true will draw error text in setting pannel about not finding the file.
    boolean drawWrongFileFormatError = false;
    //If set true will draw error text in setting panel about file formate not being .csv
    boolean priorityQueueSetting = true;
    //If set true will draw a checkmark confimring an option chosen by the user about the priority queue.
    boolean randomnessSetting = false;
    //If set true will draw a checkmark confimring an option chosen by the user about randomness.
    boolean secondSetting = false;
    //If set true will draw a checkmark confimring an option chosen by the user about using the unit seconds-
    //-instead of minutes.
    boolean drawFileSuccessful = false;
    //if the file Is sucsefully loaded it will draw a line in the setting panel telling the user-
    //the file was loaded.
    
    public Gui() {
        setTitle("Cafeteria Simulator");
        //Defines title.
        cafeteria.DefineCSVFile();
        amountOfMinutes = cafeteria.amountOfMinutes;
        //Runs define csv file in cafeteria class.
        menuBar = new JMenuBar();
        //Defines menubar in method.
        menuBar.setBackground(Color.GRAY);
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
        textFieldTwo = new JTextField();
        //defines in method
        textField.setBounds(175, 175, 100, 60);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 33));
        textField.addActionListener(this);
        //Lines define size font and centering text in textfield.
        textFieldTwo.setBounds(337, 189, 100, 30);
        textFieldTwo.setHorizontalAlignment(JTextField.CENTER);
        textFieldTwo.setFont(new Font("Arial", Font.PLAIN, 15));
        textFieldTwo.addActionListener(this);

        startingPanel = new JPanel();
        mainPanel = new JPanel();
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        inputPanel = new JPanel();
        inputPanel.setLayout(null);
        averageDisplayedPanel = new JPanel();
        //defining panels in constructer and set some layouts to null so textfields can be placed anywhere in the canvas.
        
        startingPanel.setBackground(panelColor);
        mainPanel.setBackground(panelColor);
        settingsPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(panelColorTwo);
        averageDisplayedPanel.setBackground(panelColorTwo);
        //Changes the colour to the color chosen for the background.
        
        startingGraphic = new Canvas();
        mainGraphic = new Canvas();
        inputGraphic = new Canvas();
        averageGraphic = new Canvas();
        //Defines the canvas in the constructer
        
        startingPanel.add(startingGraphic); 
        mainPanel.add(mainGraphic);
        inputPanel.add(inputGraphic);
        averageDisplayedPanel.add(averageGraphic);
        //Adds the canvases to the panels.
        
        inputPanel.add(textField);
        settingsPanel.add(textFieldTwo);
        //adds the text Field to the panel.

        // Reupdates the startingPanel to update the colour of the background.
        this.getContentPane().setPreferredSize(new Dimension(windowWidth,windowHeight));
        //Defines the size of the window.
        this.setResizable(false);
        //Makes it so the user cant resize the window.
        this.setLocation((screenWidth/2 - windowWidth/2),(screenHeight/2 - windowHeight/2));
        //Sets the location of the window on the screen by using a formula involving your screens-
        //-aspect ratio to make sure its in the middle on any screen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //defining that it will exit when closed
        addMouseListener(this);
        //putting the window to the fronting and defining its viasibility to true\
        startingPanel();
        //runs startingpanel method.
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // Defining the name of the menu option pressed as string.
        System.out.println(cmd);
        //Printing what the menu option is called.
        switch(cmd) {
            case "Exit":
                System.exit(0);
                //exits
                break;
            case "Last page":
                ChooseMenu(lastMenuValue);
                //method that draws the last panel.
                drawStringError = false;
                drawNumberError = false;
                drawFileNotFoundError = false;
                drawWrongFileFormatError = false;
                drawFileSuccessful = false;
                //resets error lines.
                break;

        }
        //switch statement for menu items

        if (e.getSource() == textField) {
            String textFieldInput = textField.getText().trim();
            //Gets the number from the textfield while getting rid of spaces added by the user.
            if (textFieldInput.matches("\\d+")) {
                cafeteriaRunNumber = Integer.parseInt(textFieldInput);
                //Deines the string as an int.
                if (cafeteriaRunNumber >= 1 && cafeteriaRunNumber <= amountOfMinutes) {
                    cafeteria.RunCafeteria(cafeteriaRunNumber);
                    //Runs the cafeteria simulator for how long the user has inputed.
                    getAverageFromList = (cafeteriaRunNumber - 1);
                    //Shows the averages for what the user has put in for run time, so if the user set the-
                    //run cafeteria at 60 mins it will first show the averages for 60 mins,
                    ArrayList<Float> staffAverageList = cafeteria.staffAverageList;
                    ArrayList<Float> studentAverageList = cafeteria.studentAverageList;
                    ArrayList<Float> averageList = cafeteria.averageList;
                    //redefines list if new options have been chosen.
                    drawNumberError = false;
                    drawStringError = false;
                    //resets error lines.
                    ChooseMenu(5);
                    //resetting the error text
                } else {
                    drawNumberError = true;
                    repaint();
                    //Allows error text about numbers to draw.
                }
                //will run cafeteria simulator unless 
            } else {
                drawStringError = true;
                repaint();
                //Allows error text about string to draw.
            }
        }
        //Code that will run when the user presses enter and if all the criteria is met will -
        //-get all the averages for the cafeteria simualtor fromt he cafeteria class. 
        
        if (e.getSource() == textFieldTwo) {
            String textFieldInputTwo = textFieldTwo.getText().trim();
            File checkFile = new File(textFieldInputTwo);
            boolean exists = checkFile.exists();
            String [] checkFileType = checkFile.getName().split("\\.");
            System.out.println(checkFileType[1]);
            if (exists == true) {
                if (checkFileType[1].equals("csv")) {
                    cafeteria.changeFile(checkFile);
                    cafeteria.DefineCSVFile();
                    amountOfMinutes = cafeteria.amountOfMinutes;
                    drawFileSuccessful = true;
                    repaint();
                } else {
                    drawWrongFileFormatError = true;
                    repaint();
                }
            } else {
                drawFileNotFoundError = true;
                repaint();
            }
            //if statements, first one checks if file was found, second one checks if it has .csv at the end
        }
    }

    public void ChooseMenu(int chooseMenuValue) {
        switch (chooseMenuValue) {
            case 1:
                System.out.println("Starting menu chosen");
                startingPanel();
                //runs starting panel method.
                break;
            case 2:
                System.out.println("Main menu chosen");
                mainPanel();
                //runs main menu panel method.
                break;
            case 3:
                System.out.println("settings menu chosen");
                settingsPanel();
                //Runs setting panel method.
                break;
            case 4:
                System.out.println("Input menu chosen");
                inputPanel();
                //Runs the input panel method
                break;
            case 5:
                System.out.println("Average menu chosen");
                averageDisplayedPanel();
                //Runs average display panel method.
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
        //Adds new panel.
        this.pack();
        this.toFront();
        //Packs everything and brings it to the front.
        this.setVisible(true);
        drawMenuValue = 1;
        //Value that will draw for starting menu in switch statemnt. 
        repaint();
        deletePanel = startingPanel;
        lastMenuValue = 1;
        //defines the last menu value in switch statement
    }

    public void mainPanel() {
        this.getContentPane().remove(deletePanel);
        this.getContentPane().add(mainPanel);
        this.pack();
        this.toFront();

        drawMenuValue = 2;
        repaint();
        deletePanel = mainPanel;
        
        drawStringError = false;
        drawNumberError = false;
        drawFileNotFoundError = false;
        drawWrongFileFormatError = false;
        drawFileSuccessful = false;
        if (useLastMenuValue == true) {
            lastMenuValue = 1;
            System.out.println("lastMenuValue");
        } else {
            useLastMenuValue = true;
        }
        
        //defines the last menu value in switch statement
    }

    public void settingsPanel() {
        this.getContentPane().remove(deletePanel);
        this.getContentPane().add(settingsPanel);
        this.pack();
        this.toFront();

        drawMenuValue = 3;
        repaint();
        deletePanel = settingsPanel;
        lastMenuValue = 2;
        //defines the last menu value in switch statement
    }

    public void inputPanel() {
        this.getContentPane().remove(deletePanel);
        this.getContentPane().add(inputPanel);
        this.pack();
        this.toFront();

        drawMenuValue = 4;
        repaint();
        deletePanel = inputPanel;
        lastMenuValue = 2;
        //defines the last menu value in switch statement
    }

    public void averageDisplayedPanel() {
        this.getContentPane().remove(deletePanel);
        this.getContentPane().add(averageDisplayedPanel);
        this.pack();
        this.toFront();

        drawMenuValue = 5;
        repaint();
        deletePanel = averageDisplayedPanel;
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
                //Paints the whs logo with its x and y coordinates.
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
                //Paints a colour filled rectangle and defines size and coordinates.
                g2.setColor(Color.BLACK);
                //Sets Colour.
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(200,320,65,25);
                //Draws another rectangle over the filled rectangle in a different colour-
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
                //Paints the whs logo with its x and y coordinates.
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
                break;
            case 3:
                g2.setFont(new Font(fontChoice,Font.BOLD,40));
                g2.setColor(Color.WHITE);
                g2.drawString("SETTINGS",140,110);
                //Draw setting for settings text.
                g2.setColor(Color.WHITE);
                g2.fillRect(381,52,75,25);
                g2.setColor(Color.BLACK);
                g2.drawRect(381,52,75,27);
                g2.setFont(new Font(fontChoice,Font.PLAIN,18));
                g2.drawString("Menu",394,70);
                //Draws menu button.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Have priority queue for teachers",10,163);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,140,25,25);
                //draws priority setting.
                if (priorityQueueSetting == true) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(6));
                    g2.drawLine(412,140,422,159);
                    g2.drawLine(422,159,446,120);
                }
                //Draws checkmark if priority queue is set to true.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Display in seconds instead of minutes",10,213);
                g2.setStroke(new BasicStroke(4));
                g2.drawRect(410,190,25,25);
                //Draws second setting.
                if (secondSetting == true) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(6));
                    g2.drawLine(412,190,422,209);
                    g2.drawLine(422,209,446,170);
                }
                //draws checkmark if seconds is set to true.
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial",Font.BOLD,20));
                g2.drawString("Change csv file by typing in box ->",10,263);
                g2.drawString("(Must have .csv at end of text.)",10,285);
                //Draws Third option to change csv.
                if (drawFileNotFoundError == true) {
                    g2.setColor(Color.RED);
                    g2.drawString("(File not recognized)",10,313);
                }
                //draws error text if the file the user inputed wasn't found in files.
                if (drawWrongFileFormatError == true) {
                    g2.setColor(Color.RED);
                    g2.drawString("(File must be a .csv file type)",10,343);
                    
                }
                //Draws error text if the file is not csv.
                if (drawFileSuccessful== true) {
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("Arial",Font.BOLD,30));
                    g2.drawString("File loaded successfully!",10,373);

                }
                //Draws text if the file successfully loaded.
                break;
            case 4:
                g2.setFont(new Font(fontChoice,Font.BOLD,30));
                g2.setColor(Color.BLACK);
                g2.drawString("Enter below how long you",48,134);
                g2.drawString("want to run the simulator from",20,164);
                g2.drawString("(1-"+(amountOfMinutes)+") minutes!",125,200);
                //Instructional text telling the user to enter in the simulation length.
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
                    g2.drawString("(input must be a number from (1-"+(amountOfMinutes)+"!)",10,410);
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
                g2.setColor(Color.WHITE);
                g2.fillRect(381,52,75,25);
                g2.setColor(Color.BLACK);
                g2.drawRect(381,52,75,27);
                g2.setFont(new Font(fontChoice,Font.PLAIN,18));
                g2.drawString("Menu",394,70);
                //Draws menu button.
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
                //Draws = signs for all variables

                g2.setFont(new Font(fontChoice,Font.BOLD,20));
                g2.setColor(Color.RED);
                if (secondSetting == false) {
                    float roundedStudentAverage = (float) (Math.round(studentAverageList.get(getAverageFromList) * 100.0)/100.0);
                    float roundedStaffAverage = (float) (Math.round(staffAverageList.get(getAverageFromList) * 100.0)/100.0);
                    float roundedAverage = (float) (Math.round(averageList.get(getAverageFromList) * 100.0)/100.0);
                    // Rounds all averages to 2 decimal places by  Multiply by 100 to shift the decimal point 2 times right-
                    //-then gets Rounded to remove extra digits, then Divides by 100 to shift the decimal back two times left leaving 2 decimal places.
                    g2.drawString(roundedStudentAverage+" Mins",285,212);
                    g2.drawString(roundedStaffAverage+" Mins",285,282);
                    g2.drawString(roundedAverage+" Mins",285,352);
                    //Draws the minute variables.
                } else {
                    float roundedStudentAverage = (float) (Math.round((studentAverageList.get(getAverageFromList)*60) * 100.0)/100.0);
                    float roundedStaffAverage = (float) (Math.round((staffAverageList.get(getAverageFromList) * 100.0)*60)/100.0);
                    float roundedAverage = (float) (Math.round((averageList.get(getAverageFromList) * 100.0)*60)/100.0);
                    //all rounded averages just times averages in averagelist by 60 before calculating.
                    g2.drawString(roundedStudentAverage+" Secs",285,212);
                    g2.drawString(roundedStaffAverage+" Secs",285,282);
                    g2.drawString(roundedAverage+" Sec",285,352);
                    //Draws the seconds variables. 
                }
                //if secondSetting is true it will give the same data just in seconds i.e will times the averages by 60. 

                
        }
    }


    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        //Defines the mouses x coordinate.
        int mouseY = e.getY();
        //Defines the mouses Y coordinate.
        //System.out.println(mouseX+"X "+mouseY+"Y");
        switch (drawMenuValue) {
            case 1: 
                if (mouseX >= 200 && mouseX <= 265 && mouseY >= 320 && mouseY <= 345) {
                    ChooseMenu(2);
                    //Chooses main Menu.
                }
                //Start button coordinates that when clicked will change the panel to the main panel.
                break;
            case 2:
                if (mouseX >= 62 && mouseX <= 397 && mouseY >= 262 && mouseY <= 302 ) {
                    ChooseMenu(4);
                    //Chooses input panel.
                }
                //Start simulator button coordinates that when clicked will change the panel to the input panel.

                if (mouseX >= 145 && mouseX <= 313 && mouseY > 315 && mouseY <= 355) {
                    ChooseMenu(3);
                    //Chooses settings menu.
                }
                //Setting button coordinates that when click will change the panel to the settings panel.
                break;
            case 3:
                if (mouseX >= 410 && mouseX <= 435 && mouseY >= 140 && mouseY<= 165) {
                    if (priorityQueueSetting == false) {
                        priorityQueueSetting = true;
                        //If set to true will draw a checkmark in the settings panel.
                        cafeteria.SettingsPush(1,true);
                        //runs method in cafeteria to use priority queue for staff
                        repaint();

                    } else {
                        priorityQueueSetting = false;
                        //If set to false will not draw a checkmark in the settings panel.
                        cafeteria.SettingsPush(1,false);
                        //runs method in cafeteria to not use priority queue for staff
                        repaint();
                    }
                }
                //Setting button coordinates and running a method in cafeteria that will decide if it uses priority queue-
                //-or normal queue for staff.

                if (mouseX >= 410 && mouseX <= 435 && mouseY >= 190 && mouseY<= 215) {
                    if (secondSetting == true) {
                        secondSetting = false;
                        repaint();
                    } else {
                        secondSetting = true;
                        repaint();
                    }
                }
                //Setting button coordinates and setting a boolean to true or false that if true,-
                //will convert the minute averages to seconds.
                
                if (mouseX >= 381 && mouseX <= 456 && mouseY >=52 && mouseY <= 77) {
                    System.out.println("Menu choice chosen");
                    useLastMenuValue = false;
                    lastMenuValue = 3;
                    ChooseMenu(2);
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
                    }
                }
                // Last button coordinates that when clicked will go to the last average-
                //-ie drawing the third average instead of the fourth average.

                if (mouseX >= 310 && mouseX < 375 && mouseY >= 440 && mouseY <= 465) {
                    if (getAverageFromList != cafeteriaRunNumber-1) {
                        getAverageFromList = getAverageFromList + 1;
                        //Chooses the next average to be drawn
                        repaint();
                    }
                }
                // Last button coordinates that when clicked will go to the next average-
                //-ie drawing the fourth average instead of the third average.

                if (mouseX >= 381 && mouseX <= 456 && mouseY >=52 && mouseY <= 77) {
                    System.out.println("Menu choice chosen");
                    useLastMenuValue = false;
                    lastMenuValue = 5;
                    ChooseMenu(2);
                }
                //
        }

    }

    public void mouseExited(MouseEvent e) {};

    public void mouseEntered(MouseEvent e) {};

    public void mousePressed(MouseEvent e) {};

    public void mouseClicked(MouseEvent e) {}
}


import java.io.File;
import java.util.Scanner;
import java.io.IOException; // handle errors
import java.util.ArrayList;

/**
 * Write a description of class Cafeteria here.
 *
 * @author Gabriel Gibson
 * @version 3/08/25
 */
public class Cafeteria
{
    final String fileName="Aririval.csv"; 
    // Default csv file.
    File theFile = new File(fileName);  
    // generate the file handle
    final int MAXLINES=300;
    // for ease of writing, we are only going to read at most 400 lines, aka 5 hours of time/
    final int VALUESPERLINE=4; 
    // 4 values for each line which is time,student,staff,served.

    private String[] values;
    //defines values from the csv file before moving to the arrayList.

    private ArrayList<String> studentAmount = new ArrayList<String>();
    private ArrayList<String> staffAmount = new ArrayList<String>();
    private ArrayList<String> servedAmount = new ArrayList<String>();
    //arrayList used at the start to define the amount of students per minute.
    public int amountOfMinutes = 0;
    //used to find amount of minutes
    Scanner kb = new Scanner(System.in);

    private ArrayList<Float> staffAverageList = new ArrayList<Float>();
    private ArrayList<Float> studentAverageList = new ArrayList<Float>();
    private ArrayList<Float> averageList = new ArrayList<Float>();
    //the averages for the student staff and combined, will be put into these list when calculated.
    private Queue queue = new Queue();
    // defines the queue class
    private Elements head = queue.head;
    //defines the head element found in the queue class
    private Elements tail = queue.Tail;
    //defines the tail element found in the queue class

    private PriorityQueue priorityQueue = new PriorityQueue();
    // defines the priority queue class for teachers
    private Elements pHead = priorityQueue.head;
    //defines the head element found in the priority queue class
    private Elements pTail = priorityQueue.head;
    //defines the tail element found in the priority queue class

    boolean priorityQueueSetting = true;
    //changes from the gui setting page.
    public Cafeteria() {

    }
    //starts constructor.
    public void changeFile(File changeFile) {
        theFile = changeFile;
    }
    //Runs if the user trys to change the file in the gui class.
    public ArrayList<Float> getTheAverageLists(int chooseAverageList) {
        switch (chooseAverageList) {
            case 1:
                return staffAverageList;

            case 2:
                return studentAverageList;

            case 3:
                return averageList;
            default:
                return null;
        }
    }
    //Returns the private lists so that they can be used in the gui class to display the-
    //averages.
    public void DefineCSVFile() {
        String CSVlines[] = new String[MAXLINES]; 
        //holds the csvLine
        String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];  
        // where we keep all those lines we read in.
        int linecount=0; 
        // initially keeps track of lines read, eventually used to remember the number that was read;

        try {
            Scanner reader = new Scanner(theFile); // Opens the file with the Scanner,
            studentAmount.clear();
            staffAmount.clear();
            servedAmount.clear();
            //resets arrayLists if already defined before from using a diffrent csv file.

            while (reader.hasNextLine()  && linecount < MAXLINES){
                String line=reader.nextLine();             
                CSVlines[linecount]=line;
                linecount++;
            }
            // Reads the file and adds it to the csvLines array.
            for (int i =1; i<linecount; i++){
                System.out.println(CSVlines[i]);
                values = CSVlines[i].split(",");
                //Takes the csv line text and splits them into a unique string every time there-
                //is a comma (,)
                for (int j=0;j<values.length;j++) {
                    switch (j) {
                        case 0:
                            System.out.println("Number!!!!");
                            break;
                        case 1:
                            if (values[j].matches("\\d+(\\.\\d+)?")){
                                studentAmount.add(values[j]);
                            } else {
                                studentAmount.add("0");
                            }
                            //if the variable isnt a number it will default to 0
                            break;
                        case 2:
                            if (values[j].matches("\\d+(\\.\\d+)?") ){
                                staffAmount.add(values[j]);
                            } else {
                                staffAmount.add("0");
                            }
                            break;
                        case 3:
                            if (values[j].matches("\\d+(\\.\\d+)?")){
                                servedAmount.add(values[j]);
                            } else {
                                servedAmount.add("0");
                            }
                            break;
                        default:
                            System.out.println("not regonised");
                            break;
                    }
                    // Switch statement that takes the 3 variables from the csv file-
                    //-Student,Staff,Served and adds them each to diffrent arrays for each type.
                    amountOfMinutes = (studentAmount.size() );
                    System.out.println(amountOfMinutes);
                    //Defines amount of averages, will be used over project when writing amount-
                    //or for loops.
                }
            }
            for (int i=1;i<amountOfMinutes;i++){
                System.out.println();
                System.out.println(+i+1+" Minutes!");
                System.out.println("Student amount "+studentAmount.get(i));
                System.out.println("Staff amount "+staffAmount.get(i));
                System.out.println("Amount served "+servedAmount.get(i));
                System.out.println();
            }
            //Prints out the amounts of student,staff,served in each row.

        } catch (IOException e) {System.out.println(e);}
    }
    //method that Defines the csv file by putting all the variables read from the csv into sorted arrays.
    public void RunCafeteria(int timeFrame) {

        System.out.println("How long do you want to run the simulator for?");
        boolean timeEnterLoop = true;
        while (timeEnterLoop == true) {
            timeFrame = timeFrame - 1;
            if (timeFrame <= amountOfMinutes && timeFrame >= 0) {
                timeEnterLoop = false;
            } else {
                System.out.println("try again"); 
            }
        }
        //Loop that runs until the number is between 1 and how many minutes are in the data set.
        while (queue.head != null) {
            queue.pop();
        }
        while (priorityQueue.head != null) {
            priorityQueue.pop();
        }
        //If the runCafeteria method is done multiple times this makes sure the queues are empty beforehand.
        staffAverageList.clear();
        studentAverageList.clear();
        averageList.clear();
        //resets lists to 0 for debuggin incase new data is used

        int whileLoopValue = 0;
        //defining the whileLoopValue.
        Elements head = queue.head;
        //defines the head element found in the queue class.
        Elements tail = queue.Tail;
        //defines the tail element found in the queue class.
        float divideStudent = 0;
        float divideStaff = 0;
        float totalStudent = 0;
        float totalStaff = 0;
        //Defines the variables that will be used when calculating the averages for student and staff.
        for (int i = 0;i<=timeFrame;i++) {
            queue.updateTime(); 
            priorityQueue.updateTime();
            int studentValue = Integer.parseInt(studentAmount.get(i));
            //Amount of students in a csv line that will be pushed in the queue
            System.out.println("Student amount is "+studentValue);

            int staffValue = Integer.parseInt(staffAmount.get(i));
            //Amount of teachers in a csv line that will be pushed in the queue
            System.out.println("Staff amount is "+staffValue);

            int servedValue = Integer.parseInt(servedAmount.get(i));
            //Amount of students and teachers in the csv line that will be served in the queue
            System.out.println("Served amount is "+servedValue);
            head = queue.head;
            pHead = priorityQueue.head;
            System.out.println(pHead);
            //redfining the head of the queue as it has changed from all the values we have pushed
            Elements next = head;
            Elements pNext = pHead;
            //System.out.println("head is "+next.getValue());

            if (priorityQueueSetting == true) {
                System.out.println("priority code running");
                while (whileLoopValue < staffValue) {
                    priorityQueue.push(0);
                    whileLoopValue++;
                }
                //Pushes all staff to priority queue if priorityQueueSetting is set to true.
                whileLoopValue = 0;
                //resets whileLoopValue
                while (whileLoopValue < studentValue) {
                    queue.push(0,2);
                    whileLoopValue++;                   
                }
                //Pushes student.
                whileLoopValue = 0;

                while (whileLoopValue < servedValue) {
                    if (priorityQueue.queueEmpty() == false) {

                        
                        divideStaff = divideStaff + 1;
                        System.out.println("divide for staff "+divideStaff);
                        //Adds to the division used for the staff average.
                        totalStaff = totalStaff + priorityQueue.head.getValue();
                        //Adds to the total used for the staff average.
                        System.out.println("total for staff "+totalStaff);
                        priorityQueue.pop();

                        //Serves/pops a staff in the priority queue class, aka the priority line.
                    } else {

                        if (this.queue.head == null) {
                            System.out.println("more served amount then people in queue");
                        } else {
                            totalStudent = totalStudent + queue.head.getValue();
                            divideStudent = divideStudent + 1;
                            //Adds to the division used for the student average.
                            System.out.println("divide for students "+divideStudent);
                            queue.pop();
                            //Serves/pops a student in the queue class, aka the normal line.
                        }
                        //Adds to the total used for the student average.
                        System.out.println("total for Student "+totalStudent);
                    }
                    //Serves priority queue first if it isnt empty.
                    whileLoopValue++;
                }
                //serves student and staff and will servee staff first if any are found in the-
                //priority queue.
                whileLoopValue = 0;
            } else if (priorityQueueSetting == false){
                while (whileLoopValue < staffValue) {
                    queue.push(0,1);
                    whileLoopValue++;
                } 
                //pushes staff to normal queue if priorityQueueSetting is set to false.
                whileLoopValue = 0;
                while (whileLoopValue < studentValue) {
                    queue.push(0,2);
                    whileLoopValue++;
                }
                //pushes students to normal queue.
                whileLoopValue = 0;
                while (whileLoopValue < servedValue) {
                    if (queue.head == null) {
                        System.out.println("more served then people in queue");
                    } else {
                        if (queue.head.getType() == "Teacher") {

                            divideStaff = divideStaff + 1;
                            System.out.println("divide for staff "+divideStaff);
                            totalStaff = totalStaff + queue.head.getValue();
                            System.out.println("total for staff "+totalStaff);
                            queue.pop();
                        } else if (queue.head.getType() == "Student") {
                            divideStudent = divideStudent + 1;
                            //Adds to the division used for the student average.
                            System.out.println("divide for students "+divideStudent);
                            totalStudent = totalStudent + queue.head.getValue();
                            //Adds to the total used for the student average.
                            System.out.println("total for Student "+totalStudent);
                            queue.pop();
                        }
                        // if statement that finds if the person being served is student or staff before popping-
                        //so they can add to the total and amount they have to divide by.
                    }
                    whileLoopValue++;
                }

                whileLoopValue = 0;
            }
            //if statement that runs two diffrent chunks on code depending if the priority queue-
            //-is set to on or off by the user.

            //pushes the staff tto the priority queue
            
            //while loop that pops/serves people in the priority queue first before the normal queue.
            whileLoopValue = 0;

            System.out.println("Divide by for student is "+divideStudent+" and for teacher is "+divideStaff);
            System.out.println("Total for student is "+totalStudent+" and for teachers is "+totalStaff);
            //Prints out data that will be used to find averages.
            System.out.println();
            float staffAverage;
            if (divideStaff == 0) {
                staffAverage = 0;
            } else {
                staffAverage = totalStaff/divideStaff;
            }
            // Debug code that makes sure that averages aren't printed as null.
            staffAverageList.add(staffAverage);
            //staff average time
            float studentAverage;
            if (divideStudent == 0) {
                studentAverage  = 0;
            } else {
                studentAverage = totalStudent/divideStudent;
            }             
            studentAverageList.add(studentAverage);
            //student average time
            float average = (staffAverage + studentAverage)/2;
            averageList.add(average);
            //average time for both students and teachers

            System.out.println("The average wait time for "+(i+1)+" minutes for students is "+studentAverage);
            System.out.println("The average wait time for "+(i+1)+" minutes for staff is "+staffAverage);
            System.out.println("The avaerage wait time for both students and teachers is "+average);
            System.out.println();
            System.out.println();
            //Prints out all averages.
        }
        //While loop that gets the averages for how long the user has chosen to run the simulator for.
    }
    //Runs the cafeteria simulator, by pushing staff and students into there selected queues in the-
    //-queue class, then after calculates the averages for students,staff, and both combined, and then-
    //puts the averages into there own list.

    public void SettingsPush(int chooseSetting,boolean trueOrFalse) {
        switch (chooseSetting) {
            case 1:
                this.priorityQueueSetting = trueOrFalse;
                //sets priorityQueueSetting to what the user has chosen in the gui class.
                System.out.println("priorityQueue is set to "+priorityQueueSetting);
                break;

        }
    }
    //Switch statement for when the user chooses a setting in the gui-
    //so that it will be pushed to the cafetria class.
}

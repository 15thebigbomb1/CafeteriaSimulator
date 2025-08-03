
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
    final String filename="Aririval.csv"; // change to reflect the CSV we are reading
    final int MAXLINES=100; // for ease of writing, we are only going to read at most 100 lines.
    final int VALUESPERLINE=4;  

    private String[] values;

    ArrayList<String> studentAmount = new ArrayList<String>();
    ArrayList<String> staffAmount = new ArrayList<String>();
    ArrayList<String> servedAmount = new ArrayList<String>();
    Scanner kb = new Scanner(System.in);

    public ArrayList<Float> staffAverageList = new ArrayList<Float>();
    public ArrayList<Float> studentAverageList = new ArrayList<Float>();
    public ArrayList<Float> averageList = new ArrayList<Float>();
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
    boolean randomnessSetting = false;
    public Cafeteria() {

    }

    public void DefineCSVFile() {
        File  thefile = new File(filename);  // generate the file handle
        String CSVlines[] = new String[MAXLINES]; //holds the csvLine
        String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];  // where we keep all those lines we read in.
        int linecount=0;  // initially keeps track of lines read, eventually used to remember the number that was read;

        try {
            Scanner reader = new Scanner(thefile); // Opens the file with the Scanner,

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
                            studentAmount.add(values[j]);
                            break;
                        case 2:
                            staffAmount.add(values[j]);
                            break;
                        case 3:
                            servedAmount.add(values[j]);
                            break;
                        default:
                            System.out.println("not regonised");
                            break;
                    }
                    // switch statement that takes the 3 variables from the csv file-
                    //-Student,Staff,Served and adds them each to diffrent arrays for each type.
                }
            }

            for (int i=0;i<60;i++){
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

    public void RunCafeteria(int timeFrame) {

        System.out.println("How long do you want to run the simulator for?");
        boolean timeEnterLoop = true;
        while (timeEnterLoop == true) {
            timeFrame = timeFrame - 1;
            if (timeFrame <= 60 && timeFrame > 0) {
                timeEnterLoop = false;
            } else {
                System.out.println("try again"); 
            }
        }
        //Loop that runs until the number is between 1-60.
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

            //values for finding the averages 
            System.out.println(priorityQueueSetting);
            if (priorityQueueSetting == true) {
                System.out.println("priority code running");
                while (whileLoopValue < staffValue) {
                    priorityQueue.push(0);
                    whileLoopValue++;
                }
                whileLoopValue = 0;
                while (whileLoopValue < studentValue) {
                    queue.push(0,2);
                    whileLoopValue++;                   
                }
                whileLoopValue = 0;

                while (whileLoopValue < servedValue) {
                    if (priorityQueue.queueEmpty() == false) {
                        divideStaff = divideStaff + 1;
                        //Adds to the division used for the staff average.
                        System.out.println("divide for staff "+divideStaff);
                        totalStaff = totalStaff + priorityQueue.head.getValue();
                        //Adds to the total used for the staff average.
                        System.out.println("total for staff "+totalStaff);
                        priorityQueue.pop();
                        //Serves/pops a staff in the priority queue class, aka the priority line.
                    } else {
                        divideStudent = divideStudent + 1;
                        //Adds to the division used for the student average.
                        System.out.println("divide for students "+divideStudent);
                        totalStudent = totalStudent + queue.head.getValue();
                        //Adds to the total used for the student average.
                        System.out.println("total for Student "+totalStudent);
                        queue.pop();
                        //Serves/pops a student in the queue class, aka the normal line.
                    }
                    whileLoopValue++;
                }
                whileLoopValue = 0;
            } else if (priorityQueueSetting == false){
                while (whileLoopValue < staffValue) {
                    queue.push(0,1);
                    whileLoopValue++;
                } 
                whileLoopValue = 0;
                while (whileLoopValue < studentValue) {
                    queue.push(0,2);
                    whileLoopValue++;
                }
                whileLoopValue = 0;
                while (whileLoopValue < servedValue) {
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
                    whileLoopValue++;
                }
                whileLoopValue = 0;
            }

            
            
            //pushes the staff tto the priority queue
            
            
            //while loop that pops/serves people in the priority queue first before the normal queue.
            whileLoopValue = 0;

            
            
            // while (next != null) {
            // totalStudent = totalStudent + next.getValue();
            // divideStudent++;
            // next = next.nextStack();
            // //goes to the next object in the queue 
            // }

            // while (pNext != null) {
            // totalStaff = totalStaff + pNext.getValue();
            // System.out.println(totalStaff);
            // divideStaff++;
            // System.out.println(divideStaff);
            // pNext = pNext.nextStack();
            // }

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
            float average = (totalStaff + totalStudent)/(divideStudent + divideStaff);
            averageList.add(average);
            //average time for both students and teachers

            System.out.println("The average wait time for "+(i+1)+" minutes for students is "+studentAverage);
            System.out.println("The average wait time for "+(i+1)+" minutes for staff is "+staffAverage);
            System.out.println("The avaerage wait time for both students and teachers is "+average);
            System.out.println();
            System.out.println();
        }
    }

    public void SettingsPush(int chooseSetting,boolean trueOrFalse) {
        switch (chooseSetting) {
            case 1:
                this.priorityQueueSetting = trueOrFalse;
                System.out.println("priorityQueue is set to "+priorityQueueSetting);
                break;

        }
    }
}

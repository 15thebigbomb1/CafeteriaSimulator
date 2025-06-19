import java.io.File;
import java.util.Scanner;
import java.io.IOException; // handle errors
import java.util.ArrayList;

/**
 * Write a description of class Cafeteria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cafeteria
{
    final String filename="Arrival.csv"; // change to reflect the CSV we are reading
    final int MAXLINES=100; // for ease of writing, we are only going to read at most 100 lines.
    final int VALUESPERLINE=4;  
    
    private String[] values;
    
    ArrayList<String> studentAmount = new ArrayList<String>();
    ArrayList<String> staffAmount = new ArrayList<String>();
    ArrayList<String> servedAmount = new ArrayList<String>();
    
    public Cafeteria() {
        
    }
    
    public void defineCSVFile() {
        File  thefile = new File(filename);  // generate the file handle
        String CSVlines[] = new String[MAXLINES];
        String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];  // where we keep all those lines we read in.
        int linecount=0;  // initially keeps track of lines read, eventually used to remember the number that was read;
        
        

        try {
            Scanner reader = new Scanner(thefile); // open the file with the Scanner
            
            // Read in the file, stop at file end or if we read too many lines
            while (reader.hasNextLine()  && linecount < MAXLINES){
                String line=reader.nextLine();             
                CSVlines[linecount]=line;
                linecount++;
            }

            // Now we have all the lines, lets print them out enmass
            // This is just to prove we have read them in okay.  In reality, we don't need to do this step.
            for (int i =1; i<linecount; i++){
                System.out.println(CSVlines[i]);
                values = CSVlines[i].split(",");
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
        } catch (IOException e) {System.out.println(e);}
    }
    
}

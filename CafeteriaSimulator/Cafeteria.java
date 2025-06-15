import java.io.File;
import java.util.Scanner;
import java.io.IOException; // handle errors

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
    public Cafeteria() {
        
    }
    public void defineCSVFile() {
        File  thefile = new File(filename);  // generate the file handle
        String CSVlines[] = new String[MAXLINES];
        String AllLinesAllElements[][]=new String[MAXLINES][VALUESPERLINE];  // where we keep all those lines we read in.
        int linecount=0;  // initially keeps track of lines read, eventually used to remember the number that was read;
        String Letters[] = {"A","B","C"};
        

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
            for (int i =0; i<linecount; i++){
                System.out.println(CSVlines[i]);
                System.out.println(Letters[0]+(i+1)+","+Letters[1]+(i+1));
            }
            // This prints out the results first and the corrodinates second

        } catch (IOException e) {System.out.println(e);}
    }
    
}

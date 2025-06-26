
/**
 * Write a description of class IQueue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queue
{
    public Elements head;
    public Elements Tail;
    //the head and tail of the queue, i.e the front and back
    public Queue() {
        
    }
    //creates object
    public boolean queueEmpty() {
        return (head == null);
    }
    // returns if queue is empty
    public void push (int value, int valueTwo) {
        Elements element = new Elements();
        //creates new element
        element.setValue(value);
        //defines the value of the object aka the int amount
        if (valueTwo == 1) {
            element.setStudent();
        } else {
            element.setTeacher();
        }
        // defines if the object is a teacher or a student
        if (queueEmpty()) {
            head = element;
        }
        //if the queue is empty then the next object will have to be the head of the object
        if (Tail != null) {
            Tail.LinkTo(element);
        }
        //if the tail is defined then the tail gets linked to the element
        Tail = element;
        //element is defined as tail
        
        
    }
    // creates/pushes a new object into the queue
    public void updateTime() {
        Elements timeUpdater = head;
        //makes the first element updated the front of the queue
        while (timeUpdater != null) {
           timeUpdater.setValue((timeUpdater.getValue()+1));
           
           timeUpdater = timeUpdater.nextStack();
           //defines the timeUpdater element as the next element in queue 
        }
        //while loop that adds 1 minute to every object in queue
    }
    
    public int pop() {
        int value=head.getValue();
        head =head.nextStack();
        //gets the head removed and defines the head as the next element in stack
        return value;
        //returns the previous head value
    }
    //removes the element at the front of the queue and defines the one next in queueas the head
}

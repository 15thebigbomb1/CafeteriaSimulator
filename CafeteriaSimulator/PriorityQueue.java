 


/**
 * Write a description of class IQueue here.
 *
 * @author Gabriel Gibson
 * @version 13/07/25
 */
public class PriorityQueue
{
    public Elements head;
    public Elements Tail;
    //the head and tail of the queue, i.e the front and back
    public PriorityQueue() {
        
    }
    //creates object
    public boolean queueEmpty() {
        return (head == null);
    }
    // returns if priority queue is empty
    public void push (int value) {
        Elements element = new Elements();
        //creates new element
        element.setValue(value);
        //defines the value of the object aka the int amount
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
    // creates/pushes a new object into the priority queue
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
    //method that updates the time for all objects in priority queue
    
    public int pop() {
        int value=head.getValue();
        head =head.nextStack();
        //gets the head removed and defines the head as the next element in stack
        return value;
        //returns the previous head value
    }
    //removes the element at the front of the priority queue and defines the one next in queueas the head
}

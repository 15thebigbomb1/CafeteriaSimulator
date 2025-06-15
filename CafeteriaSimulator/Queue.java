
/**
 * Write a description of class IQueue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queue
{
    private Elements head;
    private Elements Tail;
    
    public Queue() {
        
    }
    
    public boolean queueEmpty() {
        return (head == null);
    }
    
    public void push (int value, int valueTwo) {
        Elements element = new Elements();
        element.setValue(value);
        if (valueTwo == 1) {
            element.setStudent();
        } else {
            element.setTeacher();
        }
         
        if (queueEmpty()) {
            head = element;
        }
        
        if (Tail != null) {
            Tail.LinkTo(element);
        }
        Tail = element;
    }
    
    public int pop() {
        int value=head.getValue();
        head =head.nextStack();
        return value;
    }
    
}

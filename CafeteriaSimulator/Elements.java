
/**
 * 
 *
 * Gabriel Gibson 
 * Version one, 15/05/25
 */
public class Elements
{
    private int payload;
    private boolean isTeacher;
    private Elements next;
    public Elements()
    {
        
    }
    //to Start an element object
    public void LinkTo(Elements to) {
        next = to;
    }
    //Links the element to another user selected element
    public Elements nextStack() {
        return next;
    }
    //returns the linked element 
    public void setValue(int value) {
        payload = value;
    }
    //sets the elements value 
    public void setStudent() {
      isTeacher = false;
    }
    //sets teacher to false
    public void setTeacher() {
      isTeacher = true;
    }
    // sets teacher to true
    public int getValue() {
        return payload;
    }
    //returns value
    public String getType() {
        if (isTeacher == true) {return "Teacher";} 
        else {return "Student";} 
    }
    // returns Teacher if isTeacher is true, and it returns Student if false
}

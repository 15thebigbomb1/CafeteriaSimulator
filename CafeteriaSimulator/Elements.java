
/**
 * 
 *
 * Gabriel Gibson 
 * Version one, 15/05/25
 */
public class Elements
{
    private int payload;
    private boolean isStudent;
    private Elements next;
    public Elements()
    {
        
    }
    
    public void LinkTo(Elements to) {
        next = to;
    }
    
    public Elements nextStack() {
        return next;
    }
    
    public void setValue(int value) {
        payload = value;
    }
    
    public void setStudent() {
      isStudent = true;
    }
    
    public void setTeacher() {
      isStudent = false;
    }
    
    public int getValue() {
        return payload;
    }
    
    public String getType() {
        String returnType;
        if (isStudent == true) {
            returnType = "Student";
            return returnType;
        } else {
            returnType = "Teacher";
            return returnType;
        }
    }
}

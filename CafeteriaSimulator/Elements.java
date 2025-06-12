
/**
 * 
 *
 * Gabriel Gibson 
 * Version one, 15/05/25
 */
public class Elements
{
    private int payload;
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
    
    public int getValue() {
        return payload;
    }
}

package parking.valet;

import java.util.UUID;

/**
 * When using a hash-based Collection or Map such as HashSet, LinkedHashSet, HashMap, 
 * Hashtable, or WeakHashMap, make sure that the hashCode() of the key objects that 
 * you put into the collection never changes while the object is in the collection. 
 * The bulletproof way to ensure this is to make your keys immutable, which has also other benefits.

 * @author jian.wang
 *
 */
public class Ticket {

	public String uniqueID;
	
	public Ticket(){
		uniqueID = UUID.randomUUID().toString();
	}	
	
	/* otherwise Ticket is not going to be the same*/
	/* hash code still can collide */
	@Override
	public int hashCode(){
		return uniqueID.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Ticket))
            return false;
        if (obj == this)
            return true;
        Ticket rhs = (Ticket) obj;
        return rhs.uniqueID==this.uniqueID;
    }
}

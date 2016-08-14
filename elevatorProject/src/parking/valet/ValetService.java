package parking.valet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class ValetService {

	// TreeSet is a balanced tree, provide 
	// log(n) time for add, remove and contains
	SortedSet<Space> smallSpace = new TreeSet<>();
	SortedSet<Space> mediumSpace = new TreeSet<>();
	SortedSet<Space> largeSpace = new TreeSet<>();
	
	List<Ticket> generatedTicket = new LinkedList<>();
	
	Map<Ticket,Space> parkedCars = new HashMap<Ticket,Space>();
	Map<Ticket,Space> indexedCars = new HashMap<Ticket,Space>();
	
	public ValetService(){
		// populate smallSpace
		// populate mediumSpace
		// populate largeSpace
		// populate generatedTicket
	}
	
	// ---------------------------------------------

	// we want to pick the smallest car that's close to fit our car first
	// idempotent
	public synchronized Ticket parkCar(Car car) throws NoSuchElementException{
		
		// 1. get a space 
		Space space = null;
		if(smallSpace.size()>0)
			space = smallSpace.first();
		else if(mediumSpace.size()>0)
			space = mediumSpace.first();
		// allow NoSuchElementException so this code user can handle this
		space = largeSpace.first();
		
		// 2. park a car
		space.parkSpaceWithCar(car);
		
		// 3. place a ticket there
		Ticket ticket = generatedTicket.remove(0);
		parkedCars.put(ticket, space);
		
		return ticket;
	}
	
	// not idempotent
	public synchronized Car retrieveCar(Ticket ticket){

		// 1. un-park the car, which can take 10 minutes
		Space space = parkedCars.get(ticket);
		Car car = space.getCar();
		
		// 2. put the ticket back
		generatedTicket.add(ticket);
		
		// 3. put the parking space back
		if(space.size==Size.SMALL){
			smallSpace.add(space);
		}
		else if(space.size==Size.MEDIUM){
			mediumSpace.add(space);
		}
		else{
			largeSpace.add(space);
		}
		
		// return the car
		return car;
	}
	
	   public static void main(String args[]) {
		      // Create a tree set
		      TreeSet ts = new TreeSet();
		      // Add elements to the tree set
		      ts.add("C");
		      ts.add("A");
		      ts.add("B");
		      ts.add("E");
		      ts.add("F");
		      ts.add("D");
		      System.out.println(ts);
		   }
}

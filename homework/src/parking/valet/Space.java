package parking.valet;

public class Space {

	int floor;
	int number;
	int distanceFromBooth; 
	Size size;
	
	Car ocupant;
	
	public synchronized void parkSpaceWithCar(Car car){
		ocupant = car;
	}
	
	public synchronized Car getCar(){
		ocupant = null;
		return ocupant;
	}
}

package requests;

public class FloorRequest extends Request{
	int destinationFloor;
	
	public FloorRequest(int priority, int destinationFloor){
		this.priority = priority;
		this.destinationFloor = destinationFloor;
	}
}

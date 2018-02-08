package design.elevator.control.request;

public class ElevatorRequest extends Request{
	int destinationFloor;
	
	public ElevatorRequest(int priority, int destinationFloor){
		this.priority = priority;
		this.destinationFloor = destinationFloor;
	}
}

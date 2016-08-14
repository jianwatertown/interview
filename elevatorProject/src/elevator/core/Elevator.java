package elevator.core;

import elevator.enums.DoorStatus;
import elevator.enums.ElevatorDirection;

/***
 * Representing the Elevator "box", provide primitives to change the
 * 	status of elevator
 * 
 * @author jian.wang
 */
public class Elevator {

	// internal status of elevator
	private ElevatorDirection currentDirection;
	private DoorStatus doorStatus;
	private int currentFloor;
	
	// retrieve current status, public available
	public int getCurrentFloor(){ return currentFloor;}
	public ElevatorDirection getCurrentDirection(){return currentDirection;}
	public DoorStatus getDoorStatus() {return doorStatus;}
	
	
	// update internal status, used internally
	void goUp(){};
	void goDown(){};
	void open(){};
	void close(){};

}

package core;

import control.DoorStatus;
import control.Floor;

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
	private Floor currentFloor;
	
	// retrieve current status, public available
	public Floor getCurrentFloor(){ return currentFloor;}
	public ElevatorDirection getCurrentDirection(){return currentDirection;}
	public DoorStatus getDoorStatus() {return doorStatus;}
	
	
	// update internal status, used internally
	void goUp(){};
	void goDown(){};
	void open(){};
	void close(){};

}

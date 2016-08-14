package elevator.control;

import java.util.List;

import elevator.control.request.ElevatorRequest;
import elevator.control.request.Request;
import elevator.core.Elevator;
import elevator.enums.ElevatorInstruction;


public class ElevatorController implements RequestListener{
	
	// internal structures
	private Elevator elevator;
	private ElevatorSchedulingAlgorithm scheduler;
	private List<ElevatorRequest> requestQueue;
	// in case of multi-elevator system, this is to be replaced by a hashmap-queue
	
	
	// Clients add requests to elevator
	@Override
	public void handleRequest(Request request){}
		
	public void runElevator(){
		
		// every few milli-seconds
		while(true){
		ElevatorInstruction nextInstruction = scheduler.getNextInstructin(
						elevator.getCurrentFloor(), elevator.getCurrentDirection(),requestQueue);
		// do something about the instruction
		}
		
	}
}
 
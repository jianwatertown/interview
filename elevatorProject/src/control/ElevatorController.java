package control;

import java.util.List;

import core.Elevator;
import core.ElevatorInstruction;
import requests.ElevatorRequest;


public class ElevatorController implements RequestListener{
	
	// internal structures
	private Elevator elevator;
	private ElevatorSchedulingAlgorithm scheduler;
	private List<ElevatorRequest> requestQueue;
	// in case of multi-elevator system, this is to be replaced by a hashmap-queue
	
	
	// Clients add requests to elevator
	@Override
	public void addToRequestQueue(ElevatorRequest request){}
		
	public void runElevator(){
		
		// every few milli-seconds
		while(true){
		ElevatorInstruction nextInstruction = scheduler.getNextInstructin(
						elevator.getCurrentFloor(), elevator.getCurrentDirection(),requestQueue);
		// do something about the instruction
		}
		
	}
}
 
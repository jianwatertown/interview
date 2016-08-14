package elevator.control;

import java.util.Set;

import elevator.control.request.Request;

/**
 * If we need global optimization, then we want to send all events to here then 
 * 	figure out how to proceed
 * @author jian.wang
 *
 */
public class BuildingElevatorController implements RequestListener{

	Set<ElevatorController>	 elevatorControllers;
	private BuildingSchedulingAlgorithm globalScheduler;
	
	@Override
	public void handleRequest(Request request) {
		// ElevatorRequest
		// FloorRequest
		// use globalScheduler to figure out how to dispatch further
	}
}

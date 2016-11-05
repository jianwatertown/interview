package design.elevator.control;

import java.util.List;

import design.elevator.control.request.ElevatorRequest;
import design.elevator.enums.ElevatorDirection;
import design.elevator.enums.ElevatorInstruction;

public interface ElevatorSchedulingAlgorithm {
	public ElevatorInstruction getNextInstructin(
			int floor,ElevatorDirection direction, List<ElevatorRequest> requestQueue);
}

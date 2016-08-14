package elevator.control;

import java.util.List;

import elevator.control.request.ElevatorRequest;
import elevator.enums.ElevatorDirection;
import elevator.enums.ElevatorInstruction;

public interface ElevatorSchedulingAlgorithm {
	public ElevatorInstruction getNextInstructin(
			int floor,ElevatorDirection direction, List<ElevatorRequest> requestQueue);
}

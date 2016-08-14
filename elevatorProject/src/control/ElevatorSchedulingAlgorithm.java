package control;

import java.util.List;

import enums.ElevatorDirection;
import enums.ElevatorInstruction;
import requests.ElevatorRequest;

public interface ElevatorSchedulingAlgorithm {
	public ElevatorInstruction getNextInstructin(
			int floor,ElevatorDirection direction, List<ElevatorRequest> requestQueue);
}

package control;

import java.util.List;

import core.ElevatorDirection;
import core.ElevatorInstruction;
import requests.ElevatorRequest;

public interface ElevatorSchedulingAlgorithm {
	public ElevatorInstruction getNextInstructin(
			Floor floor,ElevatorDirection direction, List<ElevatorRequest> requestQueue);
}

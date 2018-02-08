package design.elevator.client;

import design.elevator.control.request.Request;

public interface RequestDispatcher {

	void dispatch(Request request);
}

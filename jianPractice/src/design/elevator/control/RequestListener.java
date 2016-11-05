package design.elevator.control;

import design.elevator.control.request.Request;

public interface RequestListener {
	public void handleRequest(Request request);
}

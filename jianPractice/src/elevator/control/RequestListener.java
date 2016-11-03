package elevator.control;

import elevator.control.request.Request;

public interface RequestListener {
	public void handleRequest(Request request);
}

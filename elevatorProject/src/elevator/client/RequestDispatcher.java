package elevator.client;

import elevator.control.request.Request;

public interface RequestDispatcher {

	void dispatch(Request request);
}

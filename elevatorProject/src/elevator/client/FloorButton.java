package elevator.client;

import elevator.control.request.FloorRequest;
import elevator.control.request.Request;

public class FloorButton extends Button implements RequestDispatcher{

	@Override
	public String getLabel() {
		return "up or down";
	}

	@Override
	public void press() {
		dispatch(new FloorRequest(0,10));
	}

	@Override
	public void dispatch(Request request) {
		// TODO Auto-generated method stub
		
	}
}

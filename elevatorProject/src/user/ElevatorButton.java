package user;

import java.util.Set;

import control.RequestListener;
import requests.ElevatorRequest;
import requests.Request;

/**
 * Make sure this elevator button is deployed inside the elevator
 * 
 * @author jian.wang
 *
 */
public class ElevatorButton extends Button implements RequestDispatcher {

	// Button(dispatcher) depends on ElevatorController(listener)
	Set<RequestListener> listeners;
	
	@Override
	public void dispatch(Request request) {			// only dispatch elevator request
		if(request instanceof ElevatorRequest){
			for(RequestListener listener: listeners){
				listener.handleRequest((ElevatorRequest)request);
			}
		}
	}

	@Override
	public String getLabel() {
		return "10";
	}

	@Override
	public void press() {
		dispatch(new ElevatorRequest(0,10));
	}
}

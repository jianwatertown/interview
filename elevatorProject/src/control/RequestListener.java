package control;

import requests.ElevatorRequest;

public interface RequestListener {
	public void addToRequestQueue(ElevatorRequest request);
}

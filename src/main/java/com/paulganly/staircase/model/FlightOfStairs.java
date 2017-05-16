package com.paulganly.staircase.model;

import java.util.ArrayList;
import java.util.List;

public class FlightOfStairs {

	private int flightNumber;
	private int numberOfSteps;
	private boolean forwardFacing;
	private boolean hasLanding;
	private boolean isLastFlight;

	public FlightOfStairs(int flightNumber, int numberOfSteps, boolean forwardFacing, boolean hasLanding,
			boolean isLastFlight) {
		super();
		this.flightNumber = flightNumber;
		this.numberOfSteps = numberOfSteps;
		this.forwardFacing = forwardFacing;
		this.hasLanding = hasLanding;
		this.isLastFlight = isLastFlight;
	}

	/**
	 * Given a list of integers, creates a flight of stairs for each. Alternates whether the
	 * stairs is forward or backward facing. If the flight is the last on the list,
	 * marks it as such, otherwise set the hasLanding marker to say that a landing will
	 * be needed after the flight.
	 *
	 * @param stairsFlights
	 * @return a list of flights of stairs
	 */
	public static List<FlightOfStairs> getListOfStairsFlights(Integer[] stairsFlights) {
		List<FlightOfStairs> stairsFlightsList = new ArrayList<>();
		boolean isForwardFacing = true;
		int currentFlight = 1;
		for(Integer numberOfSteps: stairsFlights){
			if(currentFlight < stairsFlights.length){
				stairsFlightsList.add(
						new FlightOfStairs(currentFlight, numberOfSteps, isForwardFacing, true, false));
			}else{
				stairsFlightsList.add(
						new FlightOfStairs(currentFlight, numberOfSteps, isForwardFacing, false, true));
			}
			isForwardFacing = !isForwardFacing;
			currentFlight ++;
		}
		return stairsFlightsList;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public boolean isForwardFacing() {
		return forwardFacing;
	}

	public void setForwardFacing(boolean forwardFacing) {
		this.forwardFacing = forwardFacing;
	}

	public boolean isHasLanding() {
		return hasLanding;
	}

	public void setHasLanding(boolean hasLanding) {
		this.hasLanding = hasLanding;
	}

	public boolean isLastFlight() {
		return isLastFlight;
	}

	public void setLastFlight(boolean isLastFlight) {
		this.isLastFlight = isLastFlight;
	}

}

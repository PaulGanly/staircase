package com.paulganly.staircase.model;

import java.util.List;

public class CalculateStepsRequest {
	
	private List<Integer> flightsOfStairs;
	private Integer strideLength;
	
	public List<Integer> getFlightsOfStairs() {
		return flightsOfStairs;
	}
	public void setFlightsOfStairs(List<Integer> flightsOfStairs) {
		this.flightsOfStairs = flightsOfStairs;
	}
	public Integer getStrideLength() {
		return strideLength;
	}
	public void setStrideLength(Integer strideLength) {
		this.strideLength = strideLength;
	}

}

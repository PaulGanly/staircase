package com.paulganly.staircase.model;

public class CalcluatedStepsResponse {
	
	private int requiredSteps;
	private int[][] stepsMap;

	public int getRequiredSteps() {
		return requiredSteps;
	}

	public void setRequiredSteps(int requiredSteps) {
		this.requiredSteps = requiredSteps;
	}

	public int[][] getStepsMap() {
		return stepsMap;
	}

	public void setStepsMap(int[][] stepsMap) {
		this.stepsMap = stepsMap;
	}

}

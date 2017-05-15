package com.paulganly.staircase.calculator;

import com.paulganly.staircase.model.CalcluatedStepsResponse;

public interface StepsCalculator {

	public CalcluatedStepsResponse calculateSteps(int strideLength, Integer[] stairsFlights);
	
}

package com.paulganly.staircase.calculator;

import com.paulganly.staircase.model.CalcluatedStepsResponse;

public interface StepsCalculator {

	public boolean isValidInput(int strideLength, Integer[] stairsFlights);

	public CalcluatedStepsResponse calculateSteps(int strideLength, Integer[] stairsFlights);

}

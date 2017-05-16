package com.paulganly.staircase.calculator;

import com.paulganly.staircase.model.CalcluatedStepsResponse;

public interface StepsCalculator {

	/**
	 * A method used to validate the input to the steps calculator
	 *
	 * @param strideLength
	 * @param stairsFlights
	 * @return true when the request values are valid
	 */
	public boolean isValidInput(int strideLength, Integer[] stairsFlights);

	/**
	 * The method used to calculate the steps required to climb the staircase
	 *
	 * @param strideLength
	 * @param stairsFlights
	 * @return the response containing the steps needed and the steps map array
	 */
	public CalcluatedStepsResponse calculateSteps(int strideLength, Integer[] stairsFlights);

}

package com.paulganly.staircase.calculator;

import org.springframework.stereotype.Service;

import com.paulganly.staircase.model.CalcluatedStepsResponse;
import com.paulganly.staircase.model.FlightOfStairs;
import com.paulganly.staircase.model.Staircase;

@Service
public class StepsCalculatorImpl implements StepsCalculator {

	private static final int STEPS_REQUIRED_FOR_EACH_LANDING = 2;

	/* (non-Javadoc)
	 * @see com.paulganly.staircase.calculator.StepsCalculator#calculateSteps(int, java.lang.Integer[])
	 */
	@Override
	public CalcluatedStepsResponse calculateSteps(int strideLength, Integer[] stairsFlights) {
		CalcluatedStepsResponse response = new CalcluatedStepsResponse();
		int totalRequiredSteps = 0;
		totalRequiredSteps += getNumberOfStepsRequiredForLandings(stairsFlights);
		totalRequiredSteps += getNumberOfStepsRequiredForFlights(strideLength, stairsFlights);
		response.setRequiredSteps(totalRequiredSteps);
		response.setStepsMap(
				new Staircase(FlightOfStairs.getListOfStairsFlights(stairsFlights), strideLength).getStepsMap());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.paulganly.staircase.calculator.StepsCalculator#isValidInput(int, java.lang.Integer[])
	 */
	@Override
	public boolean isValidInput(int strideLength, Integer[] stairsFlights) {
		if (strideLength < 1 || strideLength > 4 || stairsFlights == null || stairsFlights.length < 1
				|| stairsFlights.length > 30) {
			return false;
		} else {
			for (Integer flight : stairsFlights) {
				if (flight == null || flight < 1 || flight > 20) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Gets the total number of steps required for the stairs flights, by first dividing the 
	 * steps per flight by the stride length. Then we get the modulus of the same calclation. If there
	 * is a remainder we add another steps to the total
	 * 
	 * @param strideLength
	 * @param stairsFlights
	 * @return the total steps for climbing the stairs without the landings
	 */
	private int getNumberOfStepsRequiredForFlights(int strideLength, Integer[] stairsFlights) {
		int totalSteps = 0;
		for (Integer flightSteps : stairsFlights) {
			totalSteps += flightSteps / strideLength;
			if (flightSteps % strideLength > 0) {
				totalSteps += 1;
			}
		}
		return totalSteps;
	}

	/**
	 * Calculate the number of steps for all landings by multiplying the number of steps per
	 * landing by the number of landings
	 * 
	 * @param stairsFlights
	 * @return an int, the number of steps for all landings
	 */
	private int getNumberOfStepsRequiredForLandings(Integer[] stairsFlights) {
		int numberOfLandings = stairsFlights.length - 1;
		return numberOfLandings * STEPS_REQUIRED_FOR_EACH_LANDING;
	}
}

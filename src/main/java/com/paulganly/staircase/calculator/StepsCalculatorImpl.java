package com.paulganly.staircase.calculator;

import org.springframework.stereotype.Service;

import com.paulganly.staircase.model.CalcluatedStepsResponse;

@Service
public class StepsCalculatorImpl implements StepsCalculator {

	@Override
	public CalcluatedStepsResponse calculateSteps(int strideLength, Integer[] stairsFlights) {
		CalcluatedStepsResponse response = new CalcluatedStepsResponse();
		response.setRequiredSteps(99);
		response.setStepsMap(new int[5][10]);
		return response;
	}

}

package com.paulganly.staircase.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.paulganly.staircase.calculator.StepsCalculator;

public class StepsCalculatorTest {

	StepsCalculator stepsCalculator = new StepsCalculatorImpl(); 
	
	@Test
	public void testCalculationResponse() {
		assertEquals("Steps must be 99", 99, stepsCalculator.calculateSteps(0, new Integer[10]).getRequiredSteps());
	}

}

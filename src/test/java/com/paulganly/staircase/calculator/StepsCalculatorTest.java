package com.paulganly.staircase.calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.paulganly.staircase.calculator.StepsCalculator;

public class StepsCalculatorTest {

	StepsCalculator stepsCalculator = new StepsCalculatorImpl();
	Integer[] flights = new Integer[5];
	int[][] stepsMap;

	@Before
	public void initialiseArrays(){
		flights[0] = 3;
		flights[1] = 3;
		flights[2] = 3;
		flights[3] = 3;
		flights[4] = 3;
	}

	@Test
	public void testCalculationResponse1() {
		flights = new Integer[]{17};
		int[] lastRow = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 6};
		assertEquals("Required steps must be 6", 6, stepsCalculator.calculateSteps(3, flights).getRequiredSteps());
		assertArrayEquals("Last row must be correct", lastRow, stepsCalculator.calculateSteps(3, flights).getStepsMap()[0]);
	}

	@Test
	public void testCalculationResponse2() {
		flights = new Integer[]{17, 17};
		int[] lastRow = new int[]{13, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertEquals("Required steps must be 14", 14, stepsCalculator.calculateSteps(3, flights).getRequiredSteps());
		assertArrayEquals("Last row must be correct", lastRow, stepsCalculator.calculateSteps(3, flights).getStepsMap()[0]);
	}

	@Test
	public void testCalculationResponse3() {
		flights = new Integer[]{4,9,8,11,7,20,14};
		int[] lastRow = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 47, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertEquals("Required steps must be 50", 50, stepsCalculator.calculateSteps(2, flights).getRequiredSteps());
		assertArrayEquals("Last row must be correct", lastRow, stepsCalculator.calculateSteps(2, flights).getStepsMap()[0]);
	}

	@Test
	public void validInputs1() {
		assertTrue("Minimun stride length can be 1", stepsCalculator.isValidInput(1, flights));
	}

	@Test
	public void validInputs2() {
		assertTrue("Maximum stride length can be 4", stepsCalculator.isValidInput(4, flights));
	}

	@Test
	public void validInputs3() {
		assertTrue("Minimum steps per flight can be 1", stepsCalculator.isValidInput(2, flights));
	}

	@Test
	public void validInputs4() {
		assertTrue("Maximum steps per flight can be 20", stepsCalculator.isValidInput(2, flights));
	}

	@Test
	public void validInputs5() {
		flights = new Integer[] {1};
		assertTrue("Minimum number of flights can be 1", stepsCalculator.isValidInput(3, flights));
	}

	@Test
	public void validInputs6() {
		flights = new Integer[] {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
		assertTrue("Maximum number of flights can be 30", stepsCalculator.isValidInput(3, flights));
	}

	@Test
	public void invalidInput1() {
		assertFalse("Step length must be greater than 0", stepsCalculator.isValidInput(0, flights));
	}

	@Test
	public void invalidInput2() {
		assertFalse("Step length must be less than 5", stepsCalculator.isValidInput(5, flights));
	}

	@Test
	public void invalidInput3() {
		assertFalse("Flights array cannot be empty", stepsCalculator.isValidInput(2, null));
	}

	@Test
	public void invalidInput4() {
		flights = new Integer[] {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1};
		assertFalse("Flights array cannot have more than 30 flights", stepsCalculator.isValidInput(2, flights));
	}

	@Test
	public void invalidInput5() {
		flights[2] = null;
		assertFalse("None of the flights can be null", stepsCalculator.isValidInput(2, flights));
	}

	@Test
	public void invalidInput6() {
		flights[2] = 0;
		assertFalse("All flights must have at least one step", stepsCalculator.isValidInput(2, flights));
	}

	@Test
	public void invalidInput7() {
		flights[2] = 21;
		assertFalse("All flights must have at most twenty steps", stepsCalculator.isValidInput(2, flights));
	}

}

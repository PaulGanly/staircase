/*package com.paulganly.staircase.api;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class CalculateStepsApiControllerTest {

	CalculateStepsApi stepsApi = new CalculateStepsApiController();
	Integer[] flights = new Integer[5];

	@Before
	public void initialise(){
		flights[0] = 3;
		flights[1] = 3;
		flights[2] = 3;
		flights[3] = 3;
		flights[4] = 3;
	}

	@Test
	public void testInvalidRequest1() {
		flights[2] = 0;
		assertEquals("One of the flights is empty", HttpStatus.BAD_REQUEST, stepsApi.calculateSteps(3, flights).getStatusCode());
	}

	@Test
	public void testInvalidRequest2() {
		assertEquals("Step length is 0", HttpStatus.BAD_REQUEST, stepsApi.calculateSteps(0, flights).getStatusCode());
	}

	@Test
	public void testInvalidRequest3() {
		flights = null;
		assertEquals("Flights array is null", HttpStatus.BAD_REQUEST, stepsApi.calculateSteps(3, flights).getStatusCode());
	}

	@Test
	public void testValidRequest1() {
		assertEquals("Required steps must be 6", HttpStatus.OK, stepsApi.calculateSteps(3, flights).getStatusCode());
	}

	@Test
	public void testValidRequest2() {
		flights = new Integer[]{17, 17};
		assertEquals("Required steps must be 6", HttpStatus.OK, stepsApi.calculateSteps(3, flights).getStatusCode());
	}

	@Test
	public void testValidRequest3() {
		flights = new Integer[]{4,9,8,11,7,20,14};
		assertEquals("Required steps must be 6", HttpStatus.OK, stepsApi.calculateSteps(2, flights).getStatusCode());
	}

}
*/
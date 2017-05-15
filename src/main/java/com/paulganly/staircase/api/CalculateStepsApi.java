package com.paulganly.staircase.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paulganly.staircase.model.CalcluatedStepsResponse;

public interface CalculateStepsApi {

	@GetMapping(value = "/calculate-steps", 
				produces = { "application/json" })
	ResponseEntity<Integer> showApi();

	@PostMapping(value = "/calculate-steps", 
				produces = { "application/json" }, 
				consumes = { "application/json" })
	ResponseEntity<CalcluatedStepsResponse> calculateSteps(int strideLength, Integer[] stairsFlights);

}
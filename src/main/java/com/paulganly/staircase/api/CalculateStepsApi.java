package com.paulganly.staircase.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paulganly.staircase.model.CalculateStepsRequest;

public interface CalculateStepsApi {

	@PostMapping(	value = "/calculate-steps", 
					produces = { "application/json" }, 
					consumes = { "application/x-www-form-urlencoded" })
	ResponseEntity<Integer> calculateSteps(@ModelAttribute CalculateStepsRequest calculateStepsRequest);

}

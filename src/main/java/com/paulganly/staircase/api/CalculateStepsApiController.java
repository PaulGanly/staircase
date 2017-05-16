package com.paulganly.staircase.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paulganly.staircase.calculator.StepsCalculator;
import com.paulganly.staircase.model.CalcluatedStepsResponse;

@Controller
public class CalculateStepsApiController implements CalculateStepsApi {

	@Autowired
	StepsCalculator stepsCalculator;

	@RequestMapping(value = "/calculate-steps", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> showApi() {
		// TODO Implement this
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

	@RequestMapping(value = "/calculate-steps", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CalcluatedStepsResponse> calculateSteps(
			@RequestParam(value = "strideLength", required = true) final int strideLength,
			@RequestBody final Integer[] stairsFlights) {

		if(stepsCalculator.isValidInput(strideLength, stairsFlights)){
			CalcluatedStepsResponse response = stepsCalculator.calculateSteps(strideLength, stairsFlights);
			return new ResponseEntity<CalcluatedStepsResponse>(response, HttpStatus.OK);
		}else{
			return new ResponseEntity<CalcluatedStepsResponse>(new CalcluatedStepsResponse(), HttpStatus.BAD_REQUEST);
		}

	}
}

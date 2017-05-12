package com.paulganly.staircase.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.paulganly.staircase.model.CalculateStepsRequest;

@Controller
public class CalculateStepsApiController implements CalculateStepsApi {

	@Override
	public ResponseEntity<Integer> calculateSteps(@ModelAttribute CalculateStepsRequest calculateStepsRequest) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

}

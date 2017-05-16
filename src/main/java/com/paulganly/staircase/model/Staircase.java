package com.paulganly.staircase.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class Staircase {

	private List<FlightOfStairs> stairsFlights;
	private int strideLength;
	private int[][] stepsMap;

	public Staircase(List<FlightOfStairs> stairsFlights, int strideLength) {
		super();
		this.stairsFlights = stairsFlights;
		this.strideLength = strideLength;
		generateStepArrayMap();
	}

	private void generateStepArrayMap() {
		List<int[][]> stepMapList = new ArrayList<>();
		for (FlightOfStairs flight : stairsFlights) {
			int[][] stepMap = createEmptyStepMapForFlight(flight);
			stepMap = inputStepsIntoMap(flight, stepMap);
			stepMapList.add(0, stepMap);
		}
		setStepsMap(countEachStepFromBottom(zeroPadEachRow(stepMapList, stairsFlights)));
	}

	private int[][] createEmptyStepMapForFlight(FlightOfStairs flight) {
		int height = 0;
		int width = 0;

		if (flight.isHasLanding()) {
			height = flight.getNumberOfSteps() + 1;
			width = flight.getNumberOfSteps() + 1;
		} else if (flight.isLastFlight()) {
			height = flight.getNumberOfSteps();
			width = flight.getNumberOfSteps() + 1;
		}

		return new int[height][width];
	}

	private int[][] inputStepsIntoMap(FlightOfStairs flight, int[][] stepMap) {
		int modifyColumnAfterEachStep;
		int height = stepMap.length;
		int width = stepMap[0].length;

		if (flight.isForwardFacing()) {
			modifyColumnAfterEachStep = 1;
		} else {
			modifyColumnAfterEachStep = -1;
		}

		int currentRow = height - 1;
		int currentStep = 1;
		int currentColumn = 0;
		if (!flight.isForwardFacing()) {
			currentColumn = width - 1;
		}

		for (int i = 0; i < flight.getNumberOfSteps(); i++) {
			if ((currentStep % this.getStrideLength()) > 0) {
				stepMap[currentRow][currentColumn] = -1;
			} else {
				stepMap[currentRow][currentColumn] = 1;
			}
			currentRow += 1;
			currentStep += 1;
			currentColumn += modifyColumnAfterEachStep;
		}

		addLandingIfNeeded(flight, stepMap, width);
		addLastStepIfNeeded(flight, stepMap, width);

		return stepMap;
	}

	private int[][] zeroPadEachRow(List<int[][]> stepMapList, List<FlightOfStairs> stairsFlights) {
		int currentArrayWidth = 0;
		int proceedingArrayWidth = 0;
		int[][]combinedArray = stepMapList.get(0);
		
		for(int i = 0; i < stairsFlights.size(); i++){
			
			currentArrayWidth = combinedArray[0].length;
			proceedingArrayWidth = stepMapList.get(i+1)[0].length;
			
			if(proceedingArrayWidth > currentArrayWidth){
				
				int difference = proceedingArrayWidth - currentArrayWidth;
				int[] addedArray = new int[difference];
				
				for(int j = 0; j < combinedArray[0].length; i++){
					
					if(stairsFlights.get(i).isForwardFacing()){
						combinedArray[j] = ArrayUtils.addAll(addedArray, combinedArray[j]);
					}else{
						combinedArray[j] = ArrayUtils.addAll(combinedArray[j], addedArray);
					}
				}		
			}
			
			combinedArray = append(stepMapList.get(i+1), combinedArray);
			
		}
		return combinedArray;
	}
	
	public static int[][] append(int[][] a, int[][] b) {
        int[][] result = new int[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

	private int[][] countEachStepFromBottom(int[][] stepsMap) {
		int currentCount = 1;
		for(int i = 0; i < stepsMap.length ; i++){
			for(int j = 0; j < stepsMap[i].length ; j++){
				if(stepsMap[i][j] > 0){
					stepsMap[i][j] = currentCount;
					currentCount ++;
				}	
			}
		}
		return stepsMap;
	}

	private void addLastStepIfNeeded(FlightOfStairs flight, int[][] stepMap, int width) {
		if (flight.isLastFlight() && flight.getNumberOfSteps() % this.getStrideLength() > 0) {
			if (flight.isForwardFacing()) {
				stepMap[0][width - 1] = 1;
			} else {
				stepMap[0][0] = 1;
			}
		}
	}

	private void addLandingIfNeeded(FlightOfStairs flight, int[][] stepMap, int width) {
		if (flight.isHasLanding()) {
			if (flight.isForwardFacing()) {
				stepMap[0][width - 1] = 1;
				stepMap[0][width - 2] = 1;
			} else {
				stepMap[0][0] = 1;
				stepMap[0][1] = 1;
			}
		}
	}

	public List<FlightOfStairs> getStairsFlights() {
		return stairsFlights;
	}

	public void setStairsFlights(List<FlightOfStairs> stairsFlights) {
		this.stairsFlights = stairsFlights;
	}

	public int getStrideLength() {
		return strideLength;
	}

	public void setStrideLength(int strideLength) {
		this.strideLength = strideLength;
	}

	public int[][] getStepsMap() {
		return stepsMap;
	}

	public void setStepsMap(int[][] stepsMap) {
		this.stepsMap = stepsMap;
	}

}

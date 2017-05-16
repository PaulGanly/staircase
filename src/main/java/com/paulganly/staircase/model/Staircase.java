package com.paulganly.staircase.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Staircase {

	private List<FlightOfStairs> stairsFlights;
	private int strideLength;
	private int[][] stepsMap;

	private static final Integer USED_STEP_IN_MAP = 1;
	private static final Integer UNUSED_STEP_IN_MAP = -1;
	private static final Integer EMPTY_FIELD_IN_MAP = 0;

	public Staircase(List<FlightOfStairs> stairsFlights, int strideLength) {
		super();
		this.stairsFlights = stairsFlights;
		this.strideLength = strideLength;
		generateStepArrayMap();
	}

	/**
	 *
	 */
	private void generateStepArrayMap() {

		List<List<Integer>> stepMapList = new ArrayList<>();
		for (FlightOfStairs flight : stairsFlights) {
			List<List<Integer>> stepMap = createRowsListGivenFlight(flight, strideLength);
			if(!stepMapList.isEmpty()){
				zeroPadEachRowOfFlight(stepMapList, stepMap, flight);
			}
			stepMapList.addAll(0, stepMap);
		}
		setStepsMap(convertToIntArray(countEachStepFromBottom(stepMapList, stairsFlights)));
	}

	/**
	 *
	 * @param stepMapList
	 * @return
	 */
	private int[][] convertToIntArray(List<List<Integer>> stepMapList) {
		int columns = stepMapList.get(0).size();
		int rows = stepMapList.size();

		int[][] stepMap = new int[rows][columns];
		int rowCount = 0;

		for(List<Integer> row: stepMapList){
			int columnCount = 0;
			for(Integer column: row){
				stepMap[rowCount][columnCount] = column;
				columnCount ++;
			}
			rowCount ++;
		}

		return stepMap;
	}

	/**
	 *
	 * @param stepMapList
	 * @param stairsFlights
	 * @return
	 */
	private List<List<Integer>> countEachStepFromBottom(List<List<Integer>> stepMapList, List<FlightOfStairs> stairsFlights) {
		int currentCount = 1;
		int lastRowIndex = stepMapList.size() - 1;
		for(int i = lastRowIndex; i >= 0; i--){
			int lastColumnindex = stepMapList.get(i).size() - 1;
			for(int j = 0; j <= lastColumnindex; j++){
				if(USED_STEP_IN_MAP.equals(stepMapList.get(i).get(j))){
					stepMapList.get(i).set(j, currentCount);
					currentCount ++;
				}
			}
		}
		return stepMapList;
	}

	/**
	 *
	 * @param stepMapList
	 * @param currentFlightMap
	 * @param flight
	 */
	private void zeroPadEachRowOfFlight(List<List<Integer>> stepMapList, List<List<Integer>> currentFlightMap, FlightOfStairs flight) {
		int previousFlightsWidth = stepMapList.get(0).size();
		int endIndexOfPreviousFlight = getLastIndexOfPreviousFlight(stepMapList);
		int offsetNeeded = getOffset(endIndexOfPreviousFlight, flight, previousFlightsWidth);

		if(offsetNeeded > 0){
			addOffsetsToNewRows(offsetNeeded, currentFlightMap, flight);
		}

		int currentFlightWidth = currentFlightMap.get(0).size();

		if(currentFlightWidth > previousFlightsWidth){
			int difference = currentFlightWidth - previousFlightsWidth;
			if(flight.isForwardFacing()){
				stepMapList = padEndOfAllRows(difference, stepMapList);
			}else{
				stepMapList = padStartOfAllRows(difference, stepMapList);
			}
		}else if(currentFlightWidth < previousFlightsWidth){
			int difference =  previousFlightsWidth - currentFlightWidth;
			if(flight.isForwardFacing()){
				currentFlightMap = padEndOfAllRows(difference, currentFlightMap);
			}else{
				currentFlightMap = padStartOfAllRows(difference, currentFlightMap);
			}
		}

	}

	/**
	 *
	 * @param offset
	 * @param currentFlightMap
	 * @param flight
	 */
	private void addOffsetsToNewRows(int offset, List<List<Integer>> currentFlightMap, FlightOfStairs flight) {
		if(flight.isForwardFacing()){
			currentFlightMap = padStartOfAllRows(offset, currentFlightMap);
		}else{
			currentFlightMap = padEndOfAllRows(offset, currentFlightMap);
		}
	}

	/**
	 *
	 * @param endIndexOfPreviousFlight
	 * @param currentFlight
	 * @param previousFlightsWidth
	 * @return
	 */
	private int getOffset(int endIndexOfPreviousFlight, FlightOfStairs currentFlight, int previousFlightsWidth) {
		int offset = 0;
		boolean previousFlightFacedForward = !currentFlight.isForwardFacing();
		if(previousFlightFacedForward){
			offset = previousFlightsWidth - endIndexOfPreviousFlight;
		}else{
			offset = endIndexOfPreviousFlight + 1;
		}
		return offset;
	}

	/**
	 *
	 * @param stepMapList
	 * @return
	 */
	private int getLastIndexOfPreviousFlight(List<List<Integer>> stepMapList) {
		List<Integer> lastRow = stepMapList.get(0);
		int indexCounter = 0;
		for(Integer values: lastRow){
			if(values > 0){
				return indexCounter;
			}
			indexCounter++;
		}
		return indexCounter;
	}

	/**
	 *
	 * @param difference
	 * @param stepMapList
	 * @return
	 */
	private List<List<Integer>> padStartOfAllRows(int difference, List<List<Integer>> stepMapList) {
		List<Integer> zeroesList = new ArrayList<Integer>(Collections.nCopies(difference, 0));

		for(List<Integer> rows: stepMapList){
			rows.addAll(0, zeroesList);
		}

		return stepMapList;
	}

	/**
	 *
	 * @param difference
	 * @param stepMapList
	 * @return
	 */
	private List<List<Integer>> padEndOfAllRows(int difference, List<List<Integer>> stepMapList) {
		List<Integer> zeroesList = new ArrayList<Integer>(Collections.nCopies(difference, 0));

		for(List<Integer> rows: stepMapList){
			rows.addAll(zeroesList);
		}
		return stepMapList;
	}

	/**
	 *
	 * @param flight
	 * @param strideLength
	 * @return
	 */
	private List<List<Integer>> createRowsListGivenFlight(FlightOfStairs flight, int strideLength){
		List<List<Integer>> rowList = new ArrayList<>();
		int rows = getRowsRequiredForFlight(flight);
		int columns = getColumnsRequiredForFlight(flight);

		rowList = fillEachRowForFlight(rows, columns, flight, strideLength);

		if (!flight.isForwardFacing()) {
			reverseEachRow(rowList);
		}

		return rowList;
	}

	/**
	 *
	 * @param rowList
	 */
	private void reverseEachRow(List<List<Integer>> rowList) {
		for(List<Integer> row: rowList){
			Collections.reverse(row);
		}
	}

	/**
	 *
	 * @param rows
	 * @param columns
	 * @param flight
	 * @param strideLength
	 * @return
	 */
	private List<List<Integer>> fillEachRowForFlight(int rows, int columns, FlightOfStairs flight, int strideLength) {
		List<List<Integer>> rowList = new ArrayList<>();
		int nextColumnToPlaceStep = 0;

		for(int row = 0; row < rows; row++){
			List<Integer> thisRow = new ArrayList<>();

			for(int column = 0; column < columns; column++){
				if(nextColumnToPlaceStep == column){
					if(((column+1)%strideLength) == 0){
						thisRow.add(USED_STEP_IN_MAP);
					}else{
						thisRow.add(UNUSED_STEP_IN_MAP);
					}
				}else{
					thisRow.add(EMPTY_FIELD_IN_MAP);
				}
			}
			nextColumnToPlaceStep ++;
			rowList.add(0, thisRow);
		}

		if(flight.isHasLanding()){
			rowList.get(0).set(columns-1, USED_STEP_IN_MAP);
			rowList.get(0).set(columns-2, USED_STEP_IN_MAP);
		}

		if(flight.isLastFlight()){
			rowList.get(0).set(columns-1, USED_STEP_IN_MAP);
		}

		return rowList;
	}

	/**
	 *
	 * @param flight
	 * @return
	 */
	private int getColumnsRequiredForFlight(FlightOfStairs flight) {
		int width = 0;

		if (flight.isHasLanding()) {
			width = flight.getNumberOfSteps() + 2;
		} else if (flight.isLastFlight()) {
			width = flight.getNumberOfSteps() + 1;
		}

		return width;
	}

	/**
	 *
	 * @param flight
	 * @return
	 */
	private int getRowsRequiredForFlight(FlightOfStairs flight) {
		int height = 0;

		if (flight.isHasLanding()) {
			height = flight.getNumberOfSteps() + 1;
		} else if (flight.isLastFlight()) {
			height = flight.getNumberOfSteps();
		}

		return height;
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

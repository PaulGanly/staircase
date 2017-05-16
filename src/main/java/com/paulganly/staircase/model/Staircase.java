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
	 * This method generates a list 2d array of integers that represents the stairs. This map of the
	 * staircase is returned to the front-end.
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
	 * This method creates a list of row integers that represents the stairs.
	 * 
	 * @param flight
	 * @param strideLength
	 * @return the stairs step map
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
	 * We must zero pad each of the rows so that they are all the same length. We must also add
	 * zero padding to each of the rows where the start of the current flight will not meet the end of
	 * the previous
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
	 * Takes the list of the rows of integers of our steps map and convers them to a 2D array,
	 * ready to be used in the API response.
	 *
	 * @param stepMapList
	 * @return a 2d array of the steps map
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
	 * Cycles through the rows of the steps map. For each element we come across that equals 1 (i.e. the steps that have been used)
	 * , we convert it's value to the step number.
	 * 
	 * @param stepMapList
	 * @param stairsFlights
	 * @return the steps map with the steps count converted to the correct value
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
	 * We want the new flight of steps to meet the point were the previous ones ended. Therefore,
	 * we must add an offset to either the start or end of the rows so that the starting point
	 * of the new flight is push left or right to meet the end of the previous flight.
	 * 
	 * @param offset
	 * @param currentFlightMap
	 * @param the new flight with an offset added
	 */
	private void addOffsetsToNewRows(int offset, List<List<Integer>> currentFlightMap, FlightOfStairs flight) {
		if(flight.isForwardFacing()){
			currentFlightMap = padStartOfAllRows(offset, currentFlightMap);
		}else{
			currentFlightMap = padEndOfAllRows(offset, currentFlightMap);
		}
	}

	/**
	 * Calculates the required offset for the current row (i.e. the number of zeroes to pad the row with)
	 * 
	 * @param endIndexOfPreviousFlight
	 * @param currentFlight
	 * @param previousFlightsWidth
	 * @return the number of zeroes we must pad the row with so that the start of this flight meets the
	 * end of the last
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
	 * Get the index (or column in the map) where previous flight of stairs ended, so that
	 * we can match the start of the next flight to this point.
	 * 
	 * @param stepMapList
	 * @return the index where previous flight of stairs ended 
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
	 * A method which adds a padding of zeroes to the start of each row supplied. The number of
	 * zeroes added is given by the difference value.
	 * 
	 * @param difference
	 * @param stepMapList
	 * @return stepsMapList that has a padding of zeroes added to the start of each row
	 */
	private List<List<Integer>> padStartOfAllRows(int difference, List<List<Integer>> stepMapList) {
		List<Integer> zeroesList = new ArrayList<Integer>(Collections.nCopies(difference, 0));

		for(List<Integer> rows: stepMapList){
			rows.addAll(0, zeroesList);
		}

		return stepMapList;
	}

	/**
	 * A method which adds a padding of zeroes to the end of each row supplied. The number of
	 * zeroes added is given by the difference value.
	 * 
	 * @param difference
	 * @param stepMapList
	 * @return stepsMapList that has a padding of zeroes added to the end of each row
	 */
	private List<List<Integer>> padEndOfAllRows(int difference, List<List<Integer>> stepMapList) {
		List<Integer> zeroesList = new ArrayList<Integer>(Collections.nCopies(difference, 0));

		for(List<Integer> rows: stepMapList){
			rows.addAll(zeroesList);
		}
		return stepMapList;
	}

	/**
	 * Every alternative flight of stairs must face the opposite direction, to achieve this
	 * we reverse each of the rows of the stairs flight.
	 * 
	 * @param rowList
	 */
	private void reverseEachRow(List<List<Integer>> rowList) {
		for(List<Integer> row: rowList){
			Collections.reverse(row);
		}
	}

	/**
	 * For the give fight of stairs we create a list of rows of integers that represent the steps of the
	 * stairs. The elements of each of the rows falls under three categories: 1) steps that have been stepped 
	 * on by the user  2) steps that have been skipped by the user  3) empty blocks. These are represented
	 * by different numbers 1, -1, 0 respectively. 
	 * 
	 * This method cycles through each of the rows and fills in the values for each element given the
	 * stairs size and the length of stride of the user.
	 * 
	 * @param rows
	 * @param columns
	 * @param flight
	 * @param strideLength
	 * @return the list of rows of integers (the steps map for this flight of stairs)
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
	 * Calculates the columns in the steps map required of the given flight of stairs.
	 * Flights with a landing at the end require more columns.
	 * 
	 * @param flight
	 * @return the number of columns required
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
	 * Calculates the rows in the steps map required of the given flight of stairs.
	 * Flights with a landing at the end require an extra row.
	 * 
	 * @param flight
	 * @return the number of rows required
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

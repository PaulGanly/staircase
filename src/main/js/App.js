'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const Fetch = require('whatwg-fetch');
const BootStrap = require('react-bootstrap');
const StepsPanel = require('./StepsPanel').default;
const StridesPanel = require('./StridesPanel').default;
const ResultsModal = require('./ResultsModal').default;
const BaseUrl = "/calculate-steps";

class StairsStepsRow {
    constructor(rowNumber) {
        this.id = rowNumber;
        this.steps = 1;
    }
}

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            stepsArray: [new StairsStepsRow(1)],
            strideLength: 1,
            stairsFlights: 1,
            responseStatus: null,
            requiredSteps: null,
            stepsMap: null,
            showModal: false
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.increaseStairsFlights = this.increaseStairsFlights.bind(this);
        this.decreaseStairsFlights = this.decreaseStairsFlights.bind(this);
        this.increaseStride = this.increaseStride.bind(this);
        this.decreaseStride = this.decreaseStride.bind(this);
        this.increaseStepsArrayValue = this.increaseStepsArrayValue.bind(this);
        this.decreaseStepsArrayValue = this.decreaseStepsArrayValue.bind(this);
        this.hideResultsModal = this.hideResultsModal.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        var that = this;
        var status;
        var stepsRequired;
        var stepsMapped;
        var requestUrl = this.buildRequestUrl();
        var requestBody = this.buildRequestBody();
        
        fetch(requestUrl, {
    	  method: 'POST',
    	  headers: {
    	    'Content-Type': 'application/json'
    	  },
    	  body: requestBody
    	})
        .then((response) => {
            this.responseStatus = response.status;
            return response.json()
        })
        .then((responseData) => { 
            this.stepsRequired = responseData.requiredSteps;
            this.stepsMapped = responseData.stepsMap;
            that.updateStateWithResponse(this.status, this.stepsRequired, this.stepsMapped);
        });
        
    }

    updateStateWithResponse(status, stepsRequired, stepsMapped){
        this.setState({
            responseStatus: status,
            requiredSteps: stepsRequired,
            stepsMap: stepsMapped,
            showModal: true
        });
    }
    
    buildRequestUrl() {
        return BaseUrl + "?strideLength=" + this.state.strideLength;
    }
    
    buildRequestBody(){
    	var stepsIntegerArray = [];
    	var arrayLength = this.state.stepsArray.length;
    	for (var i = 0; i < arrayLength; i++) {
    		stepsIntegerArray.push(this.state.stepsArray[i].steps);
    	}
    	return JSON.stringify(stepsIntegerArray);
    }

    increaseStairsFlights() {
        this.setState({
            stepsArray: this.state.stepsArray.concat(new StairsStepsRow(this.state.stairsFlights + 1)),
            stairsFlights: (this.state.stairsFlights + 1)
        });
    }

    decreaseStairsFlights() {
        this.setState({
            stairsFlights: this.state.stairsFlights - 1,
            stairsFlightsArray: this.state.stepsArray.splice(-1, 1)
        });
    }

    increaseStride() {
        this.setState({
            strideLength: this.state.strideLength + 1
        });
    }

    decreaseStride() {
        this.setState({
            strideLength: this.state.strideLength - 1
        });
    }

    increaseStepsArrayValue(rowId) {
        const currentArray = this.state.stepsArray;
        currentArray[rowId].steps = currentArray[rowId].steps + 1;
        this.setState({
            stepsArray: currentArray
        });
    }

    decreaseStepsArrayValue(rowId) {
        const currentArray = this.state.stepsArray;
        currentArray[rowId].steps = currentArray[rowId].steps - 1;
        this.setState({
            stepsArray: currentArray
        });
    }

    hideResultsModal(){
        this.setState({
            showModal: false
        });
    }

    render() {
        return ( 
          <BootStrap.Grid>
            <BootStrap.Jumbotron>
              <h1>Staircase Steps Calculator</h1>
              <p>An application that calculates the minimum number of steps needed to ascend a staircase.</p>
              <BootStrap.Row>
                <form onSubmit={this.handleSubmit}>
                  <BootStrap.Grid>
                    <StepsPanel  											
                          stairsFlights={this.state.stairsFlights}
                          stepsArray={this.state.stepsArray}
                          relayArrayValuesIncreased={this.increaseStepsArrayValue}
                          relayArrayValuesDecreased={this.decreaseStepsArrayValue}
                          passDecreaseStairsClick={this.decreaseStairsFlights} 
                          passIncreaseStairsClick={this.increaseStairsFlights}>...</StepsPanel>
                    <StridesPanel 
                          strideLength={this.state.strideLength} 
                          passDecreaseStrideClick={this.decreaseStride} 
                          passIncreaseStrideClick={this.increaseStride}>...</StridesPanel>
                  </BootStrap.Grid>
                  <BootStrap.Col xs={4} xsOffset={8}>
                    <BootStrap.Button bsStyle="primary" bsSize="large" type="submit">Submit</BootStrap.Button>
                  </BootStrap.Col> 
                </form>
              </BootStrap.Row>
              <ResultsModal 
                        responseStatus={this.state.responseStatus}
                        requiredSteps={this.state.requiredSteps}
                        stepsMap={this.state.stepsMap}
                        showModal={this.state.showModal}
                        closeResultsModal={this.hideResultsModal}>...</ResultsModal> 
            </BootStrap.Jumbotron>
		    </BootStrap.Grid>
        );
    }
}

ReactDOM.render( 
    <App / > ,
    document.getElementById('react')
)
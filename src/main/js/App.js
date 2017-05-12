'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const BootStrap = require('react-bootstrap');
const StepsPanel = require('./StepsPanel').default;
const StridesPanel = require('./StridesPanel').default;

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
            stairsFlights: 1
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.increaseStairsFlights = this.increaseStairsFlights.bind(this);
        this.decreaseStairsFlights = this.decreaseStairsFlights.bind(this);
        this.increaseStride = this.increaseStride.bind(this);
        this.decreaseStride = this.decreaseStride.bind(this);
        this.increaseStepsArrayValue = this.increaseStepsArrayValue.bind(this);
        this.decreaseStepsArrayValue = this.decreaseStepsArrayValue.bind(this);
    }

    handleSubmit(event) {
        alert('A stairsFlights was submitted: ' + this.state.stepsArray + 'With stride length: ' + this.state.strideLength);
        event.preventDefault();
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
                  <BootStrap.Col xs={4} xsOffset={4}>
                    <BootStrap.Button bsStyle="primary" bsSize="large" type="submit">Submit</BootStrap.Button>
                  </BootStrap.Col>  
                </form>
              </BootStrap.Row>
              <BootStrap.Row>
                Place for results
              </BootStrap.Row>
            </BootStrap.Jumbotron>
		      </BootStrap.Grid>
        );
    }
}

ReactDOM.render( <
    App / > ,
    document.getElementById('react')
)
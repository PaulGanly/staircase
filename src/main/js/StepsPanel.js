'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const BootStrap = require('react-bootstrap');

class StepsPanel extends React.Component {
    constructor(props) {
        super(props);

        this.handleDecreaseStairsFlights = this.handleDecreaseStairsFlights.bind(this);
        this.handleIncreaseStairsFlights = this.handleIncreaseStairsFlights.bind(this);
        this.passDecreaseStepsForFlight = this.passDecreaseStepsForFlight.bind(this);
		this.passIncreaseStepsForFlight = this.passIncreaseStepsForFlight.bind(this);
    }

    handleDecreaseStairsFlights() {
        if (this.props.stairsFlights > 1) {
            this.props.passDecreaseStairsClick();
        }
    }

    handleIncreaseStairsFlights() {
        if (this.props.stairsFlights < 30) {
            this.props.passIncreaseStairsClick();
        }
    }

	handleClick(stepsRow, action, event) {
		const MIN_STEPS = 1;
		const MAX_STEPS = 20;

		if("DECREASE" == action && stepsRow.steps > MIN_STEPS){
			this.passDecreaseStepsForFlight((stepsRow.id -1));
		}else if ("INCREASE" == action && stepsRow.steps < MAX_STEPS){
			this.passIncreaseStepsForFlight((stepsRow.id -1));
		}
	}

    passDecreaseStepsForFlight(arrayIndex) {
        this.props.relayArrayValuesDecreased(arrayIndex);
    }

	passIncreaseStepsForFlight(arrayIndex) {
        this.props.relayArrayValuesIncreased(arrayIndex);
    }

    render() {	

		const stepsInputs = this.props.stepsArray.map((stepsRow) => {
			return ( 
			<div>
				<BootStrap.ControlLabel>Flight Number {stepsRow.id}</BootStrap.ControlLabel>
				<BootStrap.InputGroup>
					<BootStrap.InputGroup.Button>
						<BootStrap.Button key={stepsRow.id} onClick={this.handleClick.bind(this, stepsRow, "DECREASE")}>
							<BootStrap.Glyphicon glyph="minus"></BootStrap.Glyphicon>
						</BootStrap.Button>
					</BootStrap.InputGroup.Button> 
					<BootStrap.FormControl key={stepsRow.id} type="text" value={stepsRow.steps} />
					<BootStrap.InputGroup.Button>
						<BootStrap.Button key={stepsRow.id} onClick={this.handleClick.bind(this, stepsRow, "INCREASE" )}>
							<BootStrap.Glyphicon glyph="plus"></BootStrap.Glyphicon>
						</BootStrap.Button>
					</BootStrap.InputGroup.Button>
				</BootStrap.InputGroup>
				<BootStrap.HelpBlock>A number between 1 and 30</BootStrap.HelpBlock>
			</div>
			);
		});

        return (
		<BootStrap.Col xs={12} md={6}>
			<BootStrap.Panel header="Describe the stairs">
				<BootStrap.Image src="/steps.png" rounded />
				<BootStrap.Row>
					<BootStrap.Col xs={10} xsOffset={1}>
						<BootStrap.ControlLabel>Specify the number of flights of stairs:</BootStrap.ControlLabel>
						<BootStrap.InputGroup>
							<BootStrap.InputGroup.Button>
								<BootStrap.Button onClick={this.handleDecreaseStairsFlights}>
									<BootStrap.Glyphicon glyph="minus"></BootStrap.Glyphicon>
								</BootStrap.Button>
							</BootStrap.InputGroup.Button> 
							<BootStrap.FormControl type="text"  value={this.props.stairsFlights} />
							<BootStrap.FormControl.Feedback />
							<BootStrap.InputGroup.Button>
								<BootStrap.Button onClick={this.handleIncreaseStairsFlights}>
									<BootStrap.Glyphicon glyph="plus"></BootStrap.Glyphicon>
								</BootStrap.Button>
							</BootStrap.InputGroup.Button>
						</BootStrap.InputGroup>
						<BootStrap.HelpBlock>Must be a number between 1 and 30</BootStrap.HelpBlock>
					</BootStrap.Col>
				</BootStrap.Row>
				<BootStrap.Row>
					<BootStrap.Col xs={10} xsOffset={1}>
						<BootStrap.ControlLabel>Specify the number of steps on each flight of stairs:</BootStrap.ControlLabel>
					</BootStrap.Col>
				</BootStrap.Row>
				<BootStrap.Row>
					<BootStrap.Col xs={8} xsOffset={2}>
						{stepsInputs}
					</BootStrap.Col>
				</BootStrap.Row>
			</BootStrap.Panel>
		</BootStrap.Col>
        );
    }
}

export default StepsPanel;
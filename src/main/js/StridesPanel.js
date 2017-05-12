'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const BootStrap = require('react-bootstrap');

class StridesPanel extends React.Component {
    constructor(props) {
        super(props);
        this.handleDecreaseStrideLength = this.handleDecreaseStrideLength.bind(this);
        this.handleIncreaseStrideLength = this.handleIncreaseStrideLength.bind(this);
    }

    handleDecreaseStrideLength() {
        if (this.props.strideLength > 1) {
            this.props.passDecreaseStrideClick();
        }
    }

    handleIncreaseStrideLength() {
        if (this.props.strideLength < 4) {
            this.props.passIncreaseStrideClick();
        }
    }

    render() {
        return (
			<BootStrap.Col xs={12} md={6}>
				<BootStrap.Panel header="Describe the length of the persons stride">
					<BootStrap.Image src="/stride.png" rounded />
					<BootStrap.Row>
						<BootStrap.Col xs={10} xsOffset={1}>
							<BootStrap.ControlLabel>Specify the maximum number of steps covered with each stride:</BootStrap.ControlLabel>
							<BootStrap.InputGroup>
								<BootStrap.InputGroup.Button>
									<BootStrap.Button  onClick={this.handleDecreaseStrideLength}>
										<BootStrap.Glyphicon glyph="minus"></BootStrap.Glyphicon>
									</BootStrap.Button>
								</BootStrap.InputGroup.Button> 
								<BootStrap.FormControl type="text" value={this.props.strideLength} />
								<BootStrap.InputGroup.Button>
									<BootStrap.Button onClick={this.handleIncreaseStrideLength}>
										<BootStrap.Glyphicon glyph="plus"></BootStrap.Glyphicon>
									</BootStrap.Button>
								</BootStrap.InputGroup.Button>
							</BootStrap.InputGroup>
							<BootStrap.HelpBlock>Must be a number between 1 and 4 inclusive</BootStrap.HelpBlock>
						</BootStrap.Col>
					</BootStrap.Row>
				</BootStrap.Panel>
			</BootStrap.Col>
        );
    }
}

export default StridesPanel;
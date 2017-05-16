'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const BootStrap = require('react-BootStrap');

class ResultsModal extends React.Component {
    constructor(props) {
        super(props);
		this.handleCloseModalClick = this.handleCloseModalClick.bind(this);
    }

	handleCloseModalClick() {
    	this.props.closeResultsModal();
	}

    render() {
        return (
			<BootStrap.Modal show={this.props.showModal} onHide={this.handleCloseModalClick}>
				<BootStrap.Modal.Header closeButton>
					<BootStrap.Modal.Title>Steps Calculator Results</BootStrap.Modal.Title>
				</BootStrap.Modal.Header>
				<BootStrap.Modal.Body>
					<h4>Number of steps required:</h4>
					<h5>{this.props.requiredSteps}</h5>
				</BootStrap.Modal.Body>
				<BootStrap.Modal.Footer>
					<BootStrap.Button onClick={this.handleCloseModalClick}>Close</BootStrap.Button>
				</BootStrap.Modal.Footer>
			</BootStrap.Modal>
        );
    }
}

export default ResultsModal;

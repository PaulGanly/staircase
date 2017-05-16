'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const BootStrap = require('react-BootStrap');

class StepsMapElement {
    constructor(cssClass, stepNumber) {
        this.cssClass = cssClass;
        this.stepNumber = stepNumber;
    }
}

class ResultsModal extends React.Component {
    constructor(props) {
        super(props);
		this.state = {
			mapOpen: false
		};
		this.handleCloseModalClick = this.handleCloseModalClick.bind(this);
    }

	handleCloseModalClick() {
    	this.props.closeResultsModal();
	}

    render() {
		var stepMapRowList = [];

		if(this.props.stepsMap != null){
			var stepMap = this.props.stepsMap;

			for(var i = 0; i < stepMap.length; i++) {
				var stepMapRow = [];
				var stepRow = stepMap[i];
				for(var j = 0; j < stepRow.length; j++) {
					console.log("stepMap[" + i + "][" + j + "] = " + stepRow[j]);
					if(stepRow[j] == 0){
						stepMapRow.push(new StepsMapElement('emptyBlock', ' '));
					}else if(stepRow[j] < 0){
						stepMapRow.push(new StepsMapElement('unusedStep', ' '));
					}else{
						stepMapRow.push(new StepsMapElement('usedStep', stepRow[j]));
					}
				}

				stepMapRowList.push(stepMapRow);
			}
		}

		const stepsMap = stepMapRowList.map(function(rowFromList) {	
			var stepsMapRow = rowFromList.map(function(stepElements) {
				return (
					<td className={stepElements.cssClass}>{stepElements.stepNumber}</td>
				);
			});
			return (
				<tr>{stepsMapRow}</tr>
			);
		});

        return (
			<BootStrap.Modal show={this.props.showModal} onHide={this.handleCloseModalClick}>
				<BootStrap.Modal.Header closeButton>
					<BootStrap.Modal.Title>Steps Calculator Results</BootStrap.Modal.Title>
				</BootStrap.Modal.Header>
				<BootStrap.Modal.Body>
					<h4>Number of steps required:</h4>
					<h5>{this.props.requiredSteps}</h5>
					<div>
					<BootStrap.Button onClick={ ()=> this.setState({ mapOpen: !this.state.mapOpen })}>Show Map</BootStrap.Button>
					<BootStrap.Panel collapsible expanded={this.state.mapOpen}>
						<BootStrap.Table responsive>
							<tbody>
								{stepsMap}
							</tbody>
						</BootStrap.Table>
					</BootStrap.Panel>
				</div>
				</BootStrap.Modal.Body>
				<BootStrap.Modal.Footer>
					<BootStrap.Button onClick={this.handleCloseModalClick}>Close</BootStrap.Button>
				</BootStrap.Modal.Footer>
			</BootStrap.Modal>
        );
    }
}

export default ResultsModal;

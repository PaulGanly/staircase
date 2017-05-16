# Staircase Steps Calculator

An application that calculates the minimum number of steps needed to ascend a staircase. 

This is an application with a Spring Boot back-end and a React front-end. Bootstrap is used to style the front-end.

The user inputs the details for the staircase they want to calculate. The home screen is shown below:
### Home Screen
![Home Screen](https://github.com/PaulGanly/staircase/blob/master/Screenshots/main_screen.png)

From this screen the user can select the number of flights of stairs, the number of steps in each flight and the stride length of the person who is climbing the stairs. Upon clicking submit the details are sent to the Spring Boot REST API. Here the number of steps needed to climb the stairs is calculated and the response is returned to the front-end. When the response is recieved the results modal (below) is shown.

### Results Modal
![Results Modal](https://github.com/PaulGanly/staircase/blob/master/Screenshots/results_modal.png)

This displays the number of steps required to ascend the stairs. Clicking on the show map button displays a map of the stairs. The green blocks are the steps that have been stepped on, the blue blocks are the steps that have been skipped over.

### Prerequisites

You must have Maven, and Java 8 installed to run the application. 

Navigate to the following URL and follow instructions to install maven
```
https://maven.apache.org/download.cgi
```
Navigate to the following URL and follow instructions to install Java 8
```
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```

## Getting Started

Download or clone the repository.

Open a console in the folder containing the project. Build the application using the following command:
```
mvn clean install
```
Run the application using the following command:
```
mvn spring-boot:run
```
Run the application using the following command:
```
mvn spring-boot:run
```
Open a browser and navigate to `http://localhost:8080/` to use the application.

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](http://spring.io/) - Used for the applicaton backend
* [React](https://facebook.github.io/react/) - The web framework used

## Contributing

N/A

## Versioning

This is version 0.0.1 of the application.

## Authors

* **Paul Ganly** - *Initial work* - [PaulGanly](https://github.com/PaulGanly)

See also the list of [contributors](https://github.com/PaulGanly/staircase/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments


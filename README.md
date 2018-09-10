# Toy Robot Simulator

This is a command line Java-Gradle application that simulates a robot
on a specifiable grid.

## Description

- The application is a simulation of a toy robot moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  robot falling from the table must be prevented, however further valid movement
  commands must still be allowed.
 
The application that can read in commands of the following form

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y
  and facing NORTH, SOUTH, EAST or WEST.
- The origin (0,0) can be considered to be the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that,
  any sequence of commands may be issued, in any order, including another
  PLACE command. The application should discard all commands in the
  sequence until a valid PLACE command has been executed.
- MOVE will move the toy robot one unit forward in the direction it is currently
  facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot.
- Any move that would cause the robot to fall must be ignored.

## Example Input and Output:
    
a)

	PLACE 0,0,NORTH
    MOVE
    REPORT

	Output: 0,1,NORTH

b)

	PLACE 0,0,NORTH
	LEFT
	REPORT
	
	Output: 0,0,WEST

c)

	PLACE 1,2,EAST
	MOVE
	MOVE
	LEFT
	MOVE
	REPORT

	Output: 3,3,NORTH


## Requirements

- Implemented and tested using Java 8

- Project dependencies and compiling managed by gradle

- Gradle Application Plugin is used to build the tar which consist of executable file to run from command and also can be set to bash.

- The same executable can accept file as well as the command console.

- To Pass the file to the just use argument as "file=<<File_Name>>" with javarobosimulator executable windows and bash script. If the argumnet is not passed it will prompt for the console to get the user input.
 e.g. `./build/distributions/javarobosimulator-1.0/bin/javarobosimulator file=test.data`
## clean, build and Test

- clean: `gradle clean` or `./gradlew clean`

- Test: `gradle test` or  `./gradlew test` (Jacoco report generated)

- Build: `gradle clean build`, compiled jar in *target/* folder

## findBug && checkstye

- these are made available before each run.

##  Deliverable:
- The source files, the test data and any test code.

- Input can be from a file, or from standard input, as the developer chooses.

- It is not required to provide any graphical output showing the movement of the toy robot.

## chances of improvement

- Board can be designed with more interface based on shape.

- Also the board size can be used to be accepted from command line.
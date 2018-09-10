package com.mohit.robo.simulator;

import com.mohit.robo.exception.ToyRobotException;
import com.mohit.robo.grid.Board;
import com.mohit.robo.model.Command;
import com.mohit.robo.model.Direction;
import com.mohit.robo.model.Position;
import com.mohit.robo.model.ToyRobot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Simulator {

    private Board squareBoard;
    private ToyRobot robot;

    public Simulator(Board squareBoard, ToyRobot robot) {
        this.squareBoard = squareBoard;
        this.robot = robot;
    }

    public boolean placeToyRobot(Position position) throws ToyRobotException {

        if (squareBoard == null) {
            log.error("squareBoard object is null");
            throw new ToyRobotException("Invalid squareBoard object");
        }
        if (position == null) {
            log.error("position object is null");
            throw new ToyRobotException("Invalid position object");
        }
        if (position.getDirection() == null) {
            log.error("direction object is null");
            throw new ToyRobotException("Invalid direction value");
        }
        if (!squareBoard.isValidPosition(position)) {
            return false;
        }


        robot.setPosition(position);
        return true;
    }

    public String eval(String inputString) throws ToyRobotException {
        String[] args = inputString.split(" ");

        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException ex) {
            log.error("invalid command {}.", args[0]);
            throw new ToyRobotException("Invalid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            log.error("invalid command {} and argument length {}.", args[0] , args.length);
            throw new ToyRobotException("Invalid PLACE command");
        }

        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Command.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (NumberFormatException ex) {
                log.error("invalid params {} for PLACE command.", String.valueOf(params));
                throw new NumberFormatException("Invalid parameter for Place command");
            } catch (Exception ex) {
                log.error("invalid inputString {}.", inputString);
                throw new ToyRobotException("Invalid input string");
            }
        }

        return getPosition(command, x, y, commandDirection);
    }

    private String getPosition(Command command, int x, int y, Direction commandDirection) throws ToyRobotException {
        String output;
        switch (command) {
            case PLACE:
                output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
                break;
            case MOVE:
                if(robot.getPosition() == null) {
                    throw new ToyRobotException("ROBOT is not being Positioned. Please place the robot.");
                }
                Position newPosition = robot.getPosition().getNextPosition();
                if (!squareBoard.isValidPosition(newPosition)) {
                    output = String.valueOf(false);
                } else {
                    output = String.valueOf(robot.move(newPosition));
                }
                break;
            case LEFT:
                output = String.valueOf(robot.rotateLeft());
                break;
            case RIGHT:
                output = String.valueOf(robot.rotateRight());
                break;
            case REPORT:
                output = report();
                break;
            default:
                log.info("Invalid command");
                throw new ToyRobotException("Invalid command");
        }
        return output;
    }

    public String report() {
        if (robot.getPosition() == null) {
            return null;
        }
        return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }
}

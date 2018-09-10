package com.mohit.robo.model;

import com.mohit.robo.exception.ToyRobotException;

public class ToyRobot {

    private Position position;

    public ToyRobot() {
    }

    public ToyRobot(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position currentPosition) {
        this.position = currentPosition;
        return true;
    }

    public boolean move() throws ToyRobotException {
        return move(position.getNextPosition());
    }


    public boolean move(Position newPosition) {
        if (newPosition == null) {
            return false;
        }

        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }


    public boolean rotateLeft() {
        if (this.position.getDirection() == null) {
            return false;
        }

        this.position.setDirection(this.position.getDirection().leftDirection());
        return true;
    }


    public boolean rotateRight() {
        if (this.position.getDirection() == null) {
            return false;
        }

        this.position.setDirection(this.position.getDirection().rightDirection());
        return true;
    }

}

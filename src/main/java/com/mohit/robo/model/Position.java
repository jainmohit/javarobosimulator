package com.mohit.robo.model;

import com.mohit.robo.exception.ToyRobotException;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position(int cordX, int cordY, Direction direction) {
        this.x = cordX;
        this.y = cordY;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void change(int cordX, int cordY) {
        this.x = this.x + cordX;
        this.y = this.y + cordY;
    }

    public Position getNextPosition() throws ToyRobotException {
        if (this.direction == null) {
            throw new ToyRobotException("Invalid robot direction");
        }

        Position newPosition = new Position(this);
        switch (this.direction) {
            case NORTH:
                newPosition.change(0, 1);
                break;
            case EAST:
                newPosition.change(1, 0);
                break;
            case SOUTH:
                newPosition.change(0, -1);
                break;
            case WEST:
                newPosition.change(-1, 0);
                break;
            default:
                throw new ToyRobotException("Invalid Direction");
        }
        return newPosition;
    }
}

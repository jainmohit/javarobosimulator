package com.mohit.robo.grid.impl;

import com.mohit.robo.grid.Board;
import com.mohit.robo.model.Position;

public class SquareBoard implements Board {

    private int rows;
    private int columns;

    public SquareBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public boolean isValidPosition(Position position) {
        return !(
                position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0
        );
    }
}

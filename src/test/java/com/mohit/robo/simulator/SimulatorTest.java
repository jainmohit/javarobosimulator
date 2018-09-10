package com.mohit.robo.simulator;

import com.mohit.robo.exception.ToyRobotException;
import com.mohit.robo.grid.impl.SquareBoard;
import com.mohit.robo.model.Direction;
import com.mohit.robo.model.Position;
import com.mohit.robo.model.ToyRobot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimulatorTest {

    public static final int BOARD_X = 5;
    public static final int BOARD_Y = 5;

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);

        Assert.assertTrue(simulator.placeToyRobot(new Position(0, 1, Direction.NORTH)));
        Assert.assertTrue(simulator.placeToyRobot(new Position(2, 2, Direction.SOUTH)));
        Assert.assertFalse(simulator.placeToyRobot(new Position(6, 6, Direction.WEST)));
        Assert.assertFalse(simulator.placeToyRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testPlacingExceptions() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);

        thrown.expect(ToyRobotException.class);
        simulator.placeToyRobot(null);
        thrown.expect(ToyRobotException.class);
        simulator.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testEval() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);

        simulator.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", simulator.eval("REPORT"));

        simulator.eval("MOVE");
        simulator.eval("RIGHT");
        simulator.eval("MOVE");
        Assert.assertEquals("1,1,EAST", simulator.eval("REPORT"));

        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++) {
            simulator.eval("MOVE");
        }
        Assert.assertEquals("5,1,EAST", simulator.eval("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++) {
            simulator.eval("LEFT");
        }
        Assert.assertEquals("5,1,EAST", simulator.eval("REPORT"));

        // invalid commands
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", simulator.eval("PLACE12NORTH"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", simulator.eval("LEFFT"));
        thrown.expect(ToyRobotException.class);
        Assert.assertEquals("Invalid command", simulator.eval("RIGHTT"));
    }

    @Test
    public void testInvalidSquareBoard() throws ToyRobotException {

        SquareBoard board = null;
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);

        thrown.expect(ToyRobotException.class);
        simulator.placeToyRobot(null);
        thrown.expect(ToyRobotException.class);
        simulator.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testInvalidPlaceCommand() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);
        thrown.expect(ToyRobotException.class);
        simulator.eval("PLACE");
    }

    @Test
    public void testNumberFormatException() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);
        thrown.expect(NumberFormatException.class);
        simulator.eval("PLACE 2,a,NORTh");
    }

    @Test
    public void testMoveWithoutPosition() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);
        Assert.assertEquals(null, simulator.eval("REPORT"));
        thrown.expect(ToyRobotException.class);
        simulator.eval("MOVE");
    }

    @Test
    public void testInvalidInputDirection() throws ToyRobotException {

        SquareBoard board = new SquareBoard(BOARD_X, BOARD_Y);
        ToyRobot toyRobot = new ToyRobot();
        Simulator simulator = new Simulator(board, toyRobot);
        thrown.expect(ToyRobotException.class);
        simulator.eval("PLACE 2,2,ABC");
    }

}

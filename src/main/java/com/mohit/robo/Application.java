package com.mohit.robo;

import com.mohit.robo.exception.ToyRobotException;
import com.mohit.robo.grid.impl.SquareBoard;
import com.mohit.robo.model.ToyRobot;
import com.mohit.robo.simulator.Simulator;
import com.mohit.robo.utils.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.Console;
import java.util.List;

@Slf4j
public class Application {

    public static void main(String[] args) throws ToyRobotException {

        Console console = null;

        // can be modified to accept user input
        SquareBoard squareBoard = new SquareBoard(5, 5);
        ToyRobot robot = new ToyRobot();
        Simulator simulator = new Simulator(squareBoard, robot);

        if (args == null || args.length == 0) {
            console = System.console();
            if (console == null) {
                log.error("No console.");
                log.info("************************************************************************");
                log.info("Please use input argument as file using format as file=<<absolute path>>");
                log.info("************************************************************************");
                System.exit(1);
            }
            System.out.println("Robot Simulator");
            System.out.println("Please enter a valid command, Valid commands are:");
            System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");
            boolean keepRunning = true;
            while (keepRunning) {
                String inputString = console.readLine(": ");
                if ("EXIT".equals(inputString)) {
                    keepRunning = false;
                } else {
                    System.out.println(getEvaluation(simulator, inputString));
                }
            }

        } else {
            List<String> instructions = ApplicationUtil.getInstructionsFromFileArgs(args[0]);
            log.info("Array size of instruction in the file is {}", instructions.size());
            for (String instruction : instructions) {
                System.out.println("Instruction : " + instruction + " Output  : " + getEvaluation(simulator, instruction));
            }
        }

    }

    private static String getEvaluation(Simulator simulator, String inputString) {
        String outputVal = null;

        try {
            outputVal = simulator.eval(inputString);
        } catch (ToyRobotException ex) {
            log.error("Exception occur when evaluation the inputs. " + ex.getMessage(), ex);
        }
        return outputVal;
    }
}
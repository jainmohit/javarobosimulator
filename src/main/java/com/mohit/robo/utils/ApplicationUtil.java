package com.mohit.robo.utils;

import com.mohit.robo.exception.ToyRobotException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ApplicationUtil {

    public static List<String> getInstructionsFromFileArgs(String arg) throws ToyRobotException{
        List<String> instructionSet = null;
        if (!arg.contains("file=")) {
            log.info("Please use input argument as file using format as file=<<absolute path>>");
            throw new ToyRobotException("Not a valid file argument.");
        } else {
            String fileName = arg.trim().split("=")[1];
            if (fileName != null && fileName.length() > 0) {
                instructionSet = FileParser.parseFile(fileName);
            }
        }
        return instructionSet;
    }
}

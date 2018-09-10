package com.mohit.robo.utils;

import com.mohit.robo.exception.ToyRobotException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class FileParser {
    public static List<String> parseFile(String fileName) throws ToyRobotException {

        List<String> instructionSet = null;
        try {
            instructionSet = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (NoSuchFileException ex) {
            System.out.println("File with name and location " + fileName + " does not exist.");
            log.error("Exception occur when trying to read the file. " + ex.getMessage(), ex);
            throw new ToyRobotException("File is not Present.");
        } catch (IOException ex) {
            log.error("IO Exception occurred." + ex.getMessage(), ex);
        }
        return instructionSet;
    }
}

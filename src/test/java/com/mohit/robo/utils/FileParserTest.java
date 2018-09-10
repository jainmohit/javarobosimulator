package com.mohit.robo.utils;

import com.mohit.robo.exception.ToyRobotException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class FileParserTest {


    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void validFileTest() throws IOException, ToyRobotException {
        final File tempFile = tempFolder.newFile("input.txt");
        FileParser.parseFile(tempFile.toPath().toString());
    }

    @Test(expected = ToyRobotException.class)
    public void noSuchFileExceptionTest() throws ToyRobotException{
        FileParser.parseFile("fake.txt");
    }

    @Test
    public void throwsIOException() throws IOException, ToyRobotException {
        final File tempFile = tempFolder.newFile("input.txt");
        FileUtils.writeStringToFile(tempFile, "\000X");
        FileParser.parseFile(tempFile.toPath().toString());
    }

}

package com.mohit.robo.utils;

import com.mohit.robo.exception.ToyRobotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ApplicationUtilTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test (expected = ToyRobotException.class)
    public void inValidInstructionFromFile() throws ToyRobotException {
        ApplicationUtil.getInstructionsFromFileArgs("fakeArgs");
    }

    @Test
    public void validInstructionFromFile() throws IOException, ToyRobotException {
        final File tempFile = tempFolder.newFile("input.txt");
        ApplicationUtil.getInstructionsFromFileArgs("file=" + tempFile.toPath().toString());
    }
}

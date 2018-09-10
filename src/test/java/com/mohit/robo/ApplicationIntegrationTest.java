package com.mohit.robo;

import com.mohit.robo.exception.ToyRobotException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class ApplicationIntegrationTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();


    @Test
    public void testFileArgsApplication() throws IOException, ToyRobotException {
        final File tempFile = tempFolder.newFile("input.txt");
        FileUtils.writeStringToFile(tempFile, "PLACE 0,0,NORTH\nMOVE\nREPORT");
        String[] args = new String[1];
        args[0] = "file=" + tempFile;
        Application.main(args);
    }

}

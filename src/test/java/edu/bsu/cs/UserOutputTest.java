package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPrintMessage() {
        UserOutput output = new UserOutput();
        output.print("Test message");

        assertEquals("Test message", outContent.toString());
    }

    @Test
    public void testPrintFormattedRevisions() {
        UserOutput output = new UserOutput();
        String message = "1  2025-08-13T22:47:03Z  User1\n";
        output.print(message);

        assertEquals(message, outContent.toString());
    }

    @Test
    public void testPrintError() {
        UserOutput output = new UserOutput();
        output.printError("Error: Article not found");

        String expected = "Error: Article not found" + System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }

    @Test
    public void testPrintNetworkError() {
        UserOutput output = new UserOutput();
        output.printError("Network error occurred");

        String expected = "Network error occurred" + System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }

    @Test
    public void testPrintMultipleMessages() {
        UserOutput output = new UserOutput();
        output.print("Line 1\n");
        output.print("Line 2\n");

        assertEquals("Line 1\nLine 2\n", outContent.toString());
    }

    @Test
    public void testPrintEmptyString() {
        UserOutput output = new UserOutput();
        output.print("");

        assertEquals("", outContent.toString());
    }
}
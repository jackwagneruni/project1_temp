package edu.bsu.cs;

public class UserOutput {

    public void print(String message) {
        System.out.print(message);
    }

    public void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public void exit() {
        System.exit(0);
    }

    public void exitWithError() {
        System.exit(1);
    }
}
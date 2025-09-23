package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


public class UserInputTest {
    @Test
    public void testUserInput(){
        Scanner testscanner = new Scanner("Frank Zappa");
        UserInput input = new UserInput(testscanner);

        String result = input.getArticleInput();
        assertEquals("Frank Zappa", result);


    }
}

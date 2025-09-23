package edu.bsu.cs;
import java.util.Scanner;

public class UserInput {
    public String getArticleInput(){
        Scanner console = new Scanner(System.in);
        System.out.print("Please enter the Wikipedia Article Name: ");
        String input = console.nextLine();
        return  input;
    }


}
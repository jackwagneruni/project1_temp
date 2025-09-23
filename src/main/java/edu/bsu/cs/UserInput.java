package edu.bsu.cs;
import java.util.Scanner;

public class UserInput {
    private final Scanner scanner;

    public UserInput(Scanner scanner) {
        this.scanner = scanner;
    }
    public UserInput(){
        this.scanner = new Scanner(System.in);
    }

    public String getArticleInput(){

        System.out.print("Please enter the Wikipedia Article Name: ");
        return scanner.nextLine();
    }


}

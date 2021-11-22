package tdd.todo;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public int getInput(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int operationNumber;

        System.out.print(("Podaj numer operacji, którą chcesz wykonać: "));

        try{
            operationNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            operationNumber = -1;
        }
        scanner.close();
        return operationNumber;
    }
}

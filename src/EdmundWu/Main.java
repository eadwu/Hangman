package EdmundWu;

import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Hangman person = new EdmundWu.Hangman("hangman");

        while (person.getStrikes() < 6 && !(person.getWord().equals(person.getOutput()))) {
            String current = "";

            System.out.println("Guess a letter.");
            try {
                if (stdin.hasNext()) {
                    String input = stdin.next("[a-zA-Z]");
                    if (input.length() == 1)
                        current = input.toUpperCase();
                    else
                        throw new InputMismatchException("Unable to convert to char.");
                } else {
                    throw new InputMismatchException("Unable to retrieve input.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }

            person.execute(current);
        }
        stdin.close();
    }
}
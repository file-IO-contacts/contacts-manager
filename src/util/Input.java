package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public String getString() {
        String userInput = scanner.next();
        return userInput;
    }

    public boolean yesNo(String prompt) {
        String userInput = scanner.next();
        if(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
            return true;
        }else {
            return false;
        }
    }

    public int getInt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String userInput;

        try {
            userInput = scanner.next();
            return Integer.valueOf(userInput);

        }catch(NumberFormatException e) {
            System.out.println("not an integer, please try again");
            return getInt();
        }
    }

    public int getInt(int min, int max) {
//        System.out.println("Enter an integer from " + min + " and " + max);
        int userNumber = getInt();
        if (userNumber < min || userNumber > max) {
            System.out.println("please try again");
            userNumber = getInt(min, max);
        }
        return userNumber;
    }

    public double getDouble() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String userInput;

        try {
            userInput = scanner.next();
            return Double.valueOf(userInput);
        } catch (NumberFormatException e) {
            System.out.println("invalid double, please try agin");
            return getDouble();
        }
    }


    public double getDouble(double min, double max) {
        System.out.println("Enter an integer from " + min + " and " + max);
        double userDecimal = getDouble();
        if(userDecimal < min || userDecimal > max) {
            System.out.println("please try again");
            userDecimal = getDouble(min, max);
        }
        return userDecimal;
    }






}

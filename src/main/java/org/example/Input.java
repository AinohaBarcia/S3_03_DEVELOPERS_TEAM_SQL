package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    static Scanner sc = new Scanner(System.in);
    public static int getInt(String message) {
        int input = 0;
        boolean acceptedInput = false;
        do {
            System.out.println(message);
            try {
                input = sc.nextInt();
                acceptedInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Format error");
                sc.nextLine();
            }
        } while (!acceptedInput);
        return input;
    }
    public static byte getByte(String message) {
        byte input = 0;
        boolean acceptedInput = false;
        do {
            System.out.println(message);
            try {
                input = sc.nextByte();
                acceptedInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Format error");
                sc.nextLine();
            }
        } while (!acceptedInput);
        return input;
    }
    public static float getFloat(String message) {
        float input = 0;
        boolean acceptedInput = false;
        do {
            System.out.println(message);
            try {
                input = sc.nextFloat();
                acceptedInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Format error");
                sc.nextLine();
            }
        } while (!acceptedInput);
        return input;
    }

    public static String getString(String message) {
        String input = "";
        boolean acceptedInput = false;
        do {
            System.out.println(message);
            try {
                input = sc.next();
                acceptedInput = true;
            } catch (Exception e) {
                System.out.println("Error");
                sc.nextLine();
            }
        } while (!acceptedInput);
        return input;
    }

}

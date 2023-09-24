package org.example;

import java.util.Scanner;

public class UserInputScanner {
    Scanner sc = new Scanner(System.in);

    public String busStopCodeUserInput(){
        String stopCode = "";

        System.out.println("Please enter the bus stop code you wish to search");
        System.out.println("For Example: 490000129R");
        stopCode = sc.nextLine();

        return stopCode;
    }

    public String postCodeInput(){
        String input = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the postcode you wish to search.");
        System.out.println("For example SE10 0QJ:");
        input = sc.nextLine();

        return input;
    }
}

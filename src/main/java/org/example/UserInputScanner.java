package org.example;

import java.util.Scanner;

public class UserInputScanner {
    Scanner sc = new Scanner(System.in);

    public String inputOptionSelector(){
        String input = "";

        System.out.println("Please choose whether you would like to enter:");
        System.out.println("'Postcode' or 'Stopcode':");
        input = sc.nextLine();

        return input;
        }

    public String busStopCodeUserInput(){
        String input = "";

        System.out.println("Please enter the bus stop code you wish to search");
        System.out.println("For Example: 490000129R");

        input = sc.nextLine();

        return input;
    }

    public String postCodeInput(){
        String input = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the postcode you wish to search.");
        System.out.println("For example SE100QJ:");
        input = sc.nextLine();

        return input;
    }
    public void close(){
        sc.close();
    }
}

package org.example;

import java.util.Scanner;

public class UserInputScanner {
    public String busStopCodeUserInput(){
        String stopCode = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the bus stop code you wish to search");
        System.out.println("For Example: 490000129R");
        stopCode = sc.nextLine();
        sc.close();
        return stopCode;
    }
}

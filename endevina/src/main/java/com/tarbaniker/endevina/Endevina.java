/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tarbaniker.endevina;

import java.util.Scanner;  // Import the Scanner class

/**
 *
 * @author enric
 */
public class Endevina {

    public static void main(String[] args) {
        System.out.println("Hola Mon!");
        
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Entra animal ");

    String animalName = myObj.nextLine();  // Read user input
    System.out.println("\nanimal és: " + animalName);  // Output user input
    }
}

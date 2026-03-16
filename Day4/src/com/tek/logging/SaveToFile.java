package com.tek.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class SaveToFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            FileWriter writer = new FileWriter("test3.txt", true);
            while (true) {
                System.out.print("Enter text: ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(input + "\n");
            }
            writer.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
        sc.close();
    }
}




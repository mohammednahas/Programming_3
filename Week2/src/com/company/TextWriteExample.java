package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriteExample {

    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("students.txt")) {

            writer.write("Ahmed,85\n");
            writer.write("Mohamed,90\n");
            writer.write("Ali,78\n");

            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
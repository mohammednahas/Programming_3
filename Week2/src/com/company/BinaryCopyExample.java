package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopyExample {

    public static void main(String[] args) {

        try (
                FileInputStream input =
                        new FileInputStream("images.jpeg");

                FileOutputStream output =
                        new FileOutputStream("images_copy.jpeg")
        ) {

            int data;

            while ((data = input.read()) != -1) {  //input.read() read one byte at time

                output.write(data);
            }

            System.out.println("Image copied successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
package com.company;
import java.io.*;
public class BinaryCopyUsingBufferStream {

    public static void main(String[] args) {

        try (
                BufferedInputStream input =
                        new BufferedInputStream(
                                new FileInputStream("images.jpeg"));

                BufferedOutputStream output =
                        new BufferedOutputStream(
                                new FileOutputStream("images_copy.jpeg"))
        ) {

            int data;

            while ((data = input.read()) != -1) {
                output.write(data);
            }

            System.out.println("Image copied successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopyExampleWithBuffer {

    public static void main(String[] args) {

        /*
         * In the previous example, we read one byte from the source file
         * and write it to the destination file, then repeat the process
         * until the end of the file.
         *
         * If the image size is 4 MB (approximately 4,194,304 bytes),
         * the program performs approximately:
         * - 4,194,304 read operations.
         * - 4,194,304 write operations.
         *
         * This results in a large number of I/O operations, which can
         * affect performance, especially when dealing with large files.
         *
         * To improve performance, we use a buffer to read and write
         * multiple bytes at once instead of processing one byte at a time.
         */

        try (
                FileInputStream input =
                        new FileInputStream("images.jpeg");

                FileOutputStream output =
                        new FileOutputStream("images_copy.jpeg")
        ) {

            byte[] buffer = new byte[4096];

            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {

                output.write(buffer, 0, bytesRead);
            }

            System.out.println("Image copied successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
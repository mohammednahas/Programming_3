package com.company;

import java.io.*;

public class RandomAccess {

    public static void main(String[] args) {

        try (
                RandomAccessFile file =
                        new RandomAccessFile("grades.txt", "rw")
        ) {

            file.writeInt(80);
            file.writeInt(75);
            file.writeInt(90);
            file.writeInt(85);

            // Go directly to the third grade
            file.seek(8);

            file.writeInt(95);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.company;

import java.io.*;

public class SaveStudent {

    public static void main(String[] args) {

        Student student =
                new Student(1, "Ahmed", 85);

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream("student.dat"))
        ) {

            output.writeObject(student);

            System.out.println("Student saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
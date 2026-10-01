package com.company;

import java.io.*;

public class ReadStudent {

    public static void main(String[] args) {

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream("student.dat"))
        ) {

            Student student =
                    (Student) input.readObject();

            System.out.println(student);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
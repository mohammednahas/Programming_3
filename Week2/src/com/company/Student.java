package com.company;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + grade;
    }

    public static void main(String[] args) {
//        Student s1=new Student(1,"mohammed",84);
//        System.out.println(s1);
    }
}
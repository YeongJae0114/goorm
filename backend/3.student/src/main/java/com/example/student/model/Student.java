package com.example.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student implements Comparable<Student>{
    @Override
    public int compareTo(Student o) {
        return this.grade - o.getGrade();
    }

    private String name;
    private int grade;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

package com.example.student.repository;

import com.example.student.model.Student;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    Set<Student> students = new HashSet<>();

    public void add(Student student){
        students.add(student);
    }

    public List<Student>getAll(){
        return students.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}


package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    // 이름과 성적을 입력받아 저장
    public Student add(String name, int grade){
        Student student = new Student(name, grade);
        studentRepository.add(student);
        return student;
    }


    // 전체 성적 조회
}

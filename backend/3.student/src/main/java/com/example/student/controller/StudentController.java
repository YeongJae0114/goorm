package com.example.student.controller;

import com.example.student.model.ApiResponse;
import com.example.student.model.ErrorCode;
import com.example.student.service.StudentService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    public <T>ApiResponse<T>makeResponse(List<T> result){
        return new ApiResponse<>(result);
    }

    public <T>ApiResponse<T>makeResponse(T result){
        return makeResponse(Collections.singletonList(result));
    }

    @PostMapping("/student")
    public ApiResponse add(@RequestParam("name") String name,
                           @RequestParam("grade") int grade){
        if (grade>=6){
            throw new ClassCastException(ErrorCode.BAD_REQUEST,"asd",new )
        }

        return makeResponse(studentService.add(name, grade));
    }

}

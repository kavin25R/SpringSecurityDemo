package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students=new ArrayList<>(
            Arrays.asList(
                    new Student(1,"kavin","CSD"),
                    new Student(2,"Gayathiri","BME")
            )
    );

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/students")
    public void addStudents(@RequestBody Student student){
        students.add(student);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCSRF(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}

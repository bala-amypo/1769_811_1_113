/*package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentProfileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.StudentProfile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
public class StudentProfileContoller {
    @Autowired
    StudentProfileService ser;
    
    @PostMapping("addStudent")
    public StudentProfile addStudent(@RequestBody  StudentProfile Student) {
        return  ser.addStudents(Student);   
    }
    @GetMapping("getStudent")
    public StudentProfile getStudentById(@PathVariable Long id) {
        return ser.getStudentById(id);
    }
    @GetMapping("getStudents")
    public StudentProfile getStudents() {
        return (StudentProfile) ser.getStudents();
    }
    @PutMapping("path/{id}")
    public StudentProfile updateStudent(@PathVariable Long id) {
       
        
        return ser.updateStudent(id);
    }
    @GetMapping("getbyStudentid")
    public StudentProfile getbyStudentid (@PathVariable String studentId) {
        return ser.getbyStudentid(studentId);
    }
    
    

}
*/
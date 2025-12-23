package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService{

@Autowired
StudentProfileRepository repo;

@Override
public StudentProfile createStudent(StudentProfile student){
if(repo.existsByEmail(student.getEmail())){
throw new RuntimeException("Email already used");
}
return repo.save(student);
}

@Override
public StudentProfile updateRepeatOffender(String studentId,boolean repeatOffender){
StudentProfile student=repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("User not found"));
student.setRepeatOffender(repeatOffender);
return repo.save(student);
}

@Override
public List<StudentProfile> getAllStudents(){
return repo.findAll();
}

@Override
public StudentProfile getById(Long id){
return repo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
}

@Override
public StudentProfile getByStudentId(String studentId){
return repo.findByStudentId(studentId).orElseThrow(()->new RuntimeException("User not found"));
}
}

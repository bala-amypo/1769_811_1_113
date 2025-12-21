package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class IntegrityCaseServiceImpl
implements IntegrityCaseService {

private final IntegrityCaseRepository repository;

public IntegrityCaseServiceImpl(IntegrityCaseRepository repository) {
this.repository = repository;
}

public IntegrityCase createCase(IntegrityCase integrityCase) {
if(integrityCase.getStudentProfile() == null)
throw new IllegalArgumentException("StudentProfile required");

return repository.save(integrityCase);
}

public IntegrityCase getCaseById(Long id) {
return repository.findById(id)
.orElseThrow(() ->
new ResourceNotFoundException("Case not found"));
}

public List<IntegrityCase> getCasesByStudent(Long studentId) {
return repository.findByStudentProfile_Id(studentId);
}

public IntegrityCase updateStatus(Long id, String status) {
IntegrityCase c = getCaseById(id);
c.setStatus(status);
return repository.save(c);
}
}

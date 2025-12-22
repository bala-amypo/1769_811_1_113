package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

public interface RepeatOffenderRecordService {

RepeatOffenderRecord refreshRepeatOffender(StudentProfile studentProfile);

RepeatOffenderRecord getByStudentProfile(StudentProfile studentProfile);

List<RepeatOffenderRecord> getAll();
}

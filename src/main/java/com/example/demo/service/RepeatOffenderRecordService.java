package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {

RepeatOffenderRecord recalculate(Long studentId);

RepeatOffenderRecord getByStudent(Long studentId);

/* REQUIRED BY TESTS */
RepeatOffenderRecord updateRepeatOffenderStatus(Long studentId);

}

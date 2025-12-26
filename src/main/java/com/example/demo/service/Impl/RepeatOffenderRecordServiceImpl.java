package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RepeatOffenderRecordService;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
@Transactional
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository caseRepo;
private final RepeatOffenderRecordRepository recordRepo;
private final RepeatOffenderCalculator calculator;

public RepeatOffenderRecordServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository caseRepo,
RepeatOffenderRecordRepository recordRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.caseRepo = caseRepo;
this.recordRepo = recordRepo;
this.calculator = calculator;
}

import java.util.List;
import com.example.demo.entity.RepeatOffenderRecord;

RepeatOffenderRecord refreshRepeatOffenderData(Long studentId);

RepeatOffenderRecord getRecordByStudent(Long studentId);

List<RepeatOffenderRecord> getAllRepeatOffenders();



}

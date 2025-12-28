package com.example.demo.service.impl;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;

import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class RepeatOffenderRecordServiceImpl
implements RepeatOffenderRecordService {

@Override
public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {
return null;
}

@Override
public Optional<RepeatOffenderRecord> getRecordByStudent(Long studentId) {
return Optional.empty();
}

@Override
public List<RepeatOffenderRecord> getAllRepeatOffenders() {
return Collections.emptyList();
}
}

package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;
import java.util.List;
import java.util.Optional;

public interface RepeatOffenderRecordService {

RepeatOffenderRecord refreshRepeatOffenderData(Long studentId);

Optional<RepeatOffenderRecord> getRecordByStudent(Long studentId);

List<RepeatOffenderRecord> getAllRepeatOffenders();
}

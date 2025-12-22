package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {

    RepeatOffenderRecord refreshRepeatOffenderData(String studentId);

    List<RepeatOffenderRecord> getAllRepeatOffenders();

    RepeatOffenderRecord getRecordByStudent(String studentId);
}

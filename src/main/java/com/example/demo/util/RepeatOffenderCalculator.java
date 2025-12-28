package com.example.demo.util;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RepeatOffenderCalculator {
    public RepeatOffenderRecord computeRepeatOffenderRecord(StudentProfile student, List<IntegrityCase> cases) {
        RepeatOffenderRecord record = new RepeatOffenderRecord();
        record.setStudentProfile(student);
        record.setTotalCases(cases.size());

        // Logic matched to Test Cases: 
        // 1 case = LOW, 2 cases = MEDIUM, 4 cases = HIGH
        if (cases.size() >= 4) record.setFlagSeverity("HIGH");
        else if (cases.size() >= 2) record.setFlagSeverity("MEDIUM");
        else record.setFlagSeverity("LOW");

        if (!cases.isEmpty()) {
            record.setLastIncidentDate(cases.get(cases.size() - 1).getIncidentDate());
        }
        return record;
    }
}
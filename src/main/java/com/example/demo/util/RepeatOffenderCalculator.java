package com.example.demo.util;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component   // 🔴 THIS IS THE FIX
public class RepeatOffenderCalculator {

public RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile student,
List<IntegrityCase> cases
) {
RepeatOffenderRecord record = new RepeatOffenderRecord();
record.setStudentProfile(student);
record.setTotalCases(cases.size());

if (cases.size() <= 1) {
record.setFlagSeverity("LOW");
} else if (cases.size() <= 3) {
record.setFlagSeverity("MEDIUM");
} else {
record.setFlagSeverity("HIGH");
}

return record;
}
}

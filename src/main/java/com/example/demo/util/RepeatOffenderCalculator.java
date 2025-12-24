package com.example.demo.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

@Component
public class RepeatOffenderCalculator {

public RepeatOffenderRecord calculate(
StudentProfile student,
List<IntegrityCase> cases
) {

RepeatOffenderRecord record = new RepeatOffenderRecord();
record.setStudentProfile(student);
record.setTotalCases(cases.size());

if(!cases.isEmpty()) {
record.setFirstIncidentDate(
cases.stream()
.map(IntegrityCase::getIncidentDate)
.min(LocalDate::compareTo)
.orElse(null)
);
}

if(cases.size() >= 4) {
record.setFlagSeverity("HIGH");
} else if(cases.size() >= 2) {
record.setFlagSeverity("MEDIUM");
} else {
record.setFlagSeverity("LOW");
}

return record;
}
}

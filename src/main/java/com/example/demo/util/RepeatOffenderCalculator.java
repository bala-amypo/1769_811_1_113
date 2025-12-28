package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

@Component
public class RepeatOffenderCalculator {

/* EXISTING LOGIC */
public RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile student,
List<IntegrityCase> cases
) {

RepeatOffenderRecord record = new RepeatOffenderRecord();
record.setStudentProfile(student);
record.setTotalCases(cases.size());

if (cases.size() >= 4) {
record.setFlagSeverity("HIGH");
}
else if (cases.size() >= 2) {
record.setFlagSeverity("MEDIUM");
}
else {
record.setFlagSeverity("LOW");
}

return record;
}

/* REQUIRED BY SERVICE + TESTS */
public boolean isRepeatOffender(List<IntegrityCase> cases) {
return cases != null && cases.size() >= 2;
}

}

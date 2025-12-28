package com.example.demo.util;

import com.example.demo.entity.*;
import java.util.List;

public class RepeatOffenderCalculator {

public RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile student,
List<IntegrityCase> cases
) {
RepeatOffenderRecord r = new RepeatOffenderRecord();
r.setStudentProfile(student);
r.setTotalCases(cases.size());

if (cases.size() <= 1) {
r.setFlagSeverity("LOW");
} else if (cases.size() <= 3) {
r.setFlagSeverity("MEDIUM");
} else {
r.setFlagSeverity("HIGH");
}

return r;
}
}

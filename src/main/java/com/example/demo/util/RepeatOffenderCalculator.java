package com.example.demo.util;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

public class RepeatOffenderCalculator {

public RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile studentProfile,
List<IntegrityCase> cases
) {

int totalCases = cases.size();

String severity = "LOW";
if(totalCases >= 4) severity = "HIGH";
else if(totalCases >= 2) severity = "MEDIUM";

LocalDate firstIncidentDate =
cases.stream()
.map(IntegrityCase::getIncidentDate)
.min(Comparator.naturalOrder())
.orElse(null);

return new RepeatOffenderRecord(
studentProfile,
totalCases,
firstIncidentDate,
severity
);
}
}

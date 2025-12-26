package com.example.demo.util;

import java.util.List;
import org.springframework.stereotype.Component;
import com.example.demo.entity.*;

@Component
public class RepeatOffenderCalculator {

public RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile student,
List<IntegrityCase> cases
) {

int count = cases.size();
String level = count >= 4 ? "HIGH" : count >= 2 ? "MEDIUM" : "LOW";

return new RepeatOffenderRecord(student, count, null, level);
}

}

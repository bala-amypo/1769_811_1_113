package com.example.demo.util;

import java.util.List;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;

public interface RepeatOffenderCalculator {

RepeatOffenderRecord computeRepeatOffenderRecord(
StudentProfile studentProfile,
List<IntegrityCase> cases
);
}

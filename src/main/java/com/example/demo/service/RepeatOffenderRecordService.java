package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {

RepeatOffenderRecord refreshRepeatOffenderData(Long studentId);

RepeatOffenderRecord getRecordByStudent(Long studentId);

List<RepeatOffenderRecord> getAllRepeatOffenders();

}
package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {
RepeatOffenderRecord recalculate(Long studentId);
}

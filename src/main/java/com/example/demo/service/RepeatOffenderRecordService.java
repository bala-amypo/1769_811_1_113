package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {

RepeatOffenderRecord recalculate(Long studentProfileId);

RepeatOffenderRecord getByStudent(Long studentProfileId);
}

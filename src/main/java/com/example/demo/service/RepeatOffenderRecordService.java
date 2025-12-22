package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {

    RepeatOffenderRecord createOrUpdate(Long studentProfileId);

    RepeatOffenderRecord getById(Long id);

    List<RepeatOffenderRecord> getAll();
}

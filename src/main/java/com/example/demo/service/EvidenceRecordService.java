package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EvidenceRecord;

public interface EvidenceRecordService {

    EvidenceRecord submitEvidence(EvidenceRecord evidence);

    List<EvidenceRecord> getEvidenceByCase(Long caseId);

    EvidenceRecord getEvidenceById(Long id);

    List<EvidenceRecord> getAllEvidence();
}

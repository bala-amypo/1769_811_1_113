package com.example.demo.service;

import com.example.demo.entity.EvidenceRecord;
import java.util.List;
import java.util.Optional;

public interface EvidenceRecordService {

EvidenceRecord submitEvidence(EvidenceRecord evidence);

List<EvidenceRecord> getEvidenceByCase(Long caseId);

Optional<EvidenceRecord> getEvidenceById(Long id);

List<EvidenceRecord> getAllEvidence();
}
